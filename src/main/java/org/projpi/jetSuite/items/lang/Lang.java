package org.projpi.jetSuite.items.lang;

/**
 * Created by Hunter Henrichsen on 07-Dec-16.
 * (c) 2016 Hunter Henrichsen, all rights reserved.
 */
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An enum for requesting strings from the language file.
 * @author UberPilot
 */
public enum Lang {
    PLUGIN_PREFIX("plugin-prefix", "&8[&6&ojet&2Items&8]"),
    RARITY("rarity", "&7Rarity: %rarity%"),
    QUALITY("quality", "&7Quality: %quality%"),
    TYPE("type", "&f\u2192 &7&o%rc%%rarity%%type%"),
    VALUE1("value1", "&7Value: &6%value%"),
    VALUE3("value3", "&7Value: &e%gold%&7%silver%&6%copper%"),

    INVALID_QUANTITY("invalid-quantity", "&cInvalid quantity."),
    INVALID_ITEM("invalid-item", "&cInvalid item."),
    PERMISSION_DENIED("permission-denied", "&cSorry, you do not have permission to use that command."),

    BASE_DESC("base-desc", "&eBase Command"),
    BASE_COMMAND("base-command", "/jetitems"),
    PLUGIN_DESC("plugin-desc", "&eView author and version info of the plugin"),
    HELP_DESC("help-desc", "&eView the help message"),
    GIVE_DESC("give-desc", "&eGive [amount] [items] to yourself."),
    GIVE_OTHER_DESC("give-desc", "&eGive [amount] [items] to [player]."),
    LIST_DESC("list-desc", "&eList all loaded items."),
    HELPX_DESC("helpx-desc", "&eView the permission-help message");


    private String path;
    private String def;
    private static YamlConfiguration LANG;

    /**
     * lang enum constructor.
     * @param path The string path.
     * @param start The default string.
     */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * @param config The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        if(this == PLUGIN_PREFIX) return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    /**
     * Get the default value of the path.
     * @return The default value of the path.
     */
    public String getDefault() {
        return this.def;
    }

    /**
     * Get the path to the string.
     * @return The path to the string.
     */
    public String getPath() {
        return this.path;
    }
}
