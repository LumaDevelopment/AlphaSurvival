package xyz.alphasurvival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import xyz.alphasurvival.ASPlayer;
import xyz.alphasurvival.Main;

public class PlayerRanks implements Listener {

	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String pname = p.getDisplayName();
		String nick = Main.instance.getConfig().getString(p.getUniqueId() + ".Nickname");
		String rpgclass = "";

		if(!p.hasPermission("as.rpg.hasclass")){
			rpgclass = "Undecided ";
		}else if(p.hasPermission("as.rpg.class.cleric")){
			rpgclass = "" + ChatColor.YELLOW + ChatColor.BOLD + "Cleric " + ChatColor.RESET;
		}else if(p.hasPermission("as.rpg.class.fighter")){
			rpgclass = "" + ChatColor.RED + ChatColor.BOLD + "Fighter " + ChatColor.RESET;
		}else if(p.hasPermission("as.rpg.class.mage")){
			rpgclass = "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Mage " + ChatColor.RESET;
		}else if(p.hasPermission("as.rpg.class.ranger")){
			rpgclass = "" + ChatColor.GREEN + ChatColor.BOLD + "Ranger " + ChatColor.RESET;
		}else if(p.hasPermission("as.rpg.class.rouge")){
			rpgclass = "" + ChatColor.DARK_RED + ChatColor.BOLD + "Rouge " + ChatColor.RESET;
		}

		if(nick != null){
			p.setDisplayName(nick);
		}else{
			p.setDisplayName(p.getName());
		}

		ASPlayer player = new ASPlayer(p);

		if(p.hasPermission("as.rank.player")){
			String member = rpgclass + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> "  + ChatColor.RESET + e.getMessage();
			e.setFormat(member);
		}
		if(player.hasRank("MVP")){
			String vip = rpgclass + ChatColor.BLUE + "MVP " + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> "  + ChatColor.RESET + e.getMessage();
			e.setFormat(vip);
		}
		if(player.hasRank("Scorpius")){
			String leo = rpgclass + ChatColor.DARK_PURPLE + "SCORPIUS " + ChatColor.RESET + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.RESET + e.getMessage();
			e.setFormat(leo);
		}
		if(player.hasRank("Orion")){
			String orion = rpgclass + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Orion " + ChatColor.RESET + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.RESET + e.getMessage();
			e.setFormat(orion);
		}
		if(player.hasRank("MVP+")){
			String vip = rpgclass + ChatColor.BLUE + ChatColor.BOLD + "MVP+ " + ChatColor.RESET + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> "  + ChatColor.RESET + e.getMessage();
			e.setFormat(vip);
		}
		if(player.hasRank("Builder")){
			String builder = rpgclass + ChatColor.YELLOW + "Builder " + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.RESET + e.getMessage();
			e.setFormat(builder);
		}
		if(player.hasRank("Mod")){
			String mod = rpgclass + ChatColor.RED + "Moderator " + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.GREEN + e.getMessage();
			e.setFormat(mod);
		}
		if(player.hasRank("Admin")){
			String admin = rpgclass + ChatColor.RED + ChatColor.BOLD + "ADMIN " + ChatColor.RESET + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.GREEN + e.getMessage();
			e.setFormat(admin);
		}
		if(player.hasRank("Owner")){
			String ownertag = "" + ChatColor.GRAY + ChatColor.BOLD + "O" + ChatColor.RESET + ChatColor.DARK_PURPLE + ChatColor.BOLD + "W" + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + "N" + ChatColor.RESET + ChatColor.DARK_PURPLE + ChatColor.BOLD + "E" + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + "R " + ChatColor.RESET;
			String owner = rpgclass + ownertag + ChatColor.AQUA + pname + ChatColor.GRAY + " >>> " + ChatColor.GREEN + e.getMessage();
			e.setFormat(owner);
		}
	}

}
