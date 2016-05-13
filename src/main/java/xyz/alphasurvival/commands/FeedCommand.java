package xyz.alphasurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class FeedCommand implements Listener, CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("feed")){
			if(!sender.hasPermission("as.essentials.feed")){
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to use this command!");
				return true;
			}
			if(args.length == 0){
				if(!(sender instanceof Player)){
					sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
					return true;
				}
				Player p = (Player) sender;
				p.setFoodLevel(20);
				p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You were fed!");
				return true;
			}else if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null){
					sender.sendMessage(StringStorage.smpt + ChatColor.RED + args[1] + " is not online!");
					return true;
				}
				target.setFoodLevel(20);
				target.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You were fed by " + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + "!");
				sender.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You fed " + ChatColor.AQUA + target.getName() + ChatColor.GREEN + "!");
				return true;
			}else{
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage. Usage: /feed or /feed <playername>.");
				return true;
			}
		}
		
		return false;
	}

}
