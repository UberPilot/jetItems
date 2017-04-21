package org.projpi.uberpilot.jetMech.items.item;

import org.bukkit.enchantments.Enchantment;

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
        this.level = level;
    }
}
