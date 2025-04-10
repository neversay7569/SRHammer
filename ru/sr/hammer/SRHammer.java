package ru.sr.hammer;

import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import ru.sr.hammer.species.Diamond_Hammer;
import ru.sr.hammer.species.Gold_Hammer;
import ru.sr.hammer.species.Iron_Hammer;
import ru.sr.hammer.species.Netherite_Hammer;

public class SRHammer extends PluginBase {

    @Override
    public void onEnable() {
        Item.registerCustomItem(Diamond_Hammer.class);
        Item.registerCustomItem(Netherite_Hammer.class);
        Item.registerCustomItem(Iron_Hammer.class);
        Item.registerCustomItem(Gold_Hammer.class);

        this.getServer().getPluginManager().registerEvents(new HammerListener(), this);

        this.getLogger().info("§l§bS§dR§f плагин на молоты успешно загружен!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§l§bS§dR§f плагин на молоты выключен!");
    }
}