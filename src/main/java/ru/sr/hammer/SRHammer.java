package ru.sr.hammer;

import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import ru.sr.hammer.items.species.DiamondHammer;
import ru.sr.hammer.items.species.GoldHammer;
import ru.sr.hammer.items.species.IronHammer;
import ru.sr.hammer.items.species.NetheriteHammer;
import ru.sr.hammer.service.HammerService;
import ru.sr.hammer.service.impl.HammerServiceImpl;

public class SRHammer extends PluginBase {

    @Getter
    public HammerService hammerService;
    @Getter
    private static SRHammer instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Item.registerCustomItem(DiamondHammer.class);
        Item.registerCustomItem(NetheriteHammer.class);
        Item.registerCustomItem(IronHammer.class);
        Item.registerCustomItem(GoldHammer.class);

        this.getServer().getPluginManager().registerEvents(new HammerListener(), this);

        this.hammerService = new HammerServiceImpl();

        this.getLogger().info("§l§bS§dR§f плагин на молоты успешно загружен!");
    }
}