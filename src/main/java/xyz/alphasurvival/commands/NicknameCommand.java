package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class NicknameCommand implements Listener, CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("nickname")){
			if(!p.hasPermission("as.essentials.nickname")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to use this command!");
				return true;
			}
			if(args.length == 0){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage. Usage: \"/nickname <nickname>\".");
				return true;
			}
			String nick = ChatColor.translateAlternateColorCodes('&', args[0]);
			Main.instance.getConfig().set(p.getUniqueId() + ".Nickname", ChatColor.AQUA + "(Nick) " + ChatColor.RESET + nick);
			Main.instance.saveConfig();
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You changed your nickname to " + nick + ChatColor.GREEN + "!");
			return true;
		}
		
		return false;
	}

}
