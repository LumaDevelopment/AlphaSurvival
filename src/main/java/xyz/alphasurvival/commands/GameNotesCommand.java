package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class GameNotesCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("gamenotes")){
			sender.sendMessage("-----[" + ChatColor.BLUE + "AS Game Rules" + ChatColor.WHITE + "]-----");
			sender.sendMessage("This command notifies you of all the modified aspects of the game that apply to this server.");
			sender.sendMessage("KeepInventory is disabled!");
			sender.sendMessage("PvP is on!");
			sender.sendMessage("TNT doesn't damage blocks, but damages players!");
			sender.sendMessage("The Factions plugin is only for claiming!");
			sender.sendMessage("Use Factions claiming as a alternative to Lockette!");
			sender.sendMessage("Our RPG plugin only allows certain classes to use certain tools.\nTry a few things before you ask the owner about it.\nIf you try to open a door or cheset, make sure you do it with a allowed tool or empty hand.");
		}

		return false;
	}

}
