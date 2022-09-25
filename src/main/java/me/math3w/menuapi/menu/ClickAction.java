package me.math3w.menuapi.menu;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickAction {
    void execute(InventoryClickEvent event);
}
