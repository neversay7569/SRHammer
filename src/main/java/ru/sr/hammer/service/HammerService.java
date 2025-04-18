package ru.sr.hammer.service;

import cn.nukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import ru.sr.hammer.items.Hammer;

import java.util.concurrent.CompletableFuture;

public interface HammerService {

    /**
     * Method to use scan event to hammer using
     *
     * @param event player interact event
     */
    boolean tryToBreak(@NotNull PlayerInteractEvent event);

    /**
     * If HammerService#tryToBreak(PlayerInteractEvent) is true, this method calls to break blocks
     *
     * @param event  player interact event
     * @param hammer hammer
     * @return none
     */
    CompletableFuture<Void> breakWithHammer(@NotNull PlayerInteractEvent event, Hammer hammer);
}
