package ru.sr.hammer.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;

public class Diamond_Hammer extends ItemCustom {
    private static final String spacenameId = "sr:diamond_hammer";
    private static final String textureName = "diamond_hammer";
    private static final String name = "Алмазный молот";

    public Diamond_Hammer() {
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
        return 1561;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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
    public int getMiningSpeed() {
        return 8; // Скорость добычи для алмазного молота
    }
}