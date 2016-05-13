package xyz.alphasurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import xyz.alphasurvival.StringStorage;

public class CustomSign implements Listener{

	@EventHandler
	public void onSignCreate(SignChangeEvent e){
		Player p = e.getPlayer();
		if(e.getLine(0).equals("[AS]") && e.getLine(1).equals("Heal") && e.getLine(2).equals("Sign")){
			if(!p.hasPermission("as.signs.heal")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to create a heal sign!");
			}else{
				e.setLine(0, ChatColor.DARK_BLUE + "[Heal]");
				e.setLine(1, ChatColor.GREEN + "Click me");
				e.setLine(2, ChatColor.GREEN + "to heal!");
				p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You made a heal sign!");
			}
		}
		if(e.getLine(0).equals("[AS]") && e.getLine(1).equals("Feed") && e.getLine(2).equals("Sign")){
			if(!p.hasPermission("as.signs.feed")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You don't have permission to create a heal sign!");
			}else{
				e.setLine(0, ChatColor.DARK_BLUE + "[Feed]");
				e.setLine(1, ChatColor.GREEN + "Click me to");
				e.setLine(2, ChatColor.GREEN + "be fed!");
				p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You made a feed sign!");
			}
		}
	}
	
	@EventHandler
	public void onBlockClick(PlayerInteractEvent e){
		if(e.getClickedBlock() != null){
			if(
					e.getClickedBlock().getType() != Material.SIGN &&
					e.getClickedBlock().getType() != Material.WALL_SIGN
			){
				return;
			}
			Sign sign = (Sign) e.getClickedBlock().getState();
			if(
					sign.getLine(0).equals(ChatColor.DARK_BLUE + "[Heal]") &&
					sign.getLine(1).equals(ChatColor.GREEN + "Click me") &&
					sign.getLine(2).equals(ChatColor.GREEN + "to heal!")
			){
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "heal " + e.getPlayer().getName());
			}
			if(
					sign.getLine(0).equals(ChatColor.DARK_BLUE + "[Feed]") &&
					sign.getLine(1).equals(ChatColor.GREEN + "Click me to") &&
					sign.getLine(2).equals(ChatColor.GREEN + "be fed!")
			){
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "feed " + e.getPlayer().getName());
			}
		}
	}
	
}
