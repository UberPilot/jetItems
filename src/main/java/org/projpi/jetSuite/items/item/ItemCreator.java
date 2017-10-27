package org.projpi.jetSuite.items.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.projpi.jetSuite.items.JetItems;
import org.projpi.jetSuite.items.attribAPI.AttributeModifier;
import org.projpi.jetSuite.items.attribAPI.ItemAttributes;
import org.projpi.jetSuite.items.lang.Lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class ItemCreator
{
    public static ItemStack toItemStack(Item item)
    {
        //TODO: Swap out hardcoded strings for Lang strings. Add a Lang system.

        //Create the item stack we'll be manipulating
        JetItems.getPlugin().getLogger().log(Level.INFO, "Creating ItemStack with Material: " + item.getMaterial() + "  Amount: " + 1 + "  Damage: " + (short) item.getDamage());
        ItemStack is = new ItemStack(item.getMaterial(), 1, (short) item.getDamage());


        if (item.getAttributes() != null)
        {
            //Attributes
            ItemAttributes itemAttributes = new ItemAttributes();

            for (ItemAttribute attribute : item.getAttributes())
            {
                itemAttributes.addModifier(new AttributeModifier(attribute.getAttribute(), attribute.getAttribute().toString(), attribute.getSlot(), attribute.getOperator(), attribute.getAmount(), UUID.randomUUID()));
            }

            is = itemAttributes.apply(is);
        }

        //Get the manipulable meta
        ItemMeta meta = is.getItemMeta();


        //If there's a name, set the item name, taking Rarity into account.
        if (item.getName() != null)
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', item.getRarity().getColor() + item.getName()));


        //Convert the lore, make it so we can modify it.
        ArrayList<String> lore = new ArrayList<>();


        //If they aren't blank, set the rarity, type, and quality lines.
        JetItems.getPlugin().getLogger().log(Level.INFO, "Creating Lore with ItemType: " + item.getItemType() + "  Rarity: " + item.getRarity() + "  Quality: " + item.getQuality());
        //If an itemType other than None is set, add a line to the item. Add the rarity color and name if it has a rarity.
        if (item.getItemType() != ItemType.NONE && item.getItemType() != null)
            lore.add(Lang.TYPE.toString().replaceAll("%rc%", item.getRarity().getColor() + "&o").replaceAll("%rarity%", item.getRarity().getName() + " ").replaceAll("%type%", item.getItemType().getName()));


        //If a rarity other than Blank is set, add a line to the item.
        if (item.getRarity() != Rarity.BLANK && item.getRarity() != null)
            lore.add(Lang.RARITY.toString().replaceAll("%rarity%", item.getRarity().getLore()));


        //If a quality rather than Blank is set, add a line to the item.
        if (item.getQuality() != Quality.BLANK && item.getQuality() != null)
            lore.add(Lang.QUALITY.toString().replaceAll("%quality%", item.getQuality().getLore()));


        //Add all the other lore after the stats.
        lore.addAll(Arrays.asList(item.getLore()));


        //Loop through all the lore, and parse the color codes.
        for (int i = 0; i < lore.size(); i++)
        {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', "&7&o" + lore.get(i)));
        }


        //Sets the unbreakable status.
        if (item.isUnbreakable())
        {
            meta.spigot().setUnbreakable(true);
        }

        //Checks if there are any enchantments. If not, and glow is enabled, add an invisible enchantment.
        if (item.getEnchants() != null)
        {
            if (item.getEnchants().size() >= 1)
            {
                for (ItemEnchant enchant : item.getEnchants())
                {
                    meta.addEnchant(enchant.getEnchantment(), enchant.getLevel(), true);
                }
            } else
            {
                if (item.isGlow())
                {
                    if (item.getMaterial() != Material.BOW)
                    {
                        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                    } else
                    {
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }
        } else
        {
            if (item.isGlow())
            {
                if (item.getMaterial() != Material.BOW)
                {
                    meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                } else
                {
                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                }
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }


        //Set some flags.
        if (item.isHideEnchantments())
        {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (item.isHideAttributes())
        {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        if (item.isHideUnbreakable())
        {
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }


        //Apply the lore.
        meta.setLore(lore);


        //Apply the meta.
        is.setItemMeta(meta);

        return JetItems.getPlugin().getCreator().addNBT(item, is);
    }
}
