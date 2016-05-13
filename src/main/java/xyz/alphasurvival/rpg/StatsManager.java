package xyz.alphasurvival.rpg;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class StatsManager implements Listener{

	@EventHandler
	public void onLevelChange(PlayerLevelChangeEvent e){
		
		Player p = e.getPlayer();
		
		if(e.getNewLevel() > e.getOldLevel()){
			if(!p.hasPermission("as.rpg.hasclass")){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "To get health boosts and stat changes, choose a class!");
				return;
			}
			if(e.getNewLevel() > 20){
				p.setMaxHealth(e.getNewLevel());
				if(p.getMaxHealth() + 2 > p.getMaxHealth()){
					p.setHealth(p.getMaxHealth());
				}else{
					p.setHealth(p.getHealth() + 2);
				}
			}
			Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", getPowerStat(p) + 1);
			Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", getDefenseStat(p) + 1);
			Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", getSpeedStat(p) + 1);
			Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", getManaSkillStat(p) + 1);
			Main.instance.saveConfig();
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You leveled up!");
			p.sendMessage(ChatColor.GREEN + "Stats:");
			p.sendMessage(ChatColor.RED + "Power: " + getPowerStat(p));
			p.sendMessage(ChatColor.BLUE + "Defense: " + getDefenseStat(p));
			p.sendMessage(ChatColor.WHITE + "Speed: " + getSpeedStat(p));
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Mana Skill: " + getManaSkillStat(p));
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			e.setDamage(e.getDamage() + getPowerStat(p) / 5);
		}
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			e.setDamage(e.getDamage() - getDefenseStat(p) / 5);
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(e.getFrom().getX() == e.getTo().getX() && 
		   e.getFrom().getY() == e.getTo().getY() &&
		   e.getFrom().getZ() == e.getTo().getZ()){
			
			return;
			
		}
		int speed = getSpeedStat(e.getPlayer());
		int speedeffectamplifier = 0;
		while(speed >= 5){
			speed = speed - 5;
			speedeffectamplifier = speedeffectamplifier + 1;
		}
		if(speedeffectamplifier > 0){
			PotionEffect se = new PotionEffect(PotionEffectType.SPEED, 5 * 20, speedeffectamplifier);
			if(e.getPlayer().getActivePotionEffects().contains(se)){
				e.getPlayer().getActivePotionEffects().remove(se);
			}
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20, speedeffectamplifier));
		}
	}
	
	public static int getPowerStat(Player p){
		if(p.hasPermission("as.rpg.hasclass")){
			return Main.instance.getConfig().getInt(p.getUniqueId() + ".Stats.Power");
		}else{
			return 0;
		}
	}
	
	public static int getDefenseStat(Player p){
		if(p.hasPermission("as.rpg.hasclass")){
			return Main.instance.getConfig().getInt(p.getUniqueId() + ".Stats.Defense");
		}else{
			return 0;
		}
	}
	
	public static int getSpeedStat(Player p){
		if(p.hasPermission("as.rpg.hasclass")){
			return Main.instance.getConfig().getInt(p.getUniqueId() + ".Stats.Speed");
		}else{
			return 0;
		}
	}
	
	public static int getManaSkillStat(Player p){
		if(p.hasPermission("as.rpg.hasclass")){
			return Main.instance.getConfig().getInt(p.getUniqueId() + ".Stats.ManaSkill");
		}else{
			return 0;
		}
	}
	
}
