package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class WarpCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to access the warp suite.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("warp")){
			if(args.length == 0){
				invalidMessage(p);
				return true;
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Here is some \"/warp\" help");
					p.sendMessage("/warp list - Lists warps.");
					p.sendMessage("/warp <warpname> - Teleports you to the specified warp if you have permission.");
					p.sendMessage("/warp help - This command.");
					p.sendMessage("/warp set <warpname> - Sets a warp.");
					p.sendMessage("/warp remove <warpname> - Removes a warp.");
					return true;
				}else if(args[0].equalsIgnoreCase("list")){
					List<String> warplist = Main.instance.getConfig().getStringList("Warps");
					if(warplist != null){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "A list of warps:");
						for(String warpname : warplist){
							if(p.hasPermission("as.warps." + warpname)){
								p.sendMessage("- " + warpname);
							}else{
								p.sendMessage("- " + ChatColor.STRIKETHROUGH + warpname);
							}
						}
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "There are no warps set!");
						return true;
					}
				}else{
					List<String> warplist = Main.instance.getConfig().getStringList("Warps");
					if(warplist != null){
						for(String warpname : warplist){
							if(warpname.equalsIgnoreCase(args[0])){
								if(!p.hasPermission("as.warps." + warpname)){
									p.sendMessage(StringStorage.smpt + ChatColor.RED + "You do not have permission to teleport to Warp " + ChatColor.AQUA + warpname);
									break;
								}
								int x = Main.instance.getConfig().getInt(warpname + "-Warp" + ".X");
								int y = Main.instance.getConfig().getInt(warpname + "-Warp" + ".Y");
								int z = Main.instance.getConfig().getInt(warpname + "-Warp" + ".Z");
								String worldName = Main.instance.getConfig().getString(warpname + "-Warp" + ".World");
								p.teleport(new Location(Bukkit.getWorld(worldName), x, y, z));
								p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have been teleported to warp " + ChatColor.AQUA + warpname + ChatColor.GREEN + "!");
								return true;
							}
						}
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "There is no warp named " + args[0] + "!");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "A warp has not been set up yet!");
						return true;
					}
				}
			}else if(args.length == 2){
				if(args[0].equalsIgnoreCase("set")){
					if(!p.hasPermission("as.warps.set")){
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to set warps!");
						return true;
					}
					List<String> warplist = Main.instance.getConfig().getStringList("Warps");
					if(warplist != null){
						if(warplist.contains(args[1])){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "This warp already exists!");
							return true;
						}
						warplist.add(args[1]);
						Main.instance.getConfig().set("Warps", warplist);
						Main.instance.getConfig().set(args[1] + "-Warp" + ".X", p.getLocation().getBlockX());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".Y", p.getLocation().getBlockY());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".Z", p.getLocation().getBlockZ());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".World", p.getLocation().getWorld().getName());
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Warp " + ChatColor.AQUA + args[1] + ChatColor.GREEN + " has been set!");
						return true;
					}else{
						List<String> warplist2 = new ArrayList<String>();
						warplist2.add(args[1]);
						Main.instance.getConfig().set("Warps", warplist2);
						Main.instance.getConfig().set(args[1] + "-Warp" + ".X", p.getLocation().getBlockX());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".Y", p.getLocation().getBlockY());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".Z", p.getLocation().getBlockZ());
						Main.instance.getConfig().set(args[1] + "-Warp" + ".World", p.getLocation().getWorld().getName());
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "Warp " + ChatColor.AQUA + args[1] + ChatColor.GREEN + " has been set!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("remove")){
					if(!p.hasPermission("as.warps.remove")){
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You do not have permission to remove warps!");
						return true;
					}
					List<String> warplist = Main.instance.getConfig().getStringList("Warps");
					if(warplist != null){
						if(!warplist.contains(args[1])){
							p.sendMessage(StringStorage.smpt + ChatColor.RED + "This warp is not set, so you can not remove it!");
							return true;
						}
						warplist.remove(args[1]);
						Main.instance.getConfig().set("Warps", warplist);
						Main.instance.getConfig().getConfigurationSection(args[1] + "-Warp").equals(null);
						Main.instance.saveConfig();
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "Warp " + ChatColor.AQUA + args[1] + ChatColor.RED + " has been removed!");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "There is no warp to remove!");
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
		p.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage. Usage \"/warp help\".");
	}

}
