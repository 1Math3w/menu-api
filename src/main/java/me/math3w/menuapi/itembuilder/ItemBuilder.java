package me.math3w.menuapi.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder extends AbstractItemBuilder<ItemBuilder> {
    /**
     * Creates a new ItemBuilder instance
     *
     * @param itemStack item stack to create ItemBuilder over
     */
    public ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Creates a new ItemBuilder instance
     *
     * @param type   material to create the ItemBuilder with
     * @param amount amount of material
     */
    public ItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    /**
     * Creates a new ItemBuilder instance with item amount 1
     *
     * @param type material to create the ItemBuilder with
     */
    public ItemBuilder(Material type) {
        this(type, 1);
    }

    public ItemBuilder getThis() {
        return this;
    }

    public ItemBuilder clone() {
        return new ItemBuilder(itemStack);
    }
}
