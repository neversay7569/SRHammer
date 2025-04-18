package ru.sr.hammer;

import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import ru.sr.hammer.items.species.DiamondHammer;
import ru.sr.hammer.items.species.GoldHammer;
import ru.sr.hammer.items.species.IronHammer;
import ru.sr.hammer.items.species.NetheriteHammer;

public class HammerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() instanceof ItemCustom &&
                (event.getItem() instanceof DiamondHammer ||
                        event.getItem() instanceof GoldHammer ||
                        event.getItem() instanceof IronHammer ||
                        event.getItem() instanceof NetheriteHammer)) {
            Block clickedBlock = event.getBlock();
            if (clickedBlock != null) {
                Level level = event.getPlayer().getLevel();
                int miningSpeed;
                if (event.getItem() instanceof DiamondHammer) {
                    miningSpeed = ((DiamondHammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof GoldHammer) {
                    miningSpeed = ((GoldHammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof IronHammer) {
                    miningSpeed = ((IronHammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof NetheriteHammer) {
                    miningSpeed = ((NetheriteHammer) event.getItem()).getMiningSpeed();
                } else {
                    miningSpeed = 1;
                }
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            Vector3 blockPos = new Vector3(clickedBlock.x + x, clickedBlock.y + y, clickedBlock.z + z);
                            Block block = level.getBlock(blockPos);
                            if (block.getId() != 0) {
                                BlockBreakEvent breakEvent = new BlockBreakEvent(event.getPlayer(), block, event.getItem(), block.getDrops(event.getItem()), false, false);
                                level.getServer().getPluginManager().callEvent(breakEvent);
                                if (!breakEvent.isCancelled()) {
                                    block.onBreak(event.getPlayer().getInventory().getItemInHand());
                                }
                                try {
                                    Thread.sleep(miningSpeed);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}