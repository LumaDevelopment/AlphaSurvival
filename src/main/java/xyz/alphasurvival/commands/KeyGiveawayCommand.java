package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.alphasurvival.StringStorage;

public class KeyGiveawayCommand implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("keygiveaway")){
			if(!sender.hasPermission("as.giveaway.key")){
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to use this command!");
				return true;
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("common")){
					keyGiveaway("C");
					return true;
				}else if(args[0].equalsIgnoreCase("mvp")){
					keyGiveaway("MVP");
					return true;
				}else if(args[0].equalsIgnoreCase("mvp+")){
					keyGiveaway("MVP+");
					return true;
				}else if(args[0].equalsIgnoreCase("mythical")){
					keyGiveaway("M");
					return true;
				}else{
					sender.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid key type. Types: Common, MVP, MVP+, and Mythical.");
					return true;
				}
			}else{
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage. Usage: \"/keygiveaway <key type>\".");
				return true;
			}
		}
		return true;
	}
	
	public static void keyGiveaway(String id){
		if(id.equalsIgnoreCase("C")){
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "Common Key Giveaway Started!");
			ItemStack commonKey = new ItemStack(Material.TRIPWIRE_HOOK);
			ItemMeta ckmeta = commonKey.getItemMeta();
			ckmeta.setDisplayName(ChatColor.GREEN + "Common Key");
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN + "A crate key!");
			ckmeta.setLore(lore);
			commonKey.setItemMeta(ckmeta);
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getInventory().addItem(commonKey);
			}
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "Common Key Giveaway Complete!");
		}else if(id.equalsIgnoreCase("MVP")){
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "MVP Key Giveaway Started!");
			ItemStack vipKey = new ItemStack(Material.TRIPWIRE_HOOK);
			ItemMeta vkmeta = vipKey.getItemMeta();
			vkmeta.setDisplayName(ChatColor.BLUE + "MVP Key");
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN + "A crate key!");
			vkmeta.setLore(lore);
			vipKey.setItemMeta(vkmeta);
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getInventory().addItem(vipKey);
			}
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "MVP Key Giveaway Complete!");
		}else if(id.equalsIgnoreCase("MVP+")){
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "MVP+ Key Giveaway Started!");
			ItemStack vip2Key = new ItemStack(Material.TRIPWIRE_HOOK);
			ItemMeta vk2meta = vip2Key.getItemMeta();
			vk2meta.setDisplayName("" + ChatColor.BLUE + ChatColor.BOLD + "MVP+ Key");
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN + "A crate key!");
			vk2meta.setLore(lore);
			vip2Key.setItemMeta(vk2meta);
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getInventory().addItem(vip2Key);
			}
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "MVP+ Key Giveaway Complete!");
		}else if(id.equalsIgnoreCase("M")){
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "Mythical Key Giveaway Started!");
			ItemStack vip2Key = new ItemStack(Material.TRIPWIRE_HOOK);
			ItemMeta vk2meta = vip2Key.getItemMeta();
			vk2meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mythical Key");
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GREEN + "A crate key!");
			vk2meta.setLore(lore);
			vip2Key.setItemMeta(vk2meta);
			for(Player p : Bukkit.getOnlinePlayers()){
				p.getInventory().addItem(vip2Key);
			}
			Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.GREEN + "Mythical Key Giveaway Complete!");
		}
	}

}
