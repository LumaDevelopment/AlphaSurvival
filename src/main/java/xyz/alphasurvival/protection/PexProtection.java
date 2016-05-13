package xyz.alphasurvival.protection;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import xyz.alphasurvival.StringStorage;

public class PexProtection implements Listener{
	
	@EventHandler
	public void onPexCommandExecute(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		if(e.getMessage().startsWith("/pex")){
			e.setCancelled(true);
			player.sendMessage(StringStorage.aspt + ChatColor.RED + "Players are not allowed to use this command!");
		}
	}

}
