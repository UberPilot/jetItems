package org.projpi.jetSuite.items.nms;

import net.minecraft.server.v1_8_R2.NBTTagCompound;
import net.minecraft.server.v1_8_R2.ItemStack;
import org.bukkit.craftbukkit.v1_8_R2.inventory.CraftItemStack;
import org.projpi.jetSuite.items.api.ItemCreator;
import org.projpi.jetSuite.items.item.*;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class ItemCreator8R2 implements ItemCreator
{
    public org.bukkit.inventory.ItemStack addNBT(Item item, org.bukkit.inventory.ItemStack inProgress)
    {
        //Apply the NBT flags.
        ItemStack itemStack = CraftItemStack.asNMSCopy(inProgress);
        NBTTagCompound compound = itemStack.getTag();
        if(item.getNbtID() != null)
        {
            if(!compound.hasKey("jetID"))
            {
                compound.setString("jetID", item.getNbtID());
            }
        }
        if(!compound.hasKey("jetRarity"))
        {
            compound.setInt("jetRarity", item.getRarity().getLevel());
        }
        if(!compound.hasKey("jetQuality"))
        {
            compound.setInt("jetQuality", item.getQuality().getLevel());
        }

        inProgress = CraftItemStack.asBukkitCopy(itemStack);

        //Finish.
        return inProgress;
    }

    public boolean isJetItem(org.bukkit.inventory.ItemStack itemStack)
    {
        return CraftItemStack.asNMSCopy(itemStack).getTag().hasKey("jetID");
    }
}

