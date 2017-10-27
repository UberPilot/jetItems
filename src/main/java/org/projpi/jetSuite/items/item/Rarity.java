package org.projpi.jetSuite.items.item;

import org.bukkit.ChatColor;

/**
 * Created by Hunter on 3/16/2017.
 */
public enum Rarity
{
    BLANK("", "", "", 1.0, 1),
    TRASH("Trash", "&8", "&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022", 0.0, 0),
    COMMON("Common", "&7", "&7\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022", 1.0, 1),
    UNCOMMON("Uncommon", "&f", "&7\u2022&f\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022", 1.5, 2),
    SCARCE("Scarce", "&b", "&7\u2022&f\u2022&b\u2022&8\u2022&8\u2022&8\u2022&8\u2022&8\u2022", 2.0, 3),
    RARE("Rare", "&3", "&7\u2022&f\u2022&b\u2022&3\u2022&8\u2022&8\u2022&8\u2022&8\u2022", 2.5, 4),
    EPIC("Epic", "&2", "&7\u2022&f\u2022&b\u2022&3\u2022&2\u2022&8\u2022&8\u2022&8\u2022", 4.0, 5),
    LEGENDARY("Legendary", "&a", "&7\u2022&f\u2022&b\u2022&3\u2022&2\u2022&a\u2022&8\u2022&8\u2022", 5.0, 6),
    RESPLENDENT("Resplendent", "&e", "&7\u2022&f\u2022&b\u2022&3\u2022&2\u2022&a\u2022&e\u2022&8\u2022", 7.5, 7),
    ANCIENT("Ancient", "&6", "&6\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022", 10.0, 8),
    SHADOW("Shadow", "&5", "&5\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022", 10.0, 8),
    RADIANT("Radiant", "&4", "&4\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022", 10.0, 8),
    CELESTIAL("Celestial", "&b", "&b\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022", 10.0, 8);

    private String name, lore, color;
    private double multiplier;
    private int level;
    Rarity(String name, String color, String lore, double multiplier, int level)
    {
        this.name = name;
        this.color = ChatColor.translateAlternateColorCodes('&', color);
        this.lore = ChatColor.translateAlternateColorCodes('&', lore);
        this.multiplier = multiplier;
        this.level = level;
    }

    public String getName()
    {
        return name;
    }

    public String getLore()
    {
        return lore;
    }

    public String getColor() {
        return color;
    }

    public double getMultiplier()
    {
        return multiplier;
    }

    public int getLevel() {
        return level;
    }
}
