package xyz.alphasurvival.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class MessageCommand implements Listener, CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You have to be a player to use this command.");
			return true;
		}
		
		Player p = (Player) sender;
		Player target = Bukkit.getServer().getPlayer(args[0]);
		
		if(cmd.getName().equalsIgnoreCase("message")){
			
			if(args.length == 0 || args.length == 1){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "Usage: /message <player> <message>");
				return true;
			}
			
			if (target == null) {
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "Could not find player " + args[0] + ".");
				return true;
			}
			
			String msg = StringUtils.join(args, ' ', 1, args.length);
			
            target.sendMessage(ChatColor.BLACK + "[" + ChatColor.AQUA + p.getName() + ChatColor.LIGHT_PURPLE + " -> " + ChatColor.AQUA + "me" + ChatColor.BLACK + "] " + ChatColor.GREEN + msg);
            p.sendMessage(ChatColor.BLACK + "[" + ChatColor.AQUA + "me" + ChatColor.LIGHT_PURPLE + " -> " + ChatColor.AQUA + target.getName() + ChatColor.BLACK + "] " + ChatColor.GREEN + msg);
            
            
            return true;
            
		}
		
		return false;
	}

}
