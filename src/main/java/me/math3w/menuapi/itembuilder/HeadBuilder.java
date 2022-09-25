package me.math3w.menuapi.itembuilder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class HeadBuilder extends AbstractItemBuilder<HeadBuilder> {
    /**
     * Creates a new HeadBuilder instance
     *
     * @param itemStack item stack to create ItemBuilder over
     */
    public HeadBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Creates a new HeadBuilder instance
     *
     * @param texture the base64 value of the head texture to be set on skull
     */
    public HeadBuilder(String texture) {
        super(Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]) < 13
                ? new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3)
                : new ItemStack(Material.valueOf("PLAYER_HEAD")));

        SkullMeta headMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        try {
            Field field = headMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(headMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException exception) {
            exception.printStackTrace();
        }

        itemStack.setItemMeta(headMeta);
    }

    /**
     * Creates a new HeadBuilder instance
     *
     * @param owner the owner of the skull
     */
    public HeadBuilder(OfflinePlayer owner) {
        super(Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]) < 13
                ? new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3)
                : new ItemStack(Material.valueOf("PLAYER_HEAD")));

        SkullMeta headMeta = (SkullMeta) itemStack.getItemMeta();
        headMeta.setOwner(owner.getName());
        itemStack.setItemMeta(headMeta);
    }

    @Override
    public HeadBuilder getThis() {
        return this;
    }

    @Override
    public HeadBuilder clone() {
        return new HeadBuilder(itemStack);
    }
}
