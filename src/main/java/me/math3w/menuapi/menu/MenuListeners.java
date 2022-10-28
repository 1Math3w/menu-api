package me.math3w.menuapi.menu;

import me.math3w.menuapi.MenuAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class MenuListeners implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Inventory inventory = event.getView().getTopInventory();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;

        InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        event.setCancelled(true);

        if (event.getClickedInventory() != inventory) return;

        Menu menu = (Menu) holder;
        menu.handleMenu(event);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMenuClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        InventoryHolder holder = event.getView().getTopInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        Menu menu = (Menu) holder;
        menu.handleClose(event);

        if (MenuAPI.getRefreshingMenus().containsKey(event.getPlayer().getUniqueId())) {
            MenuAPI.getRefreshingMenus().get(event.getPlayer().getUniqueId()).cancel();
            MenuAPI.getRefreshingMenus().remove(event.getPlayer().getUniqueId());
        }
    }
}
