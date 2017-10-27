package org.projpi.jetSuite.items;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.projpi.jetSuite.items.api.ItemCreator;
import org.projpi.jetSuite.items.command.Commander;
import org.projpi.jetSuite.items.item.ItemReader;
import org.projpi.jetSuite.items.item.ItemType;
import org.projpi.jetSuite.items.item.Quality;
import org.projpi.jetSuite.items.item.Rarity;
import org.projpi.jetSuite.items.lang.Lang;
import org.projpi.jetSuite.items.lang.LoadLang;
import org.projpi.jetSuite.items.nms.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Hunter on 3/16/2017.
 */
public class JetItems extends JavaPlugin {
    private HashMap<String, ItemStack> items;
    private File LANG_FILE;
    private YamlConfiguration LANG;
    private ItemReader reader;

    private static ItemCreator creator;

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (setupNMS()) {

            getLogger().info("Actionbar setup was successful!");
            getLogger().info("The plugin setup process is complete!");

        } else {

            getLogger().severe("Failed to setup JetItems!");
            getLogger().severe("Your server version is not compatible with this plugin!");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        //Initializes the commands.
        getCommand("jetitems").setExecutor(new Commander(this));

        //Loads the language file, and if it fails, disables the plugin because a fatal error has occurred.
        this.setEnabled(loadLang());

        //Loads the items
        loadItems();

        //Creates metrics
        MetricsLite metrics = new MetricsLite(this);
    }

    private boolean loadItems() {
        if (items == null) {
            items = new HashMap<>();
        } else {
            items.clear();
        }

        if (reader == null) {
            reader = new ItemReader();
        }
        Thread itemReader = new Thread(reader, "jetItems Item Reader");
        itemReader.start();
        try {
            itemReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        items = reader.getItems();
        return items != null;
    }

    private boolean setupNMS() {

        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }

        getLogger().info("Your server is running version " + version);

        if (version.equals("v1_8_R1")) {
            //server is running 1.8-1.8.1 so we need to use the 1.8 R1 NMS class
            creator = new ItemCreator8R1();
        } else if (version.equals("v1_8_R2")) {
            //etc.
            creator = new ItemCreator8R2();
        } else if (version.equals("v1_8_R3")) {
            creator = new ItemCreator8R3();
        } else if (version.equals("v1_9_R1")) {
            creator = new ItemCreator9R1();
        } else if (version.equals("v1_9_R2")) {
            creator = new ItemCreator9R2();
        } else if (version.equals("v1_10_R1")) {
            creator = new ItemCreator10R1();
        } else if (version.equals("v1_11_R1")) {
            creator = new ItemCreator11R1();
        }else if (version.equals("v1_12_R1")) {
            creator = new ItemCreator12R1();
        }
        // This will return true if the server version was compatible with one of our NMS classes
        // because if it is, our actionbar would not be null
        return creator != null;
    }

    private boolean loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for(Lang item:Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }

        Lang.setFile(conf);
        LANG = conf;
        LANG_FILE = lang;
        return true;
    }

    public static JetItems getPlugin() {
        return JavaPlugin.getPlugin(JetItems.class);
    }

    public File getConfigFolder() {
        return JavaPlugin.getPlugin(JetItems.class).getDataFolder();
    }

    public HashMap<String, ItemStack> getItems() {
        return items;
    }

    public ItemCreator getCreator() {
        return creator;
    }
}
