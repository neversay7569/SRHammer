package ru.sr.hammer;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import ru.sr.hammer.factory.HammerFactory;
import ru.sr.hammer.factory.impl.HammerFactoryImpl;
import ru.sr.hammer.listener.HammerListener;
import ru.sr.hammer.service.BlockBreakerService;
import ru.sr.hammer.service.HammerService;
import ru.sr.hammer.service.impl.BlockBreakerServiceImpl;
import ru.sr.hammer.service.impl.HammerServiceImpl;

public class SRHammer extends PluginBase {

    @Getter
    public HammerService hammerService;
    @Getter
    public HammerFactory hammerFactory;
    @Getter
    private BlockBreakerService blockBreakerService;
    @Getter
    private static SRHammer instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.hammerFactory = new HammerFactoryImpl();

        // Load and cache hammer classes
        this.hammerFactory.loadHammers();

        // Register hammers as custom items
        this.hammerFactory.registerHammers();

        // Initialize hammer service
        this.hammerService = new HammerServiceImpl();

        // Initialize block break service
        this.blockBreakerService = new BlockBreakerServiceImpl();

        this.getServer().getPluginManager().registerEvents(new HammerListener(hammerService), this);

        this.getLogger().info("§l§bS§dR§f плагин на молоты успешно загружен!");
    }
}