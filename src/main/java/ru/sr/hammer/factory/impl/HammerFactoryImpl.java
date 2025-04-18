package ru.sr.hammer.factory.impl;

import cn.nukkit.item.Item;
import cn.nukkit.utils.OK;
import lombok.extern.slf4j.Slf4j;
import ru.sr.hammer.data.Hammer;
import ru.sr.hammer.factory.HammerFactory;
import ru.sr.hammer.factory.items.DiamondHammer;
import ru.sr.hammer.factory.items.GoldHammer;
import ru.sr.hammer.factory.items.IronHammer;
import ru.sr.hammer.factory.items.NetheriteHammer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HammerFactoryImpl implements HammerFactory {

    private final List<Class<? extends Hammer>> hammerClassMap = new ArrayList<>();

    /**
     * Loads all hammers and caching it
     */
    @Override
    public void loadHammers() {
        hammerClassMap.add(DiamondHammer.class);
        hammerClassMap.add(GoldHammer.class);
        hammerClassMap.add(IronHammer.class);
        hammerClassMap.add(NetheriteHammer.class);
    }

    /**
     * Method to register all hammers as ItemCustom
     */
    @Override
    public void registerHammers() {
        hammerClassMap.forEach(((aClass) -> {
            log.info("Register custom hammer: {}", aClass.getSimpleName());
            OK<?> successful = Item.registerCustomItem(aClass);

            if (!successful.ok()) {
                log.error("Error on register custom item: {}", String.valueOf(successful.getError()));
            }
        }));
    }
}
