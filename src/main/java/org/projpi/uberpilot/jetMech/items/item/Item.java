package org.projpi.uberpilot.jetMech.items.item;

import minecraft.spigot.community.michel_0.api.AttributeModifier;
import minecraft.spigot.community.michel_0.api.ItemAttributes;
import minecraft.spigot.community.michel_0.api.Slot;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.projpi.uberpilot.jetMech.items.JetItems;
import org.projpi.uberpilot.jetMech.items.lang.Lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

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
    private double value;
    private TriValue triValue;

    private Item(Material material, String name, String[] lore, Rarity rarity, Quality quality, ItemType itemType, String nbtID, int damage, double value, boolean glow, boolean unbreakable, boolean hideAttributes, boolean hideUnbreakable, boolean hideEnchantments, ArrayList<ItemAttribute> attributes, ArrayList<ItemEnchant> enchants)
    {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.rarity = rarity;
        this.quality = quality;
        this.itemType = itemType;
        this.nbtID = nbtID;
        this.damage = damage;
        this.value = value;
        this.glow = glow;
        this.unbreakable = unbreakable;
        this.hideAttributes = hideAttributes;
        this.hideUnbreakable = hideUnbreakable;
        this.hideEnchantments = hideEnchantments;
        this.enchants = enchants;
        this.attributes = attributes;
    }

    private Item(Material material, String name, String[] lore, Rarity rarity, Quality quality, ItemType itemType, String nbtID, int damage, TriValue value, boolean glow, boolean unbreakable, boolean hideAttributes, boolean hideUnbreakable, boolean hideEnchantments, ArrayList<ItemAttribute> attributes, ArrayList<ItemEnchant> enchants)
    {
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.rarity = rarity;
        this.quality = quality;
        this.itemType = itemType;
        this.nbtID = nbtID;
        this.damage = damage;
        this.triValue = value;
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
        //TODO: Swap out hardcoded strings for Lang strings. Add a Lang system.

        //Create the item stack we'll be manipulating
        JetItems.getPlugin().getLogger().log(Level.INFO, "Creating ItemStack with Material: " + this.material + "  Amount: " + 1 + "  Damage: " + (short) this.damage);
        ItemStack is = new ItemStack(this.material, 1, (short) damage);

        //Attributes
        ItemAttributes itemAttributes = new ItemAttributes();

        for(ItemAttribute attribute : this.attributes)
        {
            itemAttributes.addModifier(new AttributeModifier(attribute.getAttribute(), attribute.getAttribute().toString(), attribute.getSlot(), attribute.getOperator(), attribute.getAmount(), UUID.randomUUID()));
        }

        is = itemAttributes.apply(is);

        //Get the manipulable meta
        ItemMeta meta = is.getItemMeta();


        //If there's a name, set the item name, taking Rarity into account.
        if(name != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', rarity.getColor() + name));


        //Convert the lore, make it so we can modify it.
        ArrayList<String> lore = new ArrayList<>();


        //If they aren't blank, set the rarity, type, and quality lines.
        JetItems.getPlugin().getLogger().log(Level.INFO, "Creating Lore with ItemType: " + this.itemType + "  Rarity: " + this.rarity + "  Quality: " + this.quality);
        //If an itemType other than None is set, add a line to the item. Add the rarity color and name if it has a rarity.
        if(itemType != ItemType.NONE && itemType != null) lore.add(Lang.TYPE.toString().replaceAll("%rc%", rarity.getColor() + "&o").replaceAll("%rarity%", rarity.getName() + " ").replaceAll("%type%", itemType.getName()));


        //If a rarity other than Blank is set, add a line to the item.
        if(rarity != Rarity.BLANK && rarity != null) lore.add(Lang.RARITY.toString().replaceAll("%rarity%", rarity.getLore()));


        //If a quality rather than Blank is set, add a line to the item.
        if(quality != Quality.BLANK && quality != null) lore.add(Lang.QUALITY.toString().replaceAll("%quality%", quality.getLore()));


        //Prioritize TriValue over Double value.
        JetItems.getPlugin().getLogger().log(Level.INFO, "Creating Item with Value: " + value + "  or TriValue: " + triValue);
        if(triValue != null)
        {
            //Only add the applicable amounts to the item lore.
            lore.add(Lang.VALUE3.toString().replaceAll("%gold%", (triValue.gold > 0) ? triValue.gold + " " : "" )
                    .replaceAll("%silver%", (triValue.silver > 0) ? triValue.silver + " ": "")
                    .replaceAll("%copper%", (triValue.copper > 0) ? triValue.copper + " " : ""));
            //lore.add("&7Value: " + ((triValue.gold > 0) ? "&e" + triValue.gold : "" ) + ((triValue.silver > 0) ? " &7" + triValue.silver : "") + ((triValue.copper > 0) ? "&6" + triValue.copper : "" ));
        }
        else if(value != 0)
        {
            //Add a value line without currency character.
            //TODO: Add a Lang string to allow for a currency character.
            lore.add(Lang.VALUE1.toString().replaceAll("%value%", String.valueOf(value)));
        }


        //Add all the other lore after the stats.
        lore.addAll(Arrays.asList(this.lore));


        //Loop through all the lore, and parse the color codes.
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', "&7&o" + lore.get(i)));
        }


        //Sets the unbreakable status.
        if(unbreakable)
        {
            meta.setUnbreakable(true);
        }

        //Checks if there are any enchantments. If not, and glow is enabled, add an invisible enchantment.
        if(enchants.size() < 1)
        {
            if(glow)
            {
                if (material != Material.BOW)
                {
                    meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                }
                else
                {
                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                }
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
        else
        {
            for(ItemEnchant enchant : enchants)
            {
                meta.addEnchant(enchant.getEnchantment(), enchant.getLevel(), true);
            }
        }


        //Set some flags.
        if(hideEnchantments)
        {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if(hideAttributes)
        {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if(hideUnbreakable)
        {
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }


        //Apply the lore.
        meta.setLore(lore);


        //Apply the meta.
        is.setItemMeta(meta);


        //Apply the NBT flags.
        is = addNBT(is);


        //Finish.
        return is;
    }

    private ItemStack addNBT(ItemStack is)
    {
        net.minecraft.server.v1_11_R1.ItemStack itemStack = CraftItemStack.asNMSCopy(is);
        NBTTagCompound compound = itemStack.getTag();
        if(nbtID != null)
        {
            if(!compound.hasKey("jetID"))
            {
                compound.setString("jetID", nbtID);
            }
        }
        if(!compound.hasKey("jetRarity"))
        {
            compound.setInt("jetRarity", rarity.getLevel());
        }
        if(!compound.hasKey("jetQuality"))
        {
            compound.setInt("jetQuality", quality.getLevel());
        }
        if(!compound.hasKey("jetValue"))
        {
            if(triValue != null)
            {
                compound.setString("jetValue", triValue.copper + "," + triValue.silver + "," + triValue.gold);
            }
            else if(value != 0)
            {
                compound.setDouble("jetValue", value);
            }
        }
        return CraftItemStack.asBukkitCopy(itemStack);
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
        private double value;
        private TriValue triValue;

        public ItemBuilder(Material material, String nbtID)
        {
            this.nbtID = nbtID.toLowerCase();
            this.material = material;
            this.damage = 0;
            this.rarity = Rarity.BLANK;
            this.quality = Quality.BLANK;
            this.itemType = ItemType.NONE;
            this.value = 0;
            this.glow = false;
            this.hideAttributes = false;
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

        public ItemBuilder setValue(double value)
        {
            this.value = value;
            return this;
        }

        public ItemBuilder setValue(TriValue value)
        {
            this.triValue = value;
            return this;
        }

        public Item build()
        {
            if(triValue != null)
            {
                return new Item(material, name, lore, rarity, quality, itemType, nbtID, damage, triValue, glow, unbreakable, hideAttributes, hideUnbreakable, hideEnchantments, attributes, enchants);
            }
            else {
                return new Item(material, name, lore, rarity, quality, itemType, nbtID, damage, value, glow, unbreakable, hideAttributes, hideUnbreakable, hideEnchantments, attributes, enchants);
            }
        }
    }
}
