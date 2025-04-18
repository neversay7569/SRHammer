package ru.sr.hammer.service.impl;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.sr.hammer.data.Hammer;
import ru.sr.hammer.service.BlockBreakerService;
import ru.sr.hammer.service.HammerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class HammerServiceImpl implements HammerService {

    // Map to collect data about cooldowns (anti-lag system)
    private final Map<Player, Long> lastUsage = new ConcurrentHashMap<>();
    /**
     * Method to use scan event to hammer using
     *
     * @param event player interact event
     */
    @Override
    public boolean tryToBreak(@NotNull BlockBreakEvent event) {
        // Compatibility with other plugins
        if (event.isCancelled()) {
            return false;
        }

        // Use only on hammers
        if (!(event.getItem() instanceof Hammer hammer)) {
            return false;
        }

        if (isOnCooldown(event.getPlayer(), hammer)) {
            return false;
        }

        // Preventing phantom breaks
        if (!event.getPlayer().isOnline()) {
            return false;
        }

        return true;
    }

    /**
     * If HammerService#tryToBreak(PlayerInteractEvent) is true, this method calls to break blocks
     *
     * @param event player interact event
     * @param hammer hammer
     * @return none
     */
    @Override
    public CompletableFuture<Void> breakWithHammer(@NotNull BlockBreakEvent event, Hammer hammer) {
        if (event.getPlayer() == null || event.getBlock() == null) {
            return CompletableFuture.completedFuture(null);
        }

        // Prevent PlayerAuthInput packet error
        event.setCancelled(true);

        return CompletableFuture.runAsync(() -> {
            Player player = event.getPlayer();
            Block centerBlock = event.getBlock();
            Level level = player.getLevel();
            Item tool = event.getItem();

            // Prepare list of blocks to break
            List<Vector3> blocksToBreak = new ArrayList<>(27);

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        Vector3 blockPos = new Vector3(
                                centerBlock.x + x,
                                centerBlock.y + y,
                                centerBlock.z + z
                        );
                        Block block = level.getBlock(blockPos);

                        // Skip air blocks
                        if (block.getId() != 0) {
                            blocksToBreak.add(blockPos);
                        }
                    }
                }
            }

            try {
                for (Vector3 blockPos : blocksToBreak) {

                    // Async breaking blocks
                    BlockBreakerService.getInstance().breakBlock(blockPos, tool, player, level);
                }
            } catch (Exception e) {
                log.error("HammerService#breakWithHammer(@NotNull PlayerInteractEvent event, Hammer hammer) unhandled exception", e);
            }

            updateCooldown(player);
        });
    }

    /**
     * Checks player cooldown
     *
     * @param player player
     * @return boolean
     */
    private boolean isOnCooldown(Player player, Hammer hammer) {
        Long lastUsed = lastUsage.get(player);
        if (lastUsed == null) return false;

        long elapsed = System.currentTimeMillis() - lastUsed;
        if (elapsed < hammer.getCooldown()) {
            return true;
        }
        return false;
    }

    /**
     * Updates player cooldown
     *
     * @param player player
     */
    private void updateCooldown(Player player) {
        lastUsage.put(player, System.currentTimeMillis());

        if (lastUsage.size() > 1000) {
            lastUsage.keySet().removeIf(p -> !p.isOnline());
        }
    }
}
