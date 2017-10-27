package org.projpi.jetSuite.items.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.projpi.jetSuite.items.JetItems;
import org.projpi.jetSuite.items.lang.Lang;

import java.util.Map;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class Commander implements CommandExecutor
{
    private JetItems instance;

    public Commander(JetItems instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(args.length >= 1)
        {
            if(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("g"))
            {
                if(args.length == 3)
                {
                    if (sender.hasPermission("jetItems.give.self"))
                    {
                        if (sender instanceof Player)
                        {
                            if (JetItems.getPlugin().getItems().containsKey(args[1].toLowerCase()))
                            {
                                try
                                {
                                    ItemStack is = JetItems.getPlugin().getItems().get(args[1].toLowerCase());
                                    is.setAmount(Integer.valueOf(args[2]));
                                    ((Player) sender).getInventory().addItem(is);
                                } catch (Exception e)
                                {
                                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.INVALID_QUANTITY.toString());
                                }
                            } else
                            {
                                sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.INVALID_ITEM.toString());
                            }
                        }
                    } else
                    {
                        sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
                    }
                }
                else if(args.length == 4)
                {
                    if (sender.hasPermission("jetItems.give.other"))
                    {
                        if (JetItems.getPlugin().getItems().containsKey(args[1].toLowerCase()))
                        {
                            try
                            {
                                Player p = Bukkit.getPlayer(args[3]);
                                ItemStack is = JetItems.getPlugin().getItems().get(args[1].toLowerCase());
                                is.setAmount(Integer.valueOf(args[2]));
                                p.getInventory().addItem(is);
                            } catch (Exception e)
                            {
                                sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.INVALID_QUANTITY.toString());
                            }
                        }
                        else
                        {
                            sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.INVALID_ITEM.toString());
                        }
                    }
                }
                else
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
                }
            }
            else if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h"))
            {
                if(sender.hasPermission("jetItems.help"))
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString());
                    sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + ChatColor.YELLOW + " Base Command");
                    if (sender.hasPermission("jetItems.give.self")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + " give [item] [amount] " + Lang.GIVE_DESC.toString());
                    if (sender.hasPermission("jetItems.give.other")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + " give [item] [amount] [player] " + Lang.GIVE_OTHER_DESC.toString());
                    if (sender.hasPermission("jetItems.list")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + " list " + Lang.LIST_DESC.toString());
                    if (sender.hasPermission("jetInfo.help")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + " help " + Lang.HELP_DESC.toString());
                    if (sender.hasPermission("jetInfo.helpx")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + " helpx " + Lang.HELPX_DESC.toString());
                    if (sender.hasPermission("jetInfo.info")) sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + Lang.PLUGIN_DESC.toString());
                }
                else
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
                }
            }
            else if(args[0].equalsIgnoreCase("helpx") || args[0].equalsIgnoreCase("hx"))
            {
                if(sender.hasPermission("jetItems.helpx"))
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString());
                    sender.sendMessage(ChatColor.GRAY + Lang.BASE_COMMAND.toString() + ChatColor.YELLOW + " Base Command");
                    sender.sendMessage((sender.hasPermission("jetItems.give.self")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " give [item] [amount] " + Lang.GIVE_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " give [item] [amount] " + Lang.GIVE_DESC.toString());
                    sender.sendMessage((sender.hasPermission("jetItems.give.other")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " give [item] [amount] [player] " + Lang.GIVE_OTHER_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " give [item] [amount] [player] " + Lang.GIVE_OTHER_DESC.toString());
                    sender.sendMessage((sender.hasPermission("jetItems.list")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " list " + Lang.LIST_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " list " + Lang.LIST_DESC.toString());
                    sender.sendMessage((sender.hasPermission("jetItems.help")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " help " + Lang.HELP_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " help " + Lang.HELP_DESC.toString());
                    sender.sendMessage((sender.hasPermission("jetItems.helpx")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " helpx " + Lang.HELPX_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " helpx " + Lang.HELPX_DESC.toString());
                    sender.sendMessage((sender.hasPermission("jetItems.info")) ? ChatColor.GREEN + Lang.BASE_COMMAND.toString() + " " + Lang.PLUGIN_DESC.toString() : ChatColor.RED + Lang.BASE_COMMAND.toString() + " " +  Lang.PLUGIN_DESC.toString());
                }
                else
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
                }
            }
            else if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l"))
            {
                if(sender.hasPermission("jetItems.list"))
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + "Loaded Items:");
                    for(Map.Entry<String, ItemStack> entry : JetItems.getPlugin().getItems().entrySet())
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7- " + entry.getKey()));
                    }
                }
                else
                {
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
                }
            }
        }
        else
        {
            if(sender.hasPermission("jetItems.info"))
            {
                    PluginDescriptionFile pdf = JetItems.getPlugin().getDescription();
                    sender.sendMessage(Lang.PLUGIN_PREFIX.toString());
                    sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + pdf.getDescription());
                    sender.sendMessage(ChatColor.GRAY + "Author: " + ChatColor.GOLD + pdf.getAuthors());
                    sender.sendMessage(ChatColor.GRAY + "Version: " + ChatColor.GOLD + pdf.getVersion());
            }
            else
            {
                sender.sendMessage(Lang.PLUGIN_PREFIX.toString() + Lang.PERMISSION_DENIED.toString());
            }
        }
        return true;
    }
}
