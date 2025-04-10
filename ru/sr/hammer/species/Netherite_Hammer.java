package ru.sr.hammer.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;

public class Netherite_Hammer extends ItemCustom {
    private static final String spacenameId = "sr:netherite_hammer";
    private static final String textureName = "netherite_hammer";
    private static final String name = "Незеритовый молот";

    public Netherite_Hammer() {
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
        return 2031;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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
    public int getMiningSpeed() {
        return 15; // Скорость добычи для алмазного молота
    }
}