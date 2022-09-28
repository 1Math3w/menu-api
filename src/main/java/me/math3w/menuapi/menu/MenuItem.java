package me.math3w.menuapi.menu;

import me.math3w.menuapi.itembuilder.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItem extends AbstractItemBuilder<MenuItem> {
    private final List<ClickAction> clickActions = new ArrayList<>();

    /**
     * Creates a new MenuItem instance
     *
     * @param itemStack an item stack to create MenuItem over
     */
    public MenuItem(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Creates a new MenuItem instance
     *
     * @param type   material of the item
     * @param amount amount of material
     */
    public MenuItem(Material type, int amount) {
        super(type, amount);
    }


    /**
     * Creates a new MenuItem instance
     *
     * @param type material of the item
     */
    public MenuItem(Material type) {
        super(type);
    }

    /**
     * Adds an action that is going to be executed when the player clicks on the item.
     *
     * @param action an action that is executed when the player clicks on the item
     * @return a reference to this object
     */
    public MenuItem addClickAction(ClickAction action) {
        this.clickActions.add(action);
        return this;
    }


    /**
     * Adds an action that is going to be executed when the player left-clicks on the item.
     *
     * @param action an action that is executed when the player clicks on the item
     * @return a reference to this object
     */
    public MenuItem addLeftClickAction(ClickAction action) {
        this.clickActions.add((event) -> {
            if (!event.isLeftClick()) return;
            action.execute(event);
        });
        return this;
    }


    /**
     * Adds an action that is going to be executed when the player right-clicks on the item.
     *
     * @param action an action that is executed when the player clicks on the item
     * @return a reference to this object
     */
    public MenuItem addRightClickAction(ClickAction action) {
        this.clickActions.add((event) -> {
            if (!event.isRightClick()) return;
            action.execute(event);
        });
        return this;
    }

    /**
     * Retrieves click actions associated to this item.
     *
     * @return a list of click actions associated to this item
     */
    public List<ClickAction> getClickActions() {
        return clickActions;
    }

    /**
     * Adds multiple click actions to this item.
     *
     * @param actions click actions that are executed when the player clicks on the item
     * @return a reference to this object
     */
    public MenuItem addClickActions(ClickAction... actions) {
        return addClickActions(Arrays.asList(actions));
    }

    /**
     * Adds multiple click actions to this item.
     *
     * @param actions a list of click actions that are executed when the player clicks on the item
     * @return a reference to this object
     */
    public MenuItem addClickActions(List<ClickAction> actions) {
        this.clickActions.addAll(actions);
        return this;
    }

    @Override
    public MenuItem getThis() {
        return this;
    }

    @Override
    public MenuItem clone() {
        return new MenuItem(itemStack).addClickActions(getClickActions());
    }

    /**
     * Gets the item stack associated with this menu item.
     *
     * @return an item stack
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    public void doClickActions(InventoryClickEvent event) {
        clickActions.forEach(action -> action.execute(event));
    }
}
