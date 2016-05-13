package xyz.alphasurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;
import xyz.alphasurvival.rpg.RPGScoreboard;

public class PlayerListener implements Listener{
	
	@EventHandler
	public void pJoinEvent(PlayerJoinEvent e){
		Player p = e.getPlayer();
		RPGScoreboard.setupScoreboard(p);
		join(p, e);
	}
	
	@EventHandler
	public void pQuitEvent(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage(StringStorage.smpt + ChatColor.AQUA + p.getName() + ChatColor.RED + " has left the game!");
	}
	
	public static void join(Player p, PlayerJoinEvent e){
		if(p.hasPlayedBefore() == true){
			e.setJoinMessage(StringStorage.smpt + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " has joined the game!");
			p.sendMessage(ChatColor.GREEN + "Welcome back to " + ChatColor.BLUE + ChatColor.BOLD + "Alpha Survival" + ChatColor.RESET + ChatColor.GREEN + ", " + ChatColor.AQUA + p.getName() + ChatColor.GREEN + "!");
		}else if(p.hasPlayedBefore() == false){
			p.teleport(new Location(Bukkit.getWorld("Kingdom"), 11, 101, 237));
			Main.instance.getConfig().set(p.getUniqueId() + ".MaxHealth", 20);
			e.setJoinMessage(StringStorage.smpt + ChatColor.AQUA + p.getName() + ChatColor.GOLD + " has joined for the first time!");
			p.sendMessage(ChatColor.GREEN + "Hello! Welcome to " + ChatColor.BLUE + ChatColor.BOLD + "Alpha Survival" + ChatColor.RESET + ChatColor.GREEN + "!");
			p.sendMessage(ChatColor.GREEN + "To see the rules (they are very important!), use \"/rules\"!");
			p.sendMessage(ChatColor.GREEN + "There are TONS of new commands, so you'll need \"/help\"!");
			p.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "IMPORTANT: " + ChatColor.RESET + ChatColor.GREEN + "To see game modifications, use \"/gamenotes\"!");
			p.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "IMPORTANT: " + ChatColor.RESET + ChatColor.GREEN + "Your items are NOT protected unless you have a faction and have claimed your area! There is no Lockette!");
			p.sendMessage(ChatColor.GREEN + "Go to \"http://alphasurvivalweb.enjin.com/rpghelp#\" for RPG help.");
			p.sendMessage(ChatColor.GREEN + "For tips, I suggest asking a player, or if the Owner is online, ask him!");
		}
	}
	
}
