package ru.sr.hammer.items.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;
import ru.sr.hammer.items.Hammer;

public class IronHammer extends Hammer {
    private static final String spacenameId = "sr:iron_hammer";
    private static final String textureName = "iron_hammer";
    private static final String name = "Железный молот";

    public IronHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int scaleOffset() {
        return 32;
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

    @Override
    public int getMiningSpeed() {
        return 6;
    }
}