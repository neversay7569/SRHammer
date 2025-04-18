package ru.sr.hammer.service;

import cn.nukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import ru.sr.hammer.data.Hammer;

import java.util.concurrent.CompletableFuture;

public interface HammerService {

    /**
     * Method to use scan event to hammer using
     *
     * @param event player interact event
     */
    boolean tryToBreak(@NotNull BlockBreakEvent event);

    /**
     * If HammerService#tryToBreak(PlayerInteractEvent) is true, this method calls to break blocks
     *
     * @param event  player interact event
     * @param hammer hammer
     * @return none
     */
    CompletableFuture<Void> breakWithHammer(@NotNull BlockBreakEvent event, Hammer hammer);
}
