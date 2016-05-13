package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class GiveawayCommand implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,String[] args) {

		if(cmd.getName().equalsIgnoreCase("giveaway")){
			if(!sender.hasPermission("as.giveaway.rank")){
				sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			List<String> names = new ArrayList<String>();
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.hasPermission("as.rank.owner") ||
				p.hasPermission("as.rank.mod") ||
				p.hasPermission("as.rank.admin") ||
				p.hasPermission("as.rank.mvp+")){
					p.sendMessage(StringStorage.smpt + ChatColor.RED + "Because you already have a rank, you will not be included in this giveaway!");
				}else{
					names.add(p.getName());
				}
			}
			if(names.isEmpty()){
				Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.RED + "Not enough players for giveaway!");
				return true;
			}
			int max = names.size();
			int rand = randomNumber(0, max);
			String pname = names.get(rand);
			if(Bukkit.getPlayer(pname).hasPermission("as.rank.mvp")){
				Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.AQUA + pname + ChatColor.GREEN + " is already " + ChatColor.BLUE + "MVP" + ChatColor.GREEN + ", so they recieved " + ChatColor.BLUE + ChatColor.BOLD + "MVP+" + ChatColor.RESET + ChatColor.GREEN + "!");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pname + " group add MVP+");
			}else{
				Bukkit.broadcastMessage(StringStorage.smpt + ChatColor.AQUA + pname + ChatColor.GREEN + " has won " + ChatColor.BLUE + "MVP" + ChatColor.GREEN + "!");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pname + " group add MVP");
			}
			return true;
		}

		return false;
	}
	
	private static int randomNumber(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt(max) + min;
		return randomNum;
	}

}
