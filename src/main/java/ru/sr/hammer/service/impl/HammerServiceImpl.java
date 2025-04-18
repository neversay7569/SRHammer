package ru.sr.hammer.service.impl;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.sr.hammer.items.Hammer;
import ru.sr.hammer.service.HammerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class HammerServiceImpl implements HammerService {
    /**
     * Method to use scan event to hammer using
     *
     * @param event player interact event
     */
    @Override
    public boolean tryToBreak(@NotNull PlayerInteractEvent event) {
        // Compatibility with other plugins
        if (event.isCancelled()) {
            return false;
        }

        // Use only on hammers
        if (!(event.getItem() instanceof Hammer)) {
            return false;
        }

        // Preventing phantom breaks
        if (!event.getPlayer().isOnline()) {
            return false;
        }

        // Preventing erroneous breakages
        if (event.getAction() != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
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
    public CompletableFuture<Void> breakWithHammer(@NotNull PlayerInteractEvent event, Hammer hammer) {
        if (event.getPlayer() == null || event.getBlock() == null) {
            return CompletableFuture.completedFuture(null);
        }

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

        // Process blocks asynchronously
        return CompletableFuture.runAsync(() -> {
            try {
                for (Vector3 blockPos : blocksToBreak) {
                    Block block = level.getBlock(blockPos);

                    // Create and call break event
                    BlockBreakEvent breakEvent = new BlockBreakEvent(
                            player,
                            block,
                            tool,
                            block.getDrops(tool),
                            false,
                            false
                    );

                    level.getServer().getPluginManager().callEvent(breakEvent);

                    if (!breakEvent.isCancelled()) {
                        // Break the block
                        block.onBreak(tool);

                        if (hammer.getMiningSpeed() > 0) {
                            Thread.sleep(hammer.getMiningSpeed());
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("HammerService#breakWithHummer(@NotNull PlayerInteractEvent event, Hammer hammer) unhandled interrupt exception", e);
            } catch (Exception e) {
                log.error("HammerService#breakWithHummer(@NotNull PlayerInteractEvent event, Hammer hammer) unhandled exception", e);
            }
        });
    }
}
