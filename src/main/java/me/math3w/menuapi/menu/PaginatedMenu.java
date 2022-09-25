package me.math3w.menuapi.menu;

import me.math3w.menuapi.itembuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public abstract class PaginatedMenu extends Menu {
    private int page = 1;

    /**
     * Creates a new PaginatedMenu of type {@link InventoryType#CHEST} with the specified size.
     *
     * @param player a viewer of the menu
     * @param rows   an amount of rows that the menu should have
     */
    public PaginatedMenu(Player player, int rows) {
        super(player, rows);
    }

    /**
     * Creates a new PaginatedMenu of specified type.
     * If you want the chest type menu use {@link #PaginatedMenu(Player, int)}
     *
     * @param player a viewer of the menu
     * @param type   the type of inventory to create
     */
    public PaginatedMenu(Player player, InventoryType type) {
        super(player, type);
    }

    /**
     * Returns the slot of previous page item.
     *
     * @return the slot where previous page item is going to be placed
     */
    public abstract int getPreviousSlot();

    /**
     * Returns the slot of next page item.
     *
     * @return the slot where next page item is going to be placed
     */
    public abstract int getNextSlot();

    /**
     * Sets the content of the menu.
     * <p>use {@link #setItem(int, MenuItem)} or {@link #setItems(MenuItem, int...)} to set content of the menu</p>
     */
    @Override
    protected void setMenuItems() {
        setItem(getPreviousSlot(), getPreviousPageItem());
        setItem(getNextSlot(), getNextPageItem());
    }

    /**
     * Returns the next page item.
     *
     * @return the item that represents next page
     */
    protected MenuItem getNextPageItem() {
        return new MenuItem(Material.ARROW).setName(ChatColor.GREEN + "Next Page").addClickAction((event) -> setPage(page + 1));
    }

    /**
     * Returns the previous page item.
     *
     * @return the item that represents previous page
     */
    protected MenuItem getPreviousPageItem() {
        return new MenuItem(Material.ARROW).setName(ChatColor.GREEN + "Previous Page").addClickAction((event) -> setPage(page - 1));
    }

    /**
     * Returns the menu page.
     *
     * @return the current page of the menu
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page of the menu.
     * <p>the menu updates after setting new page</p>
     *
     * @param page the new page
     */
    public void setPage(int page) {
        this.page = Math.max(1, page);
        open();
    }
}
