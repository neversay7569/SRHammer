package ru.sr.hammer.listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import ru.sr.hammer.data.Hammer;
import ru.sr.hammer.service.HammerService;

import cn.nukkit.event.EventHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HammerListener implements Listener {

    private HammerService hammerService;

    @EventHandler
    public void onPlayerInteract(BlockBreakEvent event) {
        if (hammerService.tryToBreak(event)) {
            hammerService.breakWithHammer(event, (Hammer) event.getItem());
        }
    }
}