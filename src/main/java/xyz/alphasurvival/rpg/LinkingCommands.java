package xyz.alphasurvival.rpg;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class LinkingCommands implements CommandExecutor,
Listener{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.st + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("link")){
			List<String> skillList = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Skills");
			if(skillList != null){
				if(args.length == 1){
					String skill = args[0];
					if(skillList.contains(skill)){
						Main.instance.getConfig().set(p.getUniqueId() + "." + p.getInventory().getItemInMainHand().getType().name() + ".Skill", skill);
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.st + ChatColor.GREEN + "You have linked the skill " + ChatColor.LIGHT_PURPLE + skill + ChatColor.GREEN + " to the item type " + ChatColor.GREEN + itemTypeName(p.getInventory().getItemInMainHand().getType()));
						return true;
					}else{
						p.sendMessage(StringStorage.st + ChatColor.RED + "You don't have this skill! Skill names are case sensitive.");
						return true;
					}
				}else{
					p.sendMessage(StringStorage.st + ChatColor.RED + "Invalid usage! Use \"/link <skillname>\".");
					return true;
				}
			}else{
				p.sendMessage(StringStorage.st + ChatColor.RED + "You must have a skill to link to a item!");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("unlink")){
			if(Main.instance.getConfig().getConfigurationSection(p.getUniqueId() + "." + p.getInventory().getItemInMainHand().getType().name()) != null){
				String skill = Main.instance.getConfig().getString(p.getUniqueId() + "." + p.getInventory().getItemInMainHand().getType().name() + ".Skill");
				String itemName = p.getInventory().getItemInMainHand().getType().name();
				Main.instance.getConfig().set(p.getUniqueId() + "." + p.getInventory().getItemInMainHand().getType().name(), null);
				Main.instance.saveConfig();
				p.sendMessage(StringStorage.st + ChatColor.RED + "The skill " + ChatColor.LIGHT_PURPLE + skill + ChatColor.RED + " was unlinked from " + ChatColor.AQUA + itemName + ChatColor.RED + " items.");
				return true;
			}else{
				p.sendMessage(StringStorage.st + ChatColor.RED + "The item that you are holding is not linked to any skill!");
				return true;
			}
		}
		
		return true;
	}

	public static String itemTypeName(Material m){
		String enamelow = StringUtils.lowerCase(m.name());
		String enamehigh;
		if(enamelow.contains("_")){
			List<String> namelist = Arrays.asList(enamelow.split("_"));
			String np1 = StringUtils.capitalize(namelist.get(0));
			String np2 = StringUtils.capitalize(namelist.get(1));
			enamehigh = np1 + " " + np2;
		}else{
			enamehigh = StringUtils.capitalize(enamelow);
		}
		return enamehigh;
	}
	
}
