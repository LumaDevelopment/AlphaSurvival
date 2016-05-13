package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class RanksCommand implements CommandExecutor,
Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("ranks")){
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have these ranks:");
			List<String> ranks = new ArrayList<String>();
			if(p.hasPermission("as.rank.player")){
				ranks.add("Player");
			}
			if(p.hasPermission("as.rank.mvp")){
				ranks.add("MVP");
			}
			if(p.hasPermission("as.rank.scorpius")){
				ranks.add("Scorpius");
			}
			if(p.hasPermission("as.rank.orion")){
				ranks.add("Orion");
			}
			if(p.hasPermission("as.rank.mvp+")){
				ranks.add("MVP+");
			}
			if(p.hasPermission("as.rank.builder")){
				ranks.add("Builder");
			}
			if(p.hasPermission("as.rank.mod")){
				ranks.add("Moderator");
			}
			if(p.hasPermission("as.rank.admin")){
				ranks.add("Administrator");
			}
			if(p.hasPermission("as.rank.owner")){
				ranks.add("Owner");
			}
			for(String rank : ranks){
				p.sendMessage("- " + rank);
			}
			return true;
		}
		
		return false;
	}

}
