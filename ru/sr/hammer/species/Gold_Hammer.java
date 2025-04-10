package ru.sr.hammer.species;

import cn.nukkit.item.ItemTool;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustom;
import cn.nukkit.item.customitem.data.ItemCreativeCategory;
import cn.nukkit.item.customitem.data.ItemCreativeGroup;
import cn.nukkit.item.customitem.data.RenderOffsets;

public class Gold_Hammer extends ItemCustom {
    private static final String spacenameId = "sr:gold_hammer";
    private static final String textureName = "gold_hammer";
    private static final String name = "Золотой молот";

    public Gold_Hammer() {
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
        return 33;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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