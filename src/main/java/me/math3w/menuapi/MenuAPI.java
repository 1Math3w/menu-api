package me.math3w.menuapi;

import me.math3w.menuapi.menu.MenuListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuAPI {
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setup(JavaPlugin plugin) {
        MenuAPI.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new MenuListeners(), plugin);
    }
}
