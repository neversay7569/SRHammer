package ru.sr.hammer.factory.items;

import cn.nukkit.item.ItemTool;
import ru.sr.hammer.data.Hammer;

public class GoldHammer extends Hammer {
    private static final String spacenameId = "sr:gold_hammer";
    private static final String textureName = "gold_hammer";
    private static final String name = "Золотой молот";

    public GoldHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int getMaxDurability() {
        return 33;
    }

    @Override
    public int getAttackDamage() {
        return 4;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_GOLD;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }
}