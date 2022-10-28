package me.math3w.menuapi;

import me.math3w.menuapi.menu.Menu;
import me.math3w.menuapi.menu.MenuListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class MenuAPI {
    private static JavaPlugin plugin;
    private static HashMap<UUID, BukkitTask> refreshingMenus = new HashMap<>();


    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setup(JavaPlugin plugin) {
        MenuAPI.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new MenuListeners(), plugin);
    }

    public static HashMap<UUID, BukkitTask> getRefreshingMenus() {
        return refreshingMenus;
    }
}
