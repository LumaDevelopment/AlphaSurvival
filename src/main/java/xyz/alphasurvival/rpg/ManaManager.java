package xyz.alphasurvival.rpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;

public class ManaManager implements Listener{
	
	public static void takeMana(double mana, Player p){
		ManaManager.setMana(ManaManager.getMana(p) - mana, p);
	}

	public static double getMana(Player p){
		return Main.instance.getConfig().getDouble(p.getUniqueId() + ".Mana");
	}

	public static void setMana(double mana, Player p){
		Main.instance.getConfig().set(p.getUniqueId() + ".Mana", mana);
		Main.instance.saveConfig();
		for(Player player : Bukkit.getOnlinePlayers()){
			RPGScoreboard.setupScoreboard(player);
		}
	}

	public static void setManaRegenSpeed(int speed, Player p){
		Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", speed);
		Main.instance.saveConfig();
		for(Player player : Bukkit.getOnlinePlayers()){
			RPGScoreboard.setupScoreboard(player);
		}
	}

	public static int getManaRegenSpeed(Player p){
		return StatsManager.getManaSkillStat(p);
	}

	public static boolean hasMana(double mana, Player p){
		if(ManaManager.getMana(p) >= mana){
			return true;
		}else{
			return false;
		}
	}

}
