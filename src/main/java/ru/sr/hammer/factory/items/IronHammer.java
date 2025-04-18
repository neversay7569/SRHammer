package ru.sr.hammer.factory.items;

import cn.nukkit.item.ItemTool;
import ru.sr.hammer.data.Hammer;

public class IronHammer extends Hammer {
    private static final String spacenameId = "sr:iron_hammer";
    private static final String textureName = "iron_hammer";
    private static final String name = "Железный молот";

    public IronHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int getMaxDurability() {
        return 250;
    }

    @Override
    public int getAttackDamage() {
        return 6;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_IRON;
    }

    @Override
    public int getEnchantAbility() {
        return 14;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }
}
