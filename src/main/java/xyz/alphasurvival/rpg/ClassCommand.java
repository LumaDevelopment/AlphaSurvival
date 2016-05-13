package xyz.alphasurvival.rpg;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class ClassCommand implements CommandExecutor,
Listener{

	HashMap<String, String> map = new HashMap<String, String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to pick a class!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("class")){
			if(args.length == 0){
				if(p.hasPermission("as.rpg.hasclass")){
					if(p.hasPermission("as.rpg.class.cleric")){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are a " + ChatColor.AQUA + "Cleric" + ChatColor.GREEN + "!");
						return true;
					}else if(p.hasPermission("as.rpg.class.fighter")){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are a " + ChatColor.AQUA + "Fighter" + ChatColor.GREEN + "!");
						return true;
					}else if(p.hasPermission("as.rpg.class.mage")){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are a " + ChatColor.AQUA + "Mage" + ChatColor.GREEN + "!");
						return true;
					}else if(p.hasPermission("as.rpg.class.ranger")){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are a " + ChatColor.AQUA + "Ranger" + ChatColor.GREEN + "!");
						return true;
					}else if(p.hasPermission("as.rpg.class.rouge")){
						p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are a " + ChatColor.AQUA + "Rouge" + ChatColor.GREEN + "!");
						return true;
					}
				}else if(!p.hasPermission("as.rpg.hasclass")){
					p.sendMessage(StringStorage.smpt + ChatColor.RED + "You have not picked a class! Use \"/class list\" to see a list of classes!");
					return true;
				}
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("list")){
					p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "A list of classes:");
					p.sendMessage("- Cleric");
					p.sendMessage("- Fighter");
					p.sendMessage("- Mage");
					p.sendMessage("- Ranger");
					p.sendMessage("- Rouge");
					return true;
				}
				else if(args[0].equalsIgnoreCase("wait")){
					if(map.containsKey(p.getName())){
						map.remove(p.getName());
						p.sendMessage(StringStorage.smpt + "You have been removed from the \"Choosing Class\" list.");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You're not on the \"Choosing Class\" list!");
						return true;
					}
				}else{
					if(p.hasPermission("as.rpg.hasclass")){
						p.sendMessage(StringStorage.smpt + ChatColor.RED + "You've already chosen a class!");
						return true;
					}
					if(args[0].equalsIgnoreCase("cleric")){
						if(map.containsKey(p.getName()) && map.containsValue("Cleric")){
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", -1);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", 1);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", 2);
							Main.instance.saveConfig();
						}
						chooseClass(p, "Cleric");
						return true;
					}else if(args[0].equalsIgnoreCase("fighter")){
						if(map.containsKey(p.getName()) && map.containsValue("Fighter")){
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", 0);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", 0);
							Main.instance.saveConfig();
						}
						chooseClass(p, "Fighter");
						return true;
					}else if(args[0].equalsIgnoreCase("mage")){
						if(map.containsKey(p.getName()) && map.containsValue("Mage")){
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", 0);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", 0);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", 2);
							Main.instance.saveConfig();
						}
						chooseClass(p, "Mage");
						return true;
					}else if(args[0].equalsIgnoreCase("ranger")){
						if(map.containsKey(p.getName()) && map.containsValue("Ranger")){
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", 1);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", 1);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", 0);
							Main.instance.saveConfig();
						}
						chooseClass(p, "Ranger");
						return true;
					}else if(args[0].equalsIgnoreCase("rouge")){
						if(map.containsKey(p.getName()) && map.containsValue("Rouge")){
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Power", 3);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Defense", 2);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.Speed", -1);
							Main.instance.getConfig().set(p.getUniqueId() + ".Stats.ManaSkill", 0);
							Main.instance.saveConfig();
						}
						chooseClass(p, "Rouge");
						return true;
					}else{
						p.sendMessage(StringStorage.smpt + ChatColor.AQUA + args[0] + ChatColor.RED + " isn't a class!");
						return true;
					}
				}
			}else{
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "Invalid usage! Usages: \"/class list\", \"/class <class>\", and \"/class wait\".");
				return true;
			}
		}
		
		return false;
	}
	
	private void chooseClass(Player p, String rpgclass){
		if(!map.containsKey(p.getName())){
			map.put(p.getName(), rpgclass);
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "If you want to choose this class, use \"/class " + rpgclass + "\" again!");
			p.sendMessage(ChatColor.RED + "If not, use \"/class wait\".");
			return;
		}else{
			if(!map.get(p.getName()).equals(rpgclass)){
				p.sendMessage(StringStorage.smpt + ChatColor.RED + "You are on the \"Choosing List\" to become a " + ChatColor.AQUA + map.get(p.getName()) + ChatColor.RED + ", not a " + ChatColor.AQUA + rpgclass + ChatColor.RED + "!");
				p.sendMessage(ChatColor.GREEN + "If you would like to be a " + ChatColor.AQUA + rpgclass + ChatColor.GREEN + ", then use \"/class wait\", \"/class " + rpgclass + "\", and then \"/class " + rpgclass + "\" again!");
				return;
			}
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add as.rpg.class." + StringUtils.lowerCase(rpgclass));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add kit." + StringUtils.lowerCase(rpgclass));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add as.rpg.hasclass");
			p.sendMessage(StringStorage.smpt + ChatColor.GREEN + "You are now a " + ChatColor.AQUA + rpgclass + ChatColor.GREEN + "!");
			for(Player player : Bukkit.getOnlinePlayers()){
				RPGScoreboard.setupScoreboard(player);
			}
			return;
		}
	}

}
