package ru.sr.hammer.service;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import ru.sr.hammer.SRHammer;

import java.util.concurrent.CompletableFuture;

public interface BlockBreakerService {

    /**
     * Async block break for Hammer
     *
     * @param vector block coordinates
     * @param item   item-breaker
     * @param player player
     * @param level level
     * @return none
     */
    CompletableFuture<Void> breakBlock(Vector3 vector, Item item, Player player, Level level);

    static BlockBreakerService getInstance() {
        return SRHammer.getInstance().getBlockBreakerService();
    }
}
