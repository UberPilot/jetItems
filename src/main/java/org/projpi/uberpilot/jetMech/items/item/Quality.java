package org.projpi.uberpilot.jetMech.items.item;

import org.bukkit.ChatColor;

/**
 * Description here.
 *
 * @author UberPilot
 */
public enum Quality
{
    BLANK("", "&f", "", 1.0, 3),
    INFERIOR("Inferior", "&8", "&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606", 0.5, 0),
    CHEAP("Cheap", "&7", "&7\u2605&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606", 0.6, 1),
    LOW("Low", "&f", "&7\u2605&f\u2605&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606", 0.8, 2),
    AVERAGE("Average", "&b", "&7\u2605&f\u2605&b\u2605&8\u2606&8\u2606&8\u2606&8\u2606&8\u2606", 1.0, 3),
    ABOVE_AVERAGE("Above Average", "&3", "&7\u2605&f\u2605&b\u2605&3\u2605&8\u2606&8\u2606&8\u2606&8\u2606", 1.2, 4),
    FINE("Fine", "&2", "&7\u2605&f\u2605&b\u2605&3\u2605&2\u2605&8\u2606&8\u2606&8\u2606", 1.2, 5),
    SUPERB("Superb", "&a", "&7\u2605&f\u2605&b\u2605&3\u2605&2\u2605&a\u2605&8\u2606&8\u2606", 1.2, 6),
    EXCEPTIONAL("Exceptional", "&e", "&7\u2605&f\u2605&b\u2605&3\u2605&2\u2605&a\u2605&e\u2605&8\u2606", 1.2, 7),
    MASTERWORK("Masterwork", "&6", "&6\u2605\u2605\u2605\u2605\u2605\u2605\u2605\u2605", 10.0, 8);

    private String name, lore, color;
    private double multiplier;
    private int level;

    Quality(String name, String color, String lore, double multiplier, int level) {
        this.name = name;
        this.color = ChatColor.translateAlternateColorCodes('&', color);
        this.lore = ChatColor.translateAlternateColorCodes('&', lore);
        this.multiplier = multiplier;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public String getColor() {
        return color;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getLevel() {
        return level;
    }
}
