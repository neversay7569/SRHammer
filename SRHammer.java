package ru.sr.hammer;

import cn.nukkit.plugin.PluginBase;

public class SRHammer extends PluginBase {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new HammerListener(), this);
        this.getLogger().info("Hammer plugin enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Hammer plugin disabled!");
    }
}