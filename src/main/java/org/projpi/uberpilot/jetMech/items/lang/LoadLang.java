package org.projpi.uberpilot.jetMech.items.lang;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.projpi.uberpilot.jetMech.items.JetItems;

import java.io.File;

/**
 * Created by Hunter Henrichsen on 31-Dec-16.
 * (c) 2016 Hunter Henrichsen, all rights reserved.
 */
public class LoadLang
{

    public static boolean loadLang(JavaPlugin instance) {
        File lang = new File(instance.getDataFolder(), "lang.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for(Lang item:Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }

        Lang.setFile(conf);
        JetItems.LANG = conf;
        JetItems.LANG_FILE = lang;
        return true;
    }
}
