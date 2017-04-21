package org.projpi.uberpilot.jetMech.items.item;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Initializes all of the items and saves them as ItemStacks.
 *
 * @author UberPilot
 */
public class Items {

    public static HashMap<String, ItemStack> init()
    {
        HashMap<String, ItemStack> items = new HashMap<>();
        HashMap<String, Item> read = ItemReader.readItems();
        for(Map.Entry<String, Item> itemEntry : read.entrySet())
        {
            items.put(itemEntry.getKey(), itemEntry.getValue().toItemStack());
        }
        return items;
    }
}
