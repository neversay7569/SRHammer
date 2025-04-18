package ru.sr.hammer.items.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;
import ru.sr.hammer.items.Hammer;

public class GoldHammer extends Hammer {
    private static final String spacenameId = "sr:gold_hammer";
    private static final String textureName = "gold_hammer";
    private static final String name = "Золотой молот";

    public GoldHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int scaleOffset() {
        return 32;
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

    @Override
    public int getMiningSpeed() {
        return 12;
    }
}