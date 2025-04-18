package ru.sr.hammer.factory.items;

import cn.nukkit.item.ItemTool;
import ru.sr.hammer.data.Hammer;

public class DiamondHammer extends Hammer {
    private static final String spacenameId = "sr:diamond_hammer";
    private static final String textureName = "diamond_hammer";
    private static final String name = "Алмазный молот";

    public DiamondHammer() {
        super(spacenameId, name, textureName);
    }


    @Override
    public int getMaxDurability() {
        return 1561;
    }

    @Override
    public int getAttackDamage() {
        return 8;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_DIAMOND;
    }

    @Override
    public int getEnchantAbility() {
        return 10;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }

    @Override
    public int getMiningSpeed() {
        return 8;
    }
}
