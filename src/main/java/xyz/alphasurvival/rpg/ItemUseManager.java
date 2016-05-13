package xyz.alphasurvival.rpg;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class ItemUseManager implements Listener{

	@EventHandler(priority=EventPriority.LOWEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Player)
		{
			Player p = (Player) event.getDamager();
			if (canPlayerUseWeapon(p) == false)
			{
				event.setCancelled(true);
				p.sendMessage(StringStorage.ct + ChatColor.RED + "You cannot use this weapon!");
			}
			else
			{
				if (!(event.getEntity() instanceof Player))
				{
					String entityname = entityTypeName(event.getEntity());
					p.sendMessage(StringStorage.ct + ChatColor.GREEN + "You dealt " + ChatColor.AQUA + event.getDamage() + ChatColor.GREEN + " hearts of damage to the " + ChatColor.AQUA + entityname);
					return;
				}
				Player victim = (Player) event.getEntity();
				p.sendMessage(StringStorage.ct + ChatColor.GREEN + "You dealt " + ChatColor.AQUA + event.getDamage() + ChatColor.GREEN + " hearts of damage to " + ChatColor.AQUA + victim.getName());
				victim.sendMessage(StringStorage.ct + ChatColor.AQUA + p.getName() + ChatColor.RED + " dealt " + ChatColor.AQUA + event.getDamage() + ChatColor.RED + " hearts of damage to you!");
			}
		}
		else if ((event.getEntity() instanceof Player))
		{
			Player p = (Player)event.getEntity();
			String entityname = entityTypeName(event.getDamager());
			p.sendMessage(StringStorage.ct + ChatColor.RED + "A " + ChatColor.AQUA + entityname + ChatColor.RED + " has dealt " + ChatColor.AQUA + event.getDamage() + ChatColor.RED + " hearts of damage to you!");
			return;
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.EXP_BOTTLE){
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(!(e.getPlayer().getInventory().getItemInMainHand().hasItemMeta())){
					e.setCancelled(true);
					e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.RED + "Because of stat potions, EXP bottles are disabled!");
				}else{
					if(!e.getPlayer().hasPermission("as.rpg.hasclass")){
						e.setCancelled(true);
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.RED + "You must choose a class before you use a stat potion!");
						return;
					}
					String itemName = e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName();
					if(itemName.equals("" + ChatColor.RED + ChatColor.BOLD + "Power Stat Potion")){
						e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
						Main.instance.getConfig().set(e.getPlayer().getUniqueId() + ".Stats.Power", StatsManager.getPowerStat(e.getPlayer()) + 15);
						Main.instance.saveConfig();
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.GREEN + "Your " + ChatColor.RED + "Power" + ChatColor.GREEN + " stat was increased by " + ChatColor.AQUA + "15" + ChatColor.GREEN + "!");
					}else if(itemName.equals("" + ChatColor.WHITE + ChatColor.BOLD + "Speed Stat Potion")){
						e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
						Main.instance.getConfig().set(e.getPlayer().getUniqueId() + ".Stats.Speed", StatsManager.getSpeedStat(e.getPlayer()) + 15);
						Main.instance.saveConfig();
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.GREEN + "Your " + ChatColor.WHITE + "Speed" + ChatColor.GREEN + " stat was increased by " + ChatColor.AQUA + "15" + ChatColor.GREEN + "!");
					}else if(itemName.equals("" + ChatColor.BLUE + ChatColor.BOLD + "Defense Stat Potion")){
						e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
						Main.instance.getConfig().set(e.getPlayer().getUniqueId() + ".Stats.Defense", StatsManager.getDefenseStat(e.getPlayer()) + 15);
						Main.instance.saveConfig();
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.GREEN + "Your " + ChatColor.BLUE + "Defense" + ChatColor.GREEN + " stat was increased by " + ChatColor.AQUA + "15" + ChatColor.GREEN + "!");
					}else if(itemName.equals("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Mana Skill Stat Potion")){
						e.getPlayer().getInventory().remove(e.getPlayer().getInventory().getItemInMainHand());
						Main.instance.getConfig().set(e.getPlayer().getUniqueId() + ".Stats.ManaSkill", StatsManager.getManaSkillStat(e.getPlayer()) + 15);
						Main.instance.saveConfig();
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.GREEN + "Your " + ChatColor.LIGHT_PURPLE + "Mana Skill" + ChatColor.GREEN + " stat was increased by " + ChatColor.AQUA + "15" + ChatColor.GREEN + "!");
					}else{
						e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.RED + "This is not a stat potion!");
					}
				}
			}
		}
		if (Main.instance.getConfig().getConfigurationSection(e.getPlayer().getUniqueId() + "." + e.getPlayer().getInventory().getItemInMainHand().getType().name()) != null)
		{
			e.setCancelled(true);
			String skill = Main.instance.getConfig().getString(e.getPlayer().getUniqueId() + "." + e.getPlayer().getInventory().getItemInMainHand().getType().name() + ".Skill");
			e.getPlayer().sendMessage(StringStorage.st + ChatColor.GREEN + "Linked Skill " + ChatColor.LIGHT_PURPLE + skill + ChatColor.GREEN + " has been activated!");
			e.getPlayer().performCommand("skills " + skill);
			return;
		}
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOW){
				if(!canPlayerUseWeapon(e.getPlayer())){
					e.setCancelled(true);
					e.getPlayer().sendMessage(StringStorage.smpt + ChatColor.RED + "You are not allowed to use a bow!");
					return;
				}
			}
		}
	}

	public static String entityTypeName(Entity e)
	{
		String enamelow = StringUtils.lowerCase(e.getType().name());
		String enamehigh;
		if (enamelow.contains("_"))
		{
			List<String> namelist = Arrays.asList(enamelow.split("_"));
			String np1 = StringUtils.capitalize((String)namelist.get(0));
			String np2 = StringUtils.capitalize((String)namelist.get(1));
			enamehigh = np1 + " " + np2;
		}
		else
		{
			enamehigh = StringUtils.capitalize(enamelow);
		}
		return enamehigh;
	}

	public static boolean canPlayerUseWeapon(Player p)
	{
		if (p.getGameMode() == GameMode.CREATIVE) {
			return true;
		}
		Material type = p.getInventory().getItemInMainHand().getType();
		if (type == Material.AIR) {
			return true;
		}
		if (!p.hasPermission("as.rpg.hasclass")) {
			p.sendMessage(ChatColor.RED + "To be able to use weapons, choose a class!");
			return false;
		}
		if (p.hasPermission("as.rpg.class.cleric"))
		{
			if (type == Material.STICK || type == Material.BLAZE_ROD || MaterialType.getMaterialType(type) == "SHOVEL") {
				return true;
			}else{
				return false;
			}
		}
		if (p.hasPermission("as.rpg.class.fighter"))
		{
			if (MaterialType.getMaterialType(type) == "SWORD" || MaterialType.getMaterialType(type) == "AXE") {
				return true;
			}else{
				return false;
			}
		}
		if (p.hasPermission("as.rpg.class.mage"))
		{
			if (type == Material.STICK || type == Material.BLAZE_ROD || MaterialType.getMaterialType(type) == "SWORD" || type == Material.SNOW_BALL || type == Material.BOW || type == Material.EGG) {
				return true;
			}else{
				return false;
			}
		}
		if (p.hasPermission("as.rpg.class.ranger"))
		{
			if ((type == Material.EGG) || (type == Material.BOW) || (type == Material.SNOW_BALL) || MaterialType.getMaterialType(type) == "SWORD") {
				return true;
			}else{
				return false;
			}
		}
		if (p.hasPermission("as.rpg.class.rouge"))
		{
			if (MaterialType.getMaterialType(type) == "SWORD" || MaterialType.getMaterialType(type) == "AXE" || type == Material.BOW) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
