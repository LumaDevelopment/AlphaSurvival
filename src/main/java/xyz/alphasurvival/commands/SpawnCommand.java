package xyz.alphasurvival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class SpawnCommand implements CommandExecutor, Listener{
	
	public static List<Player> wantsToTele = new ArrayList<Player>();
	public static int Count = 0;
	public static int task = 0;
	public static int time = 5;
	public static Location pLoc;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(wantsToTele.contains(p)){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You are already in queue to teleport!");
				return true;
			}else{
				wantsToTele.add(p);
				runTask(p, false);
				pLoc = p.getLocation();
				p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You will teleport in 5 seconds. Don't move.");
				return true;
			}
		}
		
		return false;
	}
	
	public void Teleport(Player p){
		p.teleport(new Location(Bukkit.getWorld("Kingdom"), 72, 97, -6));
		p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You have been teleported to spawn!");
		runTask(p, true);
	}
	
	public void runTask(Player p, boolean cancel){
		if(cancel == true){
			Bukkit.getServer().getScheduler().cancelTask(task);
			Count = 0;
			if(wantsToTele.contains(p)){
				wantsToTele.remove(p);
			}
		}else{
			task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable(){
				public void run(){
					if(wantsToTele.contains(p)){
						Count = Count + 1;
						if(Count == 5){
							Count = 0;
							wantsToTele.remove(p);
							Teleport(p);
						}
					}
				}
			}, 0, time * 5);
		}
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(wantsToTele.contains(p)){
			if(e.getTo().getBlockX() != pLoc.getBlockX() || e.getTo().getBlockY() != pLoc.getBlockY() || e.getTo().getBlockZ() != pLoc.getBlockZ()){
				pLoc = null;
				runTask(p, true);
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "Teleportation request cancelled.");
			}
		}
	}

}
