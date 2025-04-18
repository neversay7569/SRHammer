package ru.sr.hammer.items.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;
import ru.sr.hammer.items.Hammer;

public class NetheriteHammer extends Hammer {
    private static final String spacenameId = "sr:netherite_hammer";
    private static final String textureName = "netherite_hammer";
    private static final String name = "Незеритовый молот";

    public NetheriteHammer() {
        super(spacenameId, name, textureName);
    }

    @Override
    public int scaleOffset() {
        return 32;
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

    @Override
    public int getMiningSpeed() {
        return 15;
    }
}