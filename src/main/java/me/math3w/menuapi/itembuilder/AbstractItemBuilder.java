package me.math3w.menuapi.itembuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T>> {
    protected final ItemStack itemStack;

    public AbstractItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public AbstractItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    public AbstractItemBuilder(Material type) {
        this(type, 1);
    }

    public abstract T getThis();

    public abstract T clone();

    /**
     * Sets the type of this item
     * <p>
     * Note that in doing so you will reset the MaterialData for this stack
     * </p>
     *
     * @param type New type to set the items in this stack to
     * @return a reference to this object
     */
    public T setType(Material type) {
        itemStack.setType(type);
        return getThis();
    }

    /**
     * Sets the amount of items in this stack
     *
     * @param amount New amount of items in this stack
     * @return a reference to this object
     */
    public T setAmount(int amount) {
        itemStack.setAmount(amount);
        return getThis();
    }

    /**
     * Sets the durability of this item
     *
     * @param durability Durability of this item
     * @return a reference to this object
     */
    public T setDurability(short durability) {
        itemStack.setDurability(durability);
        return getThis();
    }

    /**
     * Sets the MaterialData for this stack of items
     *
     * @param data New MaterialData for this item
     * @return a reference to this object
     */
    public T setData(MaterialData data) {
        itemStack.setData(data);
        return getThis();
    }

    /**
     * Adds the specified {@link Enchantment} to this item stack.
     * <p>
     * If this item stack already contained the given enchantment (at any
     * level), it will be replaced.
     * </p>
     *
     * @param ench  Enchantment to add
     * @param level Level of the enchantment
     * @return a reference to this object
     * @throws IllegalArgumentException if enchantment null, or enchantment is
     *                                  not applicable
     */
    public T addEnchantment(Enchantment ench, int level) {
        itemStack.addEnchantment(ench, level);
        return getThis();
    }

    /**
     * Adds the specified {@link Enchantment} to this item stack.
     * <p>
     * If this item stack already contained the given enchantment (at any
     * level), it will be replaced.
     * </p>
     * <p>
     * This method is unsafe and will ignore level restrictions or item type.
     * Use at your own discretion.
     * </p>
     *
     * @param ench  Enchantment to add
     * @param level Level of the enchantment
     * @return a reference to this object
     */
    public T addUnsafeEnchantment(Enchantment ench, int level) {
        itemStack.addUnsafeEnchantment(ench, level);
        return getThis();
    }

    /**
     * Removes the specified {@link Enchantment} if it exists on this
     * ItemStack
     *
     * @param ench Enchantment to remove
     * @return a reference to this object
     */
    public T removeEnchantment(Enchantment ench) {
        itemStack.removeEnchantment(ench);
        return getThis();
    }

    /**
     * Sets the display name of the item
     *
     * @param name name to set it to
     * @return a reference to this object
     */
    public T setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Sets the lore for this item.
     * Removes lore when given null.
     *
     * @param lore the lore that will be set
     * @return a reference to this object
     */
    public T setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Sets the lore for this item.
     *
     * @param lore the lore that will be set
     * @return a reference to this object
     */
    public T setLore(String... lore) {
        setLore(Arrays.asList(lore));
        return getThis();
    }

    /**
     * Removes the first occurrence of the specified line, if it is present.
     * If this list does not contain the element, it is unchanged.
     *
     * @param line the lore to remove
     * @return a reference to this object
     */
    public T removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());

        lore.remove(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Removes the line from lore at the specified position.
     *
     * @param index the index of the lore line to be removed
     * @return a reference to this object
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public T removeLoreLine(int index) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());

        lore.remove(index);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Appends the specified line to the end of lore.
     *
     * @param line the lore line to add
     * @return a reference to this object
     */
    public T addLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.hasLore() ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();

        lore.add(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Inserts the specified line at the specified position in this list.
     *
     * @param line  the lore line to add
     * @param index index at which the specified element is to be inserted
     * @return a reference to this object
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    public T addLoreLine(int index, String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.hasLore() ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();

        lore.add(index, line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Replaces the line at the specified position in this lore with the specified element.
     *
     * @param index index of the element to replace
     * @param line  the lore line to replace current line at the specified position
     * @return a reference to this object
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public T setLoreLine(int index, String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.hasLore() ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();

        lore.set(index, line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Adds itemflags which should be ignored when rendering a ItemStack in the Client. This Method does silently ignore double set itemFlags.
     *
     * @param itemFlags the hide flags which shouldn't be rendered
     * @return a reference to this object
     */
    public T addItemFlags(ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Removes specific set of itemFlags. This tells the Client it should render it again. This Method does silently ignore double removed itemFlags.
     *
     * @param itemFlags the hide flags which should be removed
     * @return a reference to this object
     */
    public T removeItemFlags(ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeItemFlags(itemFlags);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Removes all itemFlags set to this item stack. This tells the Client it should render it again.
     *
     * @return a reference to this object
     */
    public T resetItemFlags() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.removeItemFlags(itemMeta.getItemFlags().toArray(new ItemFlag[0]));
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Sets the armor color of a leather armor piece.
     *
     * @param color the color to set it to
     * @return a reference to this object
     * @throws ClassCastException if the item stack is not leather armor piece
     */
    public T setLeatherArmorColor(Color color) {
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        itemMeta.setColor(color);
        itemStack.setItemMeta(itemMeta);
        return getThis();
    }

    /**
     * Makes item seem to be enchanted
     * <p>Adds unbreaking enchantment level 0 and adds the hide enchant item flag</p>
     *
     * @param glow whether the item should glow or not
     * @return a reference to this object
     */
    public T makeGlow(boolean glow) {
        return addEnchantment(Enchantment.DURABILITY, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }

    /**
     * Retrieves the item stack from the ItemBuilder.
     *
     * @return the created ItemStack
     */
    public ItemStack toItemStack() {
        return itemStack;
    }

    /**
     * Retrieves the item stack from the ItemBuilder.
     *
     * @return the created ItemStack
     */
    public ItemStack i() {
        return toItemStack();
    }
}
