package ru.sr.hammer.data;

import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.ItemCustomTool;
import cn.nukkit.item.customitem.data.RenderOffsets;
import cn.nukkit.network.protocol.types.inventory.creative.CreativeItemCategory;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * The class that implements the basis for the hammer
 */
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
                .renderOffsets(RenderOffsets.scaleOffset(this.scaleOffset()))
                .build();
    }

    /**
     * Overridable method to correctly register custom item
     * <p>
     * Default offset: 32
     * @return scaleOffset
     */
    public int scaleOffset() {
        return 32;
    }

    /**
     * Returns a mining speed of the hummer
     *
     * @return int mining speed
     */
    public int getMiningSpeed() {
        return 0;
    }

    /**
     * Returns use cooldown in milliseconds
     * <p>
     * You can use custom cooldown settings here like:
     * <p>
     * <code>TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS) - 5 seconds cooldown</code>
     * <p>
     * Dont forget to add overrided method in the class
     */
    public long getCooldown() {
        return TimeUnit.MILLISECONDS.convert(500, TimeUnit.MILLISECONDS);
    }
}
