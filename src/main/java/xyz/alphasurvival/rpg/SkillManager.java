package xyz.alphasurvival.rpg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class SkillManager implements Listener{

	private static ArrayList<String> nofall = new ArrayList<String>();
	private static ArrayList<String> nofall2 = new ArrayList<String>();

	//Non-Command Usable Skills
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			if(p.hasPermission("as.rpg.class.mage") && p.getInventory().getItemInMainHand().getType() == Material.STICK || p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD){
				e.setDamage(applyStaffMasterSkill(e.getDamage()));
			}
			if(p.hasPermission("as.rpg.class.cleric") && MaterialType.getMaterialType(p.getInventory().getItemInMainHand().getType()) == "SHOVEL"){
				e.setDamage(applySpadeMasterSkill(e.getDamage()));
			}
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!e.getCause().equals(DamageCause.FALL)) return;

		Player p = (Player) e.getEntity();

		if (nofall.contains(p.getUniqueId().toString())) {
			e.setCancelled(true);
			nofall.remove(p.getUniqueId().toString());
		}
		if(nofall2.contains(p.getUniqueId().toString())){
			e.setCancelled(true);
		}
	}

	//Getting Skills
	@EventHandler
	public void onLevelUp(PlayerLevelChangeEvent e){
		Player p = e.getPlayer();
		if(e.getOldLevel() < e.getNewLevel()){
			//Level 5 - SmallHeal, ArrowLaunch, Jump
			if(e.getNewLevel() == 5){
				if(p.hasPermission("as.rpg.class.cleric")){
					addSkill("SmallHeal", p);
				}
				if(p.hasPermission("as.rpg.class.ranger")){
					addSkill("Jump", p);
				}
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("ArrowLaunch", p);
				}
			}
			if(e.getNewLevel() == 8){
				if(p.hasPermission("as.rpg.class.fighter")){
					addSkill("Leap", p);
				}
			}
			if(e.getNewLevel() == 10){
				if(p.hasPermission("as.rpg.class.cleric")){
					addSkill("MediumHeal", p);
				}
			}
			if(e.getNewLevel() == 15){
				if(p.hasPermission("as.rpg.class.cleric")){
					addSkill("HealingLook", p);
				}
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("Fireball", p);
				}
				if(p.hasPermission("as.rpg.class.ranger")){
					addSkill("Wolf", p);
				}
			}
			if(e.getNewLevel() == 20){
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("Combustion", p);
				}
				if(p.hasPermission("as.rpg.class.ranger")){
					addSkill("SafeFall", p);
				}
			}
			if(e.getNewLevel() == 25){
				if(p.hasPermission("as.rpg.class.fighter")){
					addSkill("Strengthening", p);
				}
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("Burn", p);
				}
			}
			if(e.getNewLevel() == 45){
				if(p.hasPermission("as.rpg.class.ranger")){
					addSkill("MultiArrow", p);
				}
			}
			if(e.getNewLevel() == 50){
				if(p.hasPermission("as.rpg.class.cleric")){
					addSkill("MegaHeal", p);
				}
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("Explosion", p);
				}
			}
			if(e.getNewLevel() == 60){
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("SelfDestruct", p);
				}
			}
			if(e.getNewLevel() == 75){
				if(p.hasPermission("as.rpg.class.cleric")){
					addSkill("HealingAura", p);
				}
				if(p.hasPermission("as.rpg.class.fighter")){
					addSkill("Bolt", p);
				}
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("Invincibility", p);
				}
			}
			if(e.getNewLevel() == 100){
				if(p.hasPermission("as.rpg.class.mage")){
					addSkill("DamageAura", p);
				}
			}
		}
	}
	
	//Cleric Skills

	public static List<String> clericSkills(){
		List<String> clericskills = new ArrayList<String>();
		clericskills.add("SpadeMaster");
		clericskills.add("SmallHeal");
		clericskills.add("MediumHeal");
		clericskills.add("HealingLook");
		clericskills.add("MegaHeal");
		clericskills.add("HealingAura");
		return clericskills;
	}
	
	public static List<String> clericSkillDescriptions(){
		List<String> clericskills = new ArrayList<String>();
		clericskills.add("Makes spades (shovels) do 5+ damage.");
		clericskills.add("Heals you 1 heart.");
		clericskills.add("Heals you 5 hearts.");
		clericskills.add("Heals the entity you're looking at 5 hearts.");
		clericskills.add("Heals you 10 hearts.");
		clericskills.add("Heals everyone in a 7 block radius 10 hearts.");
		return clericskills;
	}

	public static double applySpadeMasterSkill(double damage){
		return damage + 5;
	}
	
	//Level 5

	public static void smallHealSkill(Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		if(p.getHealth() + 2 > p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
		}else{
			p.setHealth(p.getHealth() + 2);
		}
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used SmallHeal!");
		ManaManager.takeMana(1, p);
		p.getWorld().playEffect(p.getLocation(), Effect.HEART, 0);
	}

	//Level 10
	
	public static void mediumHealSkill(Player p){
		if(ManaManager.hasMana(5, p) == false){
			notEnoughMana(5, p);
			return;
		}
		if(p.getHealth() + 10 > p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
		}else{
			p.setHealth(p.getHealth() + 10);
		}
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used MediumHeal!");
		ManaManager.takeMana(5, p);
		p.getWorld().playEffect(p.getLocation(), Effect.HEART, 0);
	}

	//Level 15
	
	public static void healingLookSkill(LivingEntity e, Player p){
		if(ManaManager.hasMana(5, p) == false){
			notEnoughMana(5, p);
			return;
		}
		if(e.getHealth() + 10 > e.getMaxHealth()){
			e.setHealth(e.getMaxHealth());
		}else{
			e.setHealth(e.getHealth() + 10);
		}
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used HealingLook!");
		if(e instanceof Player){
			((Player)e).sendMessage(StringStorage.st + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " healed you!");
		}
		e.getWorld().playEffect(e.getLocation(), Effect.HEART, 0);
		ManaManager.takeMana(5, p);
	}

	//Level 50
	
	public static void megaHealSkill(Player p){
		if(ManaManager.hasMana(10, p) == false){
			notEnoughMana(10, p);
			return;
		}
		if(p.getHealth() + 20 > p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
		}else{
			p.setHealth(p.getHealth() + 20);
		}
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used MegaHeal!");
		p.getWorld().playEffect(p.getLocation(), Effect.HEART, 0);
		ManaManager.takeMana(10, p);
	}

	//Level 75
	
	public static void healingAuraSkill(Player p){
		if(ManaManager.hasMana(10, p) == false){
			notEnoughMana(10, p);
		}
		double maxDist = 7;
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getLocation().distance(p.getLocation()) <= maxDist) {
				if(other.getHealth() + 20 > other.getMaxHealth()){
					other.setHealth(other.getMaxHealth());
				}else{
					other.setHealth(other.getHealth() + 20);
					other.sendMessage(StringStorage.ct + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " healed you with HealingAura!");
				}
			}
		}
		ManaManager.takeMana(10, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used HealingAura!");
	}

	//Fighter Skills
	
	public static List<String> fighterSkills(){
		List<String> fighterskills = new ArrayList<String>();
		fighterskills.add("Leap");
		fighterskills.add("Strengthening");
		fighterskills.add("Bolt");
		return fighterskills;
	}
	
	public static List<String> fighterSkillDescriptions(){
		List<String> fighterskills = new ArrayList<String>();
		fighterskills.add("Shoots you 3 blocks into the air!");
		fighterskills.add("Gives you Strength 3 for half a minute.");
		fighterskills.add("Strikes a lightning bolt where you are looking");
		return fighterskills;
	}

	//Level 8
	
	public static void leapSkill(final Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		Location target = p.getLocation().add(0, 3, 0);

		p.teleport(p.getLocation().add(0, 0.5, 0));

		final Vector v = Main.getVectorForPoints(p.getLocation(), target);
		p.setVelocity(v);
		nofall.add(p.getUniqueId().toString());
		ManaManager.setMana(ManaManager.getMana(p) - 1, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Leap!");
	}
	
	//Level 25
	
	public static void strengtheningSkill(Player p){
		if(ManaManager.hasMana(5, p) == false){
			notEnoughMana(5, p);
			return;
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 3));
		ManaManager.setMana(ManaManager.getMana(p) - 5, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Strengthening!");
	}

	//Level 75
	
	public static void boltSkill(Block b, Player p){
		if(ManaManager.hasMana(10, p) == false){
			notEnoughMana(10, p);
			return;
		}
		b.getWorld().strikeLightning(b.getLocation());
		ManaManager.setMana(ManaManager.getMana(p) - 10, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Bolt!");
	}

	//Mage Skills

	public static List<String> mageSkills(){
		List<String> mageskills = new ArrayList<String>();
		mageskills.add("StaffMaster");
		mageskills.add("ArrowLaunch");
		mageskills.add("Fireball");
		mageskills.add("Combustion");
		mageskills.add("Burn");
		mageskills.add("Explosion");
		mageskills.add("SelfDestruct");
		mageskills.add("Invincibility");
		mageskills.add("DamageAura");
		return mageskills;
	}
	
	public static List<String> mageSkillDescriptions(){
		List<String> mageskills = new ArrayList<String>();
		mageskills.add("Attack damage is 5+ when using Blaze Rod or Stick.");
		mageskills.add("Launches a arrow.");
		mageskills.add("Launches a fireball.");
		mageskills.add("Sets an entity in the chunk you are looking at on fire for 5 seconds.");
		mageskills.add("Gives you fire resistance and sets you on fire for 15 seconds.");
		mageskills.add("Makes a explosion where you are looking.");
		mageskills.add("Makes a powerful explosion at your location.");
		mageskills.add("Makes you invincible for 15 seconds.");
		mageskills.add("Does 10 hearts to every player within a 7 block radius.");
		return mageskills;
	}

	public static double applyStaffMasterSkill(double damage){
		return damage + 5;
	}

	//Level 5
	
	public static void arrowLaunchSkill(Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		Location loc = p.getLocation();
		loc.setY(loc.getY() + 2.5);
		p.launchProjectile(Arrow.class);
		ManaManager.setMana(ManaManager.getMana(p) - 1, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used ArrowLaunch!");
	}

	//Level 15
	
	public static void fireballSkill(Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		Fireball f = p.launchProjectile(Fireball.class);
		f.setIsIncendiary(false);
		f.setYield(0);
		ManaManager.setMana(ManaManager.getMana(p) - 1, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Fireball!");
	}

	//Level 20
	
	public static void combustionSkill(Entity e, Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		e.setFireTicks(20 * 5);
		ManaManager.setMana(ManaManager.getMana(p) - 1, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Combustion!");
	}

	//Level 25
	
	public static void burnSkill(Player p){
		if(ManaManager.hasMana(1.5, p) == false){
			notEnoughMana(1.5, p);
			return;
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15 * 20, 1));
		p.setFireTicks(20 * 15);
		ManaManager.setMana(ManaManager.getMana(p) - 1.5, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Burn!");
	}

	//Level 50
	
	public static void explosionSkill(Location loc, Player p){
		if(ManaManager.hasMana(3, p) == false){
			notEnoughMana(3, p);
			return;
		}
		p.getWorld().createExplosion(loc, 3f);
		ManaManager.setMana(ManaManager.getMana(p) - 3, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Explosion!");
	}

	//Level 60
	
	public static void selfDestructSkill(Player p){
		if(ManaManager.hasMana(5, p) == false){
			notEnoughMana(5, p);
			return;
		}
		p.getWorld().createExplosion(p.getLocation(), 5f);
		ManaManager.setMana(ManaManager.getMana(p) - 5, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used SelfDestruct!");
	}

	//Level 75
	
	public static void invincibilitySkill(Player p){
		if(ManaManager.hasMana(10, p) == false){
			notEnoughMana(10, p);
			return;
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15 * 20, 10));
		ManaManager.setMana(ManaManager.getMana(p) - 10, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Invincibilty!");
	}

	//Level 100
	
	public static void damageAuraSkill(Player p){
		if(ManaManager.hasMana(10, p) == false){
			notEnoughMana(10, p);
			return;
		}
		double maxDist = 7;
		for (Player other : Bukkit.getOnlinePlayers()) {
			if(other.equals(p)){
				continue;
			}
			if (other.getLocation().distance(p.getLocation()) <= maxDist) {
				other.damage(20);
				other.sendMessage(StringStorage.ct + ChatColor.AQUA + p.getName() + ChatColor.RED + " damaged you with DamageAura!");
			}
		}
		ManaManager.setMana(ManaManager.getMana(p) - 10, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used DamageAura!");
	}
	
	//Ranger Skills
	
	public static List<String> rangerSkills(){
		List<String> fighterskills = new ArrayList<String>();
		fighterskills.add("Jump");
		fighterskills.add("Wolf");
		fighterskills.add("SafeFall");
		fighterskills.add("MultiArrow");
		return fighterskills;
	}
	
	public static List<String> rangerSkillDescriptions(){
		List<String> fighterskills = new ArrayList<String>();
		fighterskills.add("Shoots you 3 blocks into the air!");
		fighterskills.add("Spawns a wolf.");
		fighterskills.add("You won't take fall damage for 25 seconds.");
		fighterskills.add("Fires 10 arrows.");
		return fighterskills;
	}
	
	//Level 5
	
	public static void jumpSkill(final Player p){
		if(ManaManager.hasMana(1, p) == false){
			notEnoughMana(1, p);
			return;
		}
		Location target = p.getLocation().add(0, 3, 0);

		p.teleport(p.getLocation().add(0, 0.5, 0));

		final Vector v = Main.getVectorForPoints(p.getLocation(), target);
		p.setVelocity(v);
		nofall.add(p.getUniqueId().toString());
		ManaManager.setMana(ManaManager.getMana(p) - 1, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Leap!");
	}

	//Level 15
	
	public static void wolfSkill(Player p){
		if(ManaManager.hasMana(3, p) == false){
			notEnoughMana(3, p);
			return;
		}
		p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
		ManaManager.setMana(ManaManager.getMana(p) - 3, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used Wolf!");
	}
	
	//Level 20
	
	public static void safeFallSkill(Player p){
		if(ManaManager.hasMana(5, p) == false){
			notEnoughMana(5, p);
			return;
		}
		
		nofall2.add(p.getUniqueId().toString());
		ManaManager.setMana(ManaManager.getMana(p) - 5, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used NoFall!");
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				nofall2.remove(p.getUniqueId().toString());
				p.sendMessage(StringStorage.st + ChatColor.RED + "NoFall wore off!");
			}
		}, 15);
	}
	
	//Level 45
	
	public static void multiArrowSkill(Player p){
		if(ManaManager.hasMana(7, p) == false){
			notEnoughMana(7, p);
			return;
		}
		for(int c = 0; c > 10; c++){
			p.launchProjectile(Arrow.class);
		}
		ManaManager.setMana(ManaManager.getMana(p) - 7, p);
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You used ArrowLaunch!");
	}
	
	//End of Skills
	
	public static void notEnoughMana(double mana, Player p){
		p.sendMessage(StringStorage.st + ChatColor.RED + "You must have " + mana + " Mana to use this skill, but you have " + ManaManager.getMana(p) + " Mana!");
	}
	
	public static void addSkill(String skillName, Player p){
		List<String> skillList = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Skills");
		if(skillList != null){
			if(skillList.contains(skillName)){
				return;
			}
			skillList.add(skillName);
			Main.instance.getConfig().set(p.getUniqueId() + ".Skills", skillList);
			Main.instance.saveConfig();
		}else{
			List<String> newSkillList = new ArrayList<String>();
			newSkillList.add(skillName);
			Main.instance.getConfig().set(p.getUniqueId() + ".Skills", newSkillList);
			Main.instance.saveConfig();
		}
		p.sendMessage(StringStorage.st + ChatColor.GREEN + "You learned " + ChatColor.LIGHT_PURPLE + skillName + ChatColor.GREEN + "!");
	}
	
}
