package ru.sr.hammer.factory.items;

import cn.nukkit.item.ItemTool;
import ru.sr.hammer.data.Hammer;

public class NetheriteHammer extends Hammer {
    private static final String spacenameId = "sr:netherite_hammer";
    private static final String textureName = "netherite_hammer";
    private static final String name = "Незеритовый молот";

    public NetheriteHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int getMaxDurability() {
        return 2031;
    }

    @Override
    public int getAttackDamage() {
        return 10;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_NETHERITE;
    }

    @Override
    public int getEnchantAbility() {
        return 15;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }
}