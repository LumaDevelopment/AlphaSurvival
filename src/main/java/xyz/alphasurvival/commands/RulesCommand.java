package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class RulesCommand implements CommandExecutor, Listener{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("rules")){
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "The rules are:");
			p.sendMessage(ChatColor.RED + "Chat Rules: No Spamming, Caps, Rudeness, Hackusation, Trolling, Mini-Modding, Arguing, Advertising, Chat Filter Bypassing, Discrimination, or giving money to players for in-game items!");
			p.sendMessage(ChatColor.RED + "General Rules: No Trolling, Exploitation, Hacking, or client mods (Other than NEI, TMI, or Optifine)!");
			p.sendMessage(ChatColor.RED + "Other Rules: No Inappropriate Skins, Names, Pet Names, or Optifine Capes!");
			p.sendMessage(ChatColor.RED + "Breaking any of these rules may result in a ban, depending on the crime commited and the level of damage/trespassing done.");
			p.sendMessage(ChatColor.RED + "It does not matter if you read the rules or not, punishments still apply.");
			return true;
		}
		
		return false;
	}

}
