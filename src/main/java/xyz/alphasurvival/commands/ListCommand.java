package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class ListCommand implements CommandExecutor,
Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("list")){
			List<String> playerList = new ArrayList<String>();
			for(Player player : Bukkit.getOnlinePlayers()){
				playerList.add(player.getName());
			}
			String output = StringStorage.smpt + ChatColor.GREEN + "A list of players (" + playerList.size() + "): " + ChatColor.AQUA;
			for(String player : playerList){
				if(playerList.size() == 1){
					output = output + player;
					break;
				}
				if((playerList.indexOf(player) + 1) == playerList.size()){
					output = output + "and " + player;
				}else{
					output = output + player + ", ";
				}
			}
			sender.sendMessage(output);
			return true;
		}
		return false;
	}

}
