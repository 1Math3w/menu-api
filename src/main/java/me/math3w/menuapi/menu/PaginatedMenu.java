package me.math3w.menuapi.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public abstract class PaginatedMenu extends Menu {
    private int page = getFirstPage();

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
    protected abstract int getPreviousSlot();

    /**
     * Returns the slot of next page item.
     *
     * @return the slot where next page item is going to be placed
     */
    protected abstract int getNextSlot();

    /**
     * Sets the content of the menu.
     * <p>use {@link #setItem(int, MenuItem)} or {@link #setItems(MenuItem, int...)} to set content of the menu</p>
     */
    @Override
    protected void setMenuItems() {
        if (hasPreviousPage() || displayItemsWhenPageNotAvailable()) {
            setItem(getPreviousSlot(), getPreviousPageItem());
        }
        if (hasNextPage() || displayItemsWhenPageNotAvailable()) {
            setItem(getNextSlot(), getNextPageItem());
        }
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
        this.page = page < getFirstPage() ? getFirstPage() : Math.min(page, getLastPage());
        open();
    }

    /**
     * Returns the first page.
     *
     * @return the first page of the menu
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * Returns the last page.
     *
     * @return the last page of the menu
     */
    public int getLastPage() {
        return Integer.MAX_VALUE;
    }

    /**
     * Returns whether the menu has next page.
     *
     * @return whether the menu has next page
     */
    public boolean hasNextPage() {
        return getPage() < getLastPage();
    }

    /**
     * Returns whether the menu has previous page.
     *
     * @return whether the menu has previous page
     */
    public boolean hasPreviousPage() {
        return getPage() > getFirstPage();
    }

    /**
     * Should it display the previous or next page item when the page is not available.
     * <p>Override the {@link PaginatedMenu#getLastPage()} method to work this properly when set to false</p>
     *
     * @return whether it should display the next/previous page item when the page doesn't exist
     */
    protected boolean displayItemsWhenPageNotAvailable() {
        return false;
    }
}
