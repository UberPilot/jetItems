package org.projpi.uberpilot.jetMech.items;

import org.bstats.Metrics;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.projpi.uberpilot.jetMech.items.command.Commander;
import org.projpi.uberpilot.jetMech.items.item.Items;
import org.projpi.uberpilot.jetMech.items.lang.LoadLang;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Hunter on 3/16/2017.
 */
public class JetItems extends JavaPlugin
{
    public static HashMap<String, ItemStack> items;
    public static File LANG_FILE;
    public static YamlConfiguration LANG;

    @Override
    public void onLoad()
    {
        if(!getDataFolder().exists()) getDataFolder().mkdirs();
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
    }

    @Override
    public void onEnable()
    {
        //Initializes the commands.
        getCommand("jetitems").setExecutor(new Commander(this));

        //Loads the language file, and if it fails, disables the plugin because a fatal error has occurred.
        this.setEnabled(LoadLang.loadLang(this));

        //Loads the items
        items = Items.init();

        //Creates metrics
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SingleLineChart("items")
        {
            @Override
            public int getValue() {
                return items.size();
            }
        });
    }

    public static void reload()
    {
        JetItems.getPlugin().onEnable();
    }

    public static JetItems getPlugin()
    {
        return JavaPlugin.getPlugin(JetItems.class);
    }

    public static File getConfigFolder()
    {
        return JavaPlugin.getPlugin(JetItems.class).getDataFolder();
    }
}
