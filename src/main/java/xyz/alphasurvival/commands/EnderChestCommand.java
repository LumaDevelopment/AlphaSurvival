package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class EnderChestCommand implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("enderchest")){
			if(!p.hasPermission("as.essentials.enderchest")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to use this command!");
				return true;
			}
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have opened your enderchest!");
			p.openInventory(p.getEnderChest());
			return true;
		}
		
		return false;
	}

}
