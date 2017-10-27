package org.projpi.jetSuite.items.item;

import org.bukkit.enchantments.Enchantment;
import org.projpi.jetSuite.items.JetItems;

import java.util.logging.Level;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class ItemEnchant
{
    private Enchantment enchantment;
    private int level;

    public Enchantment getEnchantment()
    {
        return enchantment;
    }

    public int getLevel()
    {
        return level;
    }

    public ItemEnchant(String enchantment, int level)
    {
        this.enchantment = Enchantment.getByName(enchantment.toUpperCase());
        if(this.enchantment == null) JetItems.getPlugin().getLogger().log(Level.INFO, "Enchantment " + enchantment + " was not found, and will not be applied.");
        this.level = level;
    }
}
