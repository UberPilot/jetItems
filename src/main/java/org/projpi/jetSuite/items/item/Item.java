package org.projpi.jetSuite.items.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.projpi.jetSuite.items.JetItems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Hunter on 3/16/2017.
 */
public class Item
{
    private Material material;
    private int damage;
    private String name;
    private String nbtID;
    private String[] lore;
    private Rarity rarity;
    private Quality quality;
    private ItemType itemType;
    private ArrayList<ItemAttribute> attributes;
    private ArrayList<ItemEnchant> enchants;
    private boolean glow;
    private boolean unbreakable;
    private boolean hideAttributes;
    private boolean hideUnbreakable;
    private boolean hideEnchantments;

    private Item(Material material, String name, String[] lore, Rarity rarity, Quality quality, ItemType itemType, String nbtID, int damage, boolean glow, boolean unbreakable, boolean hideAttributes, boolean hideUnbreakable, boolean hideEnchantments, ArrayList<ItemAttribute> attributes, ArrayList<ItemEnchant> enchants)
    {
        //JetItems.getPlugin().getLogger().log(Level.INFO, "Created item:" + this.toString());
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.rarity = rarity;
        this.quality = quality;
        this.itemType = itemType;
        this.nbtID = nbtID;
        this.damage = damage;
        this.glow = glow;
        this.unbreakable = unbreakable;
        this.hideAttributes = hideAttributes;
        this.hideUnbreakable = hideUnbreakable;
        this.hideEnchantments = hideEnchantments;
        this.enchants = enchants;
        this.attributes = attributes;
    }

    public ItemStack toItemStack()
    {
        return ItemCreator.toItemStack(this);
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "material=" + material +
                ", damage=" + damage +
                ", name='" + name + '\'' +
                ", nbtID='" + nbtID + '\'' +
                ", lore=" + Arrays.toString(lore) +
                ", rarity=" + rarity +
                ", quality=" + quality +
                ", itemType=" + itemType +
                ", attributes=" + attributes +
                ", enchants=" + enchants +
                ", glow=" + glow +
                ", unbreakable=" + unbreakable +
                ", hideAttributes=" + hideAttributes +
                ", hideUnbreakable=" + hideUnbreakable +
                ", hideEnchantments=" + hideEnchantments +
                '}';
    }

    public static class ItemBuilder
    {
        //Builder pattern for Item.
        private Material material;
        private String name;
        private String nbtID;
        private String[] lore;
        private Rarity rarity;
        private Quality quality;
        private ItemType itemType;
        private ArrayList<ItemAttribute> attributes;
        private ArrayList<ItemEnchant> enchants;
        private boolean glow;
        private boolean unbreakable;
        private boolean hideAttributes;
        private boolean hideUnbreakable;
        private boolean hideEnchantments;
        private int damage;

        public ItemBuilder(Material material, String nbtID)
        {
            this.nbtID = nbtID.toLowerCase();
            this.material = material;
            this.damage = 0;
            this.rarity = Rarity.BLANK;
            this.quality = Quality.BLANK;
            this.itemType = ItemType.NONE;
            this.glow = false;
            this.unbreakable = false;
            this.hideAttributes = false;
            this.hideUnbreakable = false;
            this.hideEnchantments = false;
        }

        public ItemBuilder setAttributes(ArrayList<ItemAttribute> attributes)
        {
            this.attributes = attributes;
            return this;
        }

        public ItemBuilder setEnchants(ArrayList<ItemEnchant> enchants)
        {
            this.enchants = enchants;
            return this;
        }

        public ItemBuilder setGlow(boolean glow)
        {
            this.glow = glow;
            return this;
        }

        public ItemBuilder setUnbreakable(boolean unbreakable)
        {
            this.unbreakable = unbreakable;
            return this;
        }

        public ItemBuilder setHideAttributes(boolean hideAttributes)
        {
            this.hideAttributes = hideAttributes;
            return this;
        }

        public ItemBuilder setHideEnchantments(boolean hideEnchantments)
        {
            this.hideEnchantments = hideEnchantments;
            return this;
        }

        public ItemBuilder setHideUnbreakable(boolean hideUnbreakable)
        {
            this.hideUnbreakable = hideUnbreakable;
            return this;
        }

        public ItemBuilder setName(String name)
        {
            this.name = name;
            return this;
        }

        public ItemBuilder setLore(String[] lore)
        {
            this.lore = lore;
            return this;
        }

        public ItemBuilder setRarity(Rarity rarity)
        {
            this.rarity = rarity;
            return this;
        }

        public ItemBuilder setQuality(Quality quality)
        {
            this.quality = quality;
            return this;
        }

        public ItemBuilder setItemType(ItemType itemType)
        {
            this.itemType = itemType;
            return this;
        }

        public ItemBuilder setDamage(int damage)
        {
            this.damage = Math.max(0, Math.min(16, damage));
            return this;
        }

        public Item build()
        {
            return new Item(material, name, lore, rarity, quality, itemType, nbtID, damage, glow, unbreakable, hideAttributes, hideUnbreakable, hideEnchantments, attributes, enchants);
        }
    }

    //Getters and such
    public Material getMaterial()
    {
        return material;
    }

    public int getDamage()
    {
        return damage;
    }

    public String getName()
    {
        return name;
    }

    public String getNbtID()
    {
        return nbtID;
    }

    public String[] getLore()
    {
        return lore;
    }

    public Rarity getRarity()
    {
        return rarity;
    }

    public Quality getQuality()
    {
        return quality;
    }

    public ItemType getItemType()
    {
        return itemType;
    }

    public ArrayList<ItemAttribute> getAttributes()
    {
        return attributes;
    }

    public ArrayList<ItemEnchant> getEnchants()
    {
        return enchants;
    }

    public boolean isGlow()
    {
        return glow;
    }

    public boolean isUnbreakable()
    {
        return unbreakable;
    }

    public boolean isHideAttributes()
    {
        return hideAttributes;
    }

    public boolean isHideUnbreakable()
    {
        return hideUnbreakable;
    }

    public boolean isHideEnchantments()
    {
        return hideEnchantments;
    }
}
