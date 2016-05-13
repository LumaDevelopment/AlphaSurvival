package xyz.alphasurvival.protection;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class OpOverride implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("op")){
			sender.sendMessage(StringStorage.aspt + ChatColor.RED + "This command is disabled!");
			return true;
		}
		
		return false;
	}

}
