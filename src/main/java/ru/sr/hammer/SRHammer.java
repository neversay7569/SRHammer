package ru.sr.hammer;

import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import ru.sr.hammer.items.species.DiamondHammer;
import ru.sr.hammer.items.species.GoldHammer;
import ru.sr.hammer.items.species.IronHammer;
import ru.sr.hammer.items.species.NetheriteHammer;

public class SRHammer extends PluginBase {

    @Override
    public void onEnable() {
        Item.registerCustomItem(DiamondHammer.class);
        Item.registerCustomItem(NetheriteHammer.class);
        Item.registerCustomItem(IronHammer.class);
        Item.registerCustomItem(GoldHammer.class);

        this.getServer().getPluginManager().registerEvents(new HammerListener(), this);

        this.getLogger().info("§l§bS§dR§f плагин на молоты успешно загружен!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§l§bS§dR§f плагин на молоты выключен!");
    }
}