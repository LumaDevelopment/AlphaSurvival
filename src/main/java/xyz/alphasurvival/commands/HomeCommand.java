package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class HomeCommand implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("home")){
			if(args.length == 0){
				invalidMessage(p);
				return true;
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Here is some \"/home\" help");
					p.sendMessage("/home list - Lists your homes.");
					p.sendMessage("/home <homename> - Teleports you to the specified home.");
					p.sendMessage("/home help - This command.");
					p.sendMessage("/home set <homename> - Sets a home.");
					p.sendMessage("/home remove <homename> - Removes a home.");
					return true;
				}else if(args[0].equalsIgnoreCase("list")){
					p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "A list of your homes:");
					List<String> homelist = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Homes");
					if(homelist != null){
						for(String warpname : homelist){
							p.sendMessage("- " + warpname);
						}
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have not set a home yet!");
						return true;
					}
				}else{
					List<String> homelist = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Homes");
					if(homelist != null){
						for(String warpname : homelist){
							if(warpname.equals(args[0])){
								int x = Main.instance.getConfig().getInt(p.getUniqueId() + "." + warpname + "-Home" + ".X");
								int y = Main.instance.getConfig().getInt(p.getUniqueId() + "." + warpname + "-Home" + ".Y");
								int z = Main.instance.getConfig().getInt(p.getUniqueId() + "." + warpname + "-Home" + ".Z");
								String worldName = Main.instance.getConfig().getString(p.getUniqueId() + "." + warpname + "-Home" + ".World");
								p.teleport(new Location(Bukkit.getWorld(worldName), x, y, z));
								p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have been teleported to Home " + ChatColor.AQUA + warpname + ChatColor.GREEN + "!");
								return true;
							}
						}
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have a home named " + args[0] + ". Home names are case sensitive!");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have not made any homes!");
						return true;
					}
				}
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("set")){
					List<String> homelist = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Homes");
					if(homelist != null){
						if(p.hasPermission("as.rank.player") && homelist.size() > 2){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have reached you max home limit!");
							return true;
						}
						if(p.hasPermission("as.rank.mvp") && homelist.size() > 3){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have reached you max home limit!");
							return true;
						}
						if(p.hasPermission("as.rank.scorpius") && homelist.size() > 3){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have reached you max home limit!");
							return true;
						}
						if(p.hasPermission("as.rank.orion") && homelist.size() > 3){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have reached you max home limit!");
							return true;
						}
						if(p.hasPermission("as.rank.mvp+") && homelist.size() > 4){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have reached you max home limit!");
							return true;
						}
						if(homelist.contains(args[1])){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "This home already exists!");
							return true;
						}
						for(Material m : Material.values()){
							if(m.name().equals(args[1])){
								p.sendMessage(StringStorage.smpt + ChatColor.RED + "Becuase of the RPG feature of AlphaSurvival, you cannot name your home a Material name!");
								return true;
							}
						}
						homelist.add(args[1]);
						Main.instance.getConfig().set(p.getUniqueId() + ".Homes", homelist);
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".X", p.getLocation().getBlockX());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".Y", p.getLocation().getBlockY());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".Z", p.getLocation().getBlockZ());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".World", p.getLocation().getWorld().getName());
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Home " + ChatColor.AQUA + args[1] + ChatColor.GREEN + " has been set!");
						return true;
					}else{
						List<String> homelist2 = new ArrayList<String>();
						homelist2.add(args[1]);
						Main.instance.getConfig().set(p.getUniqueId() + ".Homes", homelist2);
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".X", p.getLocation().getBlockX());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".Y", p.getLocation().getBlockY());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".Z", p.getLocation().getBlockZ());
						Main.instance.getConfig().set(p.getUniqueId() + "." + args[1] + "-Home" + ".World", p.getLocation().getWorld().getName());
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Home " + ChatColor.AQUA + args[1] + ChatColor.GREEN + " has been set!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("remove")){
					List<String> homelist = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Homes");
					if(homelist != null){
						if(!homelist.contains(args[1])){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "This home is not set, so you can not remove it!");
							return true;
						}
						homelist.remove(args[1]);
						Main.instance.getConfig().set(p.getUniqueId() + ".Homes", homelist);
						Main.instance.getConfig().getConfigurationSection(p.getUniqueId() + "." + args[1] + "-Home").equals(null);
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "Home " + ChatColor.AQUA + args[1] + ChatColor.RED + " has been removed!");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have any homes to remove!");
						return true;
					}
				}else{
					invalidMessage(p);
					return true;
				}
			}
		}
		return false;
	}
	
	private void invalidMessage(Player p){
		p.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage. Usage \"/home help\".");
	}

}
