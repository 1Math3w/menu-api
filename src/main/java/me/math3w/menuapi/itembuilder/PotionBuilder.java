package me.math3w.menuapi.itembuilder;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class PotionBuilder {
    protected final Potion potion;

    /**
     * Construct a new PotionBuilder of the given type.
     *
     * @param type the potion type
     */
    public PotionBuilder(PotionType type) {
        potion = new Potion(type);
    }

    /**
     * Sets the level of this potion.
     *
     * @param level the new level of this potion
     * @return a reference to this object
     */
    public PotionBuilder setLevel(int level) {
        potion.setLevel(level);
        return this;
    }

    /**
     * Sets whether this potion has extended duration.
     *
     * @param extended whether the potion should have extended duration
     * @return a reference to this object
     */
    public PotionBuilder setExtended(boolean extended) {
        potion.setHasExtendedDuration(extended);
        return this;
    }

    /**
     * Sets whether this potion is a splash potion.
     *
     * @param splash whether this is a splash potion
     * @return a reference to this object
     */
    public PotionBuilder setSplash(boolean splash) {
        potion.setSplash(splash);
        return this;
    }

    @Override
    public PotionBuilder clone() {
        return new PotionBuilder(potion.getType()).setLevel(potion.getLevel()).setExtended(potion.hasExtendedDuration()).setSplash(potion.isSplash());
    }

    /**
     * Converts this potion to an {@link ItemStack}.
     *
     * @return the created ItemStack
     */
    public ItemStack toItemStack() {
        return potion.toItemStack(1);
    }

    /**
     * Converts this potion to an {@link ItemStack}.
     *
     * @return the created ItemStack
     */
    public ItemStack i() {
        return toItemStack();
    }
}
