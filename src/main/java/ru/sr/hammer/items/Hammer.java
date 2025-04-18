package ru.sr.hammer.items;

import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.network.protocol.types.inventory.creative.CreativeItemCategory;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public abstract class Hammer extends ItemCustomTool {

    /**
     * Hammer constructor (default ItemCustomTool constructor)
     *
     * @param id namespaceid
     * @param name in-game name
     * @param textureName in-game texture name
     */
    public Hammer(@NotNull String id, @Nullable String name, @NotNull String textureName) {
        super(id, name, textureName);
    }

    /**
     * Returns CustomItemDefinition for Hammer
     *
     * @return CustomItemDefinition
     */
    @Override
    public CustomItemDefinition getDefinition() {
        // We use new CreativeItemCategory class from protocol
        return CustomItemDefinition
                .toolBuilder(this, CreativeItemCategory.EQUIPMENT)
                .allowOffHand(false)
                .handEquipped(true)
                .renderOffsets(RenderOffsets.scaleOffset(scaleOffset()))
                .build();
    }

    /**
     * Overridable method to correctly register custom item
     * <p>
     * Default offset: 16
     * @return scaleOffset
     */
    public int scaleOffset() {
        return 0;
    }

    /**
     * Returns a mining speed of the hummer
     *
     * @return int mining speed
     */
    public int getMiningSpeed() {
        return 0;
    }
}
