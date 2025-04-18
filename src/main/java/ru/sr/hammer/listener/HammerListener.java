package ru.sr.hammer.listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import ru.sr.hammer.data.Hammer;
import ru.sr.hammer.service.HammerService;

import lombok.AllArgsConstructor;
import cn.nukkit.event.EventHandler;

@AllArgsConstructor
public class HammerListener implements Listener {

    private HammerService hammerService;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (hammerService.tryToBreak(event)) {
            hammerService.breakWithHammer(event, (Hammer) event.getItem());
        }
    }
}