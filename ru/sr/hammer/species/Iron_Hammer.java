package ru.sr.hammer.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;

public class Iron_Hammer extends ItemCustom {
    private static final String spacenameId = "sr:iron_hammer";
    private static final String textureName = "iron_hammer";
    private static final String name = "Железный молот";

    public Iron_Hammer() {
        super(spacenameId, name, textureName);
    }

    public int scaleOffset() {
        return 32;
    }

    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
                .simpleBuilder(this, ItemCreativeCategory.EQUIPMENT)
                .creativeGroup(ItemCreativeGroup.PICKAXE)
                .allowOffHand(true)
                .handEquipped(true)
                .renderOffsets(RenderOffsets.scaleOffset(scaleOffset()))
                .build();
    }

    @Override
    public int getMaxDurability() {
        return 250;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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
    public int getMiningSpeed() {
        return 6; // Скорость добычи для алмазного молота
    }
}