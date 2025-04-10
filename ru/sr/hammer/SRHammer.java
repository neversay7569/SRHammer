package ru.sr.hammer;

import cn.nukkit.plugin.PluginBase;
import ru.sr.hammer.HammerListener;

public class SRHammer extends PluginBase {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new HammerListener(), this);
        this.getLogger().info("§l§bS§dR§f плагин на молоты успешно загружен!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§l§bS§dR§f плагин на молоты выключен!");
    }
}