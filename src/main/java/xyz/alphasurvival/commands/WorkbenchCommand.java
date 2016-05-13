package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class WorkbenchCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("workbench")){
			if(!p.hasPermission("as.essentials.workbench")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to use this command!");
				return true;
			}
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have opened a workbench!");
			p.openWorkbench(null, true);
			return true;
		}
		
		return false;
	}

}
