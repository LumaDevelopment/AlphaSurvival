package xyz.alphasurvival.rpg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class StatsCommand implements CommandExecutor,
Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("stats")){
			if(!p.hasPermission("as.rpg.hasclass")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You must choose a class to use this command!");
				return true;
			}
			p.sendMessage(ChatColor.BLACK + "--------[" + ChatColor.BLUE + "RPG Stats" + ChatColor.BLACK + "]--------");
			p.sendMessage(ChatColor.RED + "Health: " + ChatColor.AQUA + p.getHealth() + ChatColor.GRAY + "/" + ChatColor.AQUA + p.getMaxHealth());
			p.sendMessage(ChatColor.YELLOW + "Food: " + ChatColor.AQUA + p.getFoodLevel() + ChatColor.GRAY + "/" + ChatColor.AQUA + "20");
			p.sendMessage(ChatColor.GREEN + "Level: " + ChatColor.AQUA + p.getLevel());
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Mana: " + ChatColor.AQUA + ManaManager.getMana(p));
			p.sendMessage(ChatColor.GREEN + "Stats:");
			p.sendMessage(ChatColor.RED + "Power: " + ChatColor.AQUA + StatsManager.getPowerStat(p));
			p.sendMessage(ChatColor.BLUE + "Defense: " + ChatColor.AQUA + StatsManager.getDefenseStat(p));
			p.sendMessage(ChatColor.WHITE + "Speed: " +ChatColor.AQUA + StatsManager.getSpeedStat(p));
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Mana Skill: " + ChatColor.AQUA + StatsManager.getManaSkillStat(p));
			RPGScoreboard.setupScoreboard(p);
			return true;
		}
		
		return false;
	}

}
