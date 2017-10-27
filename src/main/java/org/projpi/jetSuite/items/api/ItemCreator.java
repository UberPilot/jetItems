package org.projpi.jetSuite.items.api;

import org.bukkit.inventory.ItemStack;
import org.projpi.jetSuite.items.item.Item;

/**
 * Description here.
 *
 * @author UberPilot
 */
public interface ItemCreator
{
    ItemStack addNBT(Item item, ItemStack inProgress);
    boolean isJetItem(org.bukkit.inventory.ItemStack itemStack);
}
