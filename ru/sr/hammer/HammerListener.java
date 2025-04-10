package ru.sr.hammer;

import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import ru.sr.hammer.species.Diamond_Hammer;
import ru.sr.hammer.species.Gold_Hammer;
import ru.sr.hammer.species.Iron_Hammer;
import ru.sr.hammer.species.Netherite_Hammer;

public class HammerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() instanceof ItemCustom &&
                (event.getItem() instanceof Diamond_Hammer ||
                        event.getItem() instanceof Gold_Hammer ||
                        event.getItem() instanceof Iron_Hammer ||
                        event.getItem() instanceof Netherite_Hammer)) {
            Block clickedBlock = event.getBlock();
            if (clickedBlock != null) {
                Level level = event.getPlayer().getLevel();
                int miningSpeed;
                if (event.getItem() instanceof Diamond_Hammer) {
                    miningSpeed = ((Diamond_Hammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof Gold_Hammer) {
                    miningSpeed = ((Gold_Hammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof Iron_Hammer) {
                    miningSpeed = ((Iron_Hammer) event.getItem()).getMiningSpeed();
                } else if (event.getItem() instanceof Netherite_Hammer) {
                    miningSpeed = ((Netherite_Hammer) event.getItem()).getMiningSpeed();
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