package me.math3w.menuapi.menu;

import me.math3w.menuapi.MenuAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Menu implements InventoryHolder {
    private final Player player;
    private final InventoryType type;
    private MenuItem[] items;
    private Inventory inventory;

    /**
     * Creates a new Menu of type {@link InventoryType#CHEST} with the specified size.
     *
     * @param player a viewer of the menu
     * @param rows   an amount of rows that the menu should have
     */
    public Menu(Player player, int rows) {
        this.player = player;
        this.items = new MenuItem[rows * 9];
        this.type = InventoryType.CHEST;
    }

    /**
     * Creates a new Menu of specified type.
     * If you want the chest type menu use {@link #Menu(Player, int)}
     *
     * @param player a viewer of the menu
     * @param type   the type of inventory to create
     */
    public Menu(Player player, InventoryType type) {
        this.player = player;
        this.items = new MenuItem[type.getDefaultSize()];
        this.type = type;
    }

    private static ItemStack[] convertToItemStacks(MenuItem[] items) {
        return Arrays.stream(items)
                .map(item -> item == null ? new ItemStack(Material.AIR) : item.getItemStack())
                .toArray(ItemStack[]::new);
    }

    /**
     * Returns the menu name
     *
     * @return the menu name
     */
    public abstract String getMenuName();

    /**
     * Sets the content of the menu.
     * <p>use {@link #setItem(int, MenuItem)} or {@link #setItems(MenuItem, int...)} to set content of the menu</p>
     */
    protected abstract void setMenuItems();

    /**
     * Sets the item into the menu.
     * <p>The menu is not updated after using this method.</p>
     *
     * @param index a slot where the item should be placed
     * @param item  a MenuItem that is going to be placed on the index
     */
    protected void setItem(int index, MenuItem item) {
        items[index] = item;
    }

    /**
     * Sets the item on the multiple indexes.
     * <p>The menu is not updated after using this method.</p>
     *
     * @param item    a MenuItem that is going to be placed on the index
     * @param indexes an array of slots where the items should be placed
     */
    protected void setItems(MenuItem item, int... indexes) {
        for (int index : indexes) {
            setItem(index, item);
        }
    }

    /**
     * Sets the item on the multiple indexes.
     * <p>The menu is not updated after using this method.</p>
     *
     * @param item    a MenuItem that is going to be placed on the index
     * @param indexes an array of slots where the items should be placed
     */
    protected void setItems(MenuItem item, List<Integer> indexes) {
        for (int index : indexes) {
            setItem(index, item);
        }
    }

    /**
     * Executes item click actions on the clicked item
     *
     * @param event an event that triggers this method
     */
    public void handleMenu(InventoryClickEvent event) {
        if (items[event.getSlot()] == null) return;

        MenuItem item = items[event.getSlot()];
        item.doClickActions(event);

        event.setCancelled(true);
    }


    /**
     * Method that is executed when player opens this menu.
     */
    public void handleOpen() {

    }

    /**
     * Method that is executed when player closes this menu.
     *
     * @param event an event that triggers this method
     */
    public void handleClose(InventoryCloseEvent event) {

    }

    /**
     * Returns the menu as Bukkit inventory
     *
     * @return the inventory
     */
    @Override
    public Inventory getInventory() {
        inventory = type == InventoryType.CHEST
                ? Bukkit.createInventory(this, getSize(), getMenuName())
                : Bukkit.createInventory(this, type, getMenuName());
        update();

        return inventory;
    }

    /**
     * Opens the menu to the player
     */
    public void open() {
        player.openInventory(getInventory());

        if (updateTime() > 0) {
            if (MenuAPI.getRefreshingMenus().containsKey(getViewer().getUniqueId())) {
                getViewer().sendMessage("Stop updating");
                MenuAPI.getRefreshingMenus().get(getViewer().getUniqueId()).cancel();
                MenuAPI.getRefreshingMenus().remove(getViewer().getUniqueId());
            }

            MenuAPI.getRefreshingMenus().put(getViewer().getUniqueId(), Bukkit.getScheduler().runTaskTimer(MenuAPI.getPlugin(), this::update, updateTime(), updateTime()));
        }
    }

    /**
     * Updates the menu
     */
    public void update() {
        items = new MenuItem[this.items.length];
        setMenuItems();
        inventory.setContents(convertToItemStacks(items));
    }

    /**
     * Returns the content of the menu
     *
     * @return the content of the menu
     */
    public MenuItem[] getItems() {
        return items;
    }

    /**
     * Returns the type of the menu
     *
     * @return the {@link InventoryType} of the menu
     */
    public InventoryType getType() {
        return type;
    }

    /**
     * Returns the size of the menu.
     *
     * @return the size of the menu
     */
    public int getSize() {
        return items.length;
    }

    /**
     * Returns the Player who is viewing this inventory.
     *
     * @return the Player who is viewing this inventory
     */
    public Player getViewer() {
        return player;
    }

    /**
     * Returns the time that it should take to update the menu.
     * Set to 0 if the menu shouldn't be updated.
     *
     * @return time in ticks
     */
    public int updateTime() {
        return 0;
    }
}
