package ru.sr.hammer.service.impl;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.level.particle.DestroyBlockParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.StringTag;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.potion.Effect;
import ru.sr.hammer.service.BlockBreakerService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BlockBreakerServiceImpl implements BlockBreakerService {

    /**
     * Async block break for Hammer
     *
     * @param vector block coordinates
     * @param item   item-breaker
     * @param player player
     * @param level  level
     * @return none
     */
    @Override
    public CompletableFuture<Void> breakBlock(Vector3 vector, Item item, Player player, Level level) {
        return CompletableFuture.runAsync(() -> {
            if (player != null && player.getGamemode() > Player.ADVENTURE) {
                return;
            }

            Block target = level.getBlock(vector);
            Item[] drops;
            int dropExp = target.getDropExp();

            if (player != null) {
                if (player.getGamemode() == Player.ADVENTURE) {
                    Tag tag = item.getNamedTagEntry("CanDestroy");
                    boolean canBreak = false;
                    if (tag instanceof ListTag) {
                        for (Tag v : ((ListTag<? extends Tag>) tag).getAll()) {
                            if (v instanceof StringTag) {
                                Item entry = Item.fromString(((StringTag) v).data);
                                if (entry.getId() > 0 && entry.getBlockUnsafe() != null && entry.getBlockUnsafe().getId() == target.getId()) {
                                    canBreak = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!canBreak) {
                        return;
                    }
                }

                double breakTime = target.calculateBreakTime(item, player);

                if (player.isCreative() && breakTime > 0.15) {
                    breakTime = 0.15;
                }

                if (player.hasEffect(Effect.HASTE)) {
                    breakTime *= 1 - (0.2 * (player.getEffect(Effect.HASTE).getAmplifier() + 1));
                }

                if (player.hasEffect(Effect.MINING_FATIGUE)) {
                    breakTime *= 1 - (0.3 * (player.getEffect(Effect.MINING_FATIGUE).getAmplifier() + 1));
                }

                Enchantment eff = item.getEnchantment(Enchantment.ID_EFFICIENCY);

                if (eff != null && eff.getLevel() > 0) {
                    breakTime *= 1 - (0.3 * eff.getLevel());
                }

                breakTime -= 0.15;

                Item[] eventDrops;

                eventDrops = target.getDrops(player, item);

                boolean fastBreak = (player.lastBreak + breakTime * 1000) > Long.sum(System.currentTimeMillis(), 1000);

                BlockBreakEvent ev = new BlockBreakEvent(player, target, null, item, eventDrops, player.isCreative(), fastBreak);

                Server.getInstance().getPluginManager().callEvent(ev);
                if (ev.isCancelled()) {
                    return;
                }

                drops = ev.getDrops();
                dropExp = ev.getDropExp();
            } else if (!target.isBreakable(item)) {
                return;
            } else if (item.hasEnchantment(Enchantment.ID_SILK_TOUCH)) {
                drops = new Item[]{target.toItem()};
            } else {
                drops = target.getDrops(null, item);
            }

            Vector3 above = new Vector3(target.x, target.y + 1, target.z);
            int bid = level.getBlockIdAt((int) above.x, (int) above.y, (int) above.z);
            if (bid == Item.FIRE || bid == Item.SOUL_FIRE) {
                level.setBlock(above, Block.get(BlockID.AIR), true);
            }

            Map<Integer, Player> players = level.getChunkPlayers((int) target.x >> 4, (int) target.z >> 4);
            level.addParticle(new DestroyBlockParticle(target.add(0.5), target), players.values());

            BlockEntity blockEntity = level.getBlockEntity(target);
            if (blockEntity != null) {
                blockEntity.onBreak();
                blockEntity.close();

                level.updateComparatorOutputLevel(target);
            }

            target.onBreak(item, player);

            item.useOn(target);

            if (level.gameRules.getBoolean(GameRule.DO_TILE_DROPS)) {
                if (player != null && drops.length != 0) {
                    if (player.isSurvival() || player.isAdventure()) {
                        level.dropExpOrb(vector.add(0.5, 0.5, 0.5), dropExp);
                    }
                }

                for (Item drop : drops) {
                    if (drop.getCount() > 0) {
                        level.dropItem(vector.add(0.5, 0.5, 0.5), drop);
                    }
                }
            }
        });
    }
}
