package org.projpi.jetSuite.items.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.projpi.jetSuite.items.JetItems;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class ItemReader implements Runnable
{
    private static final File dataFolder = JetItems.getPlugin().getConfigFolder();
    private static HashMap<String, Item> items;
    private HashMap<String, ItemStack> builtItems;
    private static JSONParser parser = new JSONParser();
    public static Item.ItemBuilder builder;

    public HashMap<String, ItemStack> getItems()
    {
        return builtItems;
    }

    public void run()
    {
        while(JetItems.getPlugin().getItems() == null);
        items = new HashMap<>();
        builtItems = new HashMap<>();
        readDir(dataFolder);
        for(Map.Entry<String, Item> entry : items.entrySet())
        {
            builtItems.put(entry.getKey(), entry.getValue().toItemStack());
        }
    }

    private static void readDir(File directory)
    {
        try {
            if(directory.isDirectory())
            {
                for (File file : directory.listFiles()) {
                    if(file.isFile())
                    {
                        if(file.getName().endsWith(".json"))
                        {
                            readItem(file);
                        }
                    }
                    else if(file.isDirectory())
                    {
                        readDir(file);
                    }
                }
            }
            else
            {

            }
        }
        catch (NullPointerException npe)
        {
            JetItems.getPlugin().getLogger().log(Level.INFO, "No files found in data folder.");
        }
    }

    private static void readItem(File file)
    {
        if(file.isFile())
        {
            try {
                JSONArray array = (JSONArray) parser.parse(new FileReader(file));
                item:
                for(Object o : array)
                {
                    JSONObject object = (JSONObject) o;
                    String material = ((String) object.get("material")).toUpperCase();
                    String id = (String) object.get("id");
                    JetItems.getPlugin().getLogger().log(Level.INFO, "Loading item " + id + " with material " + material);
                    builder = new Item.ItemBuilder(Material.getMaterial(material), id);


                    //Metadata Handling
                    if(object.containsKey("meta"))
                    {
                        //Clamps the meta to 16.
                        builder.setDamage(Math.toIntExact((long) object.get("meta")));
                    }


                    //Handling of potential Name sections
                    if(object.containsKey("name"))
                    {
                        builder.setName((String) object.get("name"));
                    }


                    //Handling of potential Rarity sections
                    if(object.containsKey("rarity"))
                    {
                        builder.setRarity(Rarity.valueOf(((String) object.get("rarity")).toUpperCase()));
                    }


                    //Handling of potential Quality sections
                    if(object.containsKey("quality"))
                    {
                        builder.setQuality(Quality.valueOf(((String) object.get("quality")).toUpperCase()));
                    }


                    //Handling of potential ItemType sections
                    if(object.containsKey("itemType"))
                    {
                        builder.setItemType(ItemType.valueOf(((String) object.get("itemType")).toUpperCase()));
                    }


                    //Simple boolean settings. Glow, Unbreakable, and hiding different parts
                    if(object.containsKey("glow"))
                    {
                        builder.setGlow((boolean) object.get("glow"));
                    }
                    if(object.containsKey("unbreakable"))
                    {
                        builder.setUnbreakable((boolean) object.get("unbreakable"));
                    }
                    if(object.containsKey("hideEnchantments"))
                    {
                        builder.setHideEnchantments((boolean) object.get("hideEnchantments"));
                    }
                    if(object.containsKey("hideAttributes"))
                    {
                        builder.setHideAttributes((boolean) object.get("hideAttributes"));
                    }
                    if(object.containsKey("hideUnbreakable"))
                    {
                        builder.setHideUnbreakable((boolean) object.get("hideUnbreakable"));
                    }


                    //Handling of potential Lore sections
                    if(object.containsKey("lore"))
                    {
                        JSONArray lore = (JSONArray) object.get("lore");
                        ArrayList<String> loreLines = new ArrayList<>();
                        for(Object line : lore)
                        {
                            loreLines.add((String) line);
                        }
                        builder.setLore(loreLines.toArray(new String[loreLines.size()]));
                    }

                    //Handling of possible Enchantment sections
                    if(object.containsKey("enchantments") || object.containsKey("enchants"))
                    {
                        ArrayList<ItemEnchant> enchants = new ArrayList<>();
                        JSONArray jsonEnch = (JSONArray) object.get("enchantments");
                        for(Object e : jsonEnch)
                        {
                            JSONObject enchant = (JSONObject) e;
                            if(enchant.containsKey("enchant") && enchant.containsKey("level"))
                            {
                                enchants.add(new ItemEnchant((String) enchant.get("enchant"), Math.toIntExact((long) enchant.get("level"))));
                            }
                        }
                        builder.setEnchants(enchants);
                    }


                    //Handling of possible Attribute sections
                    if(object.containsKey("attributes"))
                    {
                        ArrayList<ItemAttribute> attribs = new ArrayList<>();
                        JSONArray jsonAttrib = (JSONArray) object.get("attributes");
                        for(Object a : jsonAttrib)
                        {
                            JSONObject attrib = (JSONObject) a;
                            String attribute, slot = "MAIN_HAND";
                            int operation = 0;
                            double amount;
                            if(attrib.containsKey("attribute") && attrib.containsKey("amount"))
                            {
                                attribute = (String) attrib.get("attribute");
                                amount = (double) attrib.get("amount");
                            }
                            else continue;
                            if(attrib.containsKey("slot"))
                            {
                                slot = (String) attrib.get("slot");
                            }
                            if(attrib.containsKey("operation"))
                            {
                                operation = Math.toIntExact((long) attrib.get("operation"));
                            }
                            attribs.add(new ItemAttribute(attribute, slot, operation, amount));
                        }
                        builder.setAttributes(attribs);
                    }
                    items.put(id, builder.build());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
