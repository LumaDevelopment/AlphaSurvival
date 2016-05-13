package xyz.alphasurvival.rpg;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.Main;
import xyz.alphasurvival.StringStorage;

public class SkillCommand implements CommandExecutor,
Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.st + ChatColor.RED + "You must be a player to use skills!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("skills")){
			if(!p.hasPermission("as.rpg.hasclass")){
				p.sendMessage(StringStorage.st + ChatColor.RED + "You must have a class to use this command!");
				return true;
			}
			if(args.length == 1){
				List<String> skillList = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Skills");
				if(args[0].equalsIgnoreCase("list")){
					if(p.hasPermission("as.rpg.class.cleric")){
						listSkills(p, skillList, "Cleric");
						return true;
					}else if(p.hasPermission("as.rpg.class.fighter")){
						listSkills(p, skillList, "Fighter");
						return true;
					}else if(p.hasPermission("as.rpg.class.mage")){
						listSkills(p, skillList, "Mage");
						return true;
					}else if(p.hasPermission("as.rpg.class.ranger")){
						listSkills(p, skillList, "Ranger");
						return true;
					}else if(p.hasPermission("as.rpg.class.rouge")){
						p.sendMessage(StringStorage.st + ChatColor.RED + "The Rouge class dosn't have any skills!");
						return true;
					}
				}else{
					if(skillList != null){
						if(skillList.contains(args[0])){
							String skill = args[0];
							if(p.hasPermission("as.rpg.class.cleric")){
								if(skill.equalsIgnoreCase("SpadeMaster")){
									p.sendMessage(StringStorage.st + ChatColor.GREEN + "This skill is used automatically when you hit with a shovel!");
									return true;
								}else if(skill.equalsIgnoreCase("SmallHeal")){
									SkillManager.smallHealSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("MediumHeal")){
									SkillManager.mediumHealSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("HealingLook")){
									if(p.getEyeLocation().getBlock().getChunk().getEntities()[0] != null){
										Entity re = p.getEyeLocation().getBlock().getChunk().getEntities()[0];
										if(re instanceof LivingEntity){
											LivingEntity e = (LivingEntity) re;
											SkillManager.healingLookSkill(e, p);
											return true;
										}
									}else{
										p.sendMessage(StringStorage.st + ChatColor.RED + "There are no living entities in the chunk of the block you are looking at!");
										return true;
									}
								}else if(skill.equalsIgnoreCase("MegaHeal")){
									SkillManager.megaHealSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("HealingAura")){
									SkillManager.healingAuraSkill(p);
									return true;
								}
							}else if(p.hasPermission("as.rpg.class.fighter")){
								if(skill.equalsIgnoreCase("Leap")){
									SkillManager.leapSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Strengthening")){
									SkillManager.strengtheningSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Bolt")){
									Block b = p.getEyeLocation().getBlock();
									SkillManager.boltSkill(b, p);
								}
							}else if(p.hasPermission("as.rpg.class.mage")){
								if(skill.equalsIgnoreCase("StaffMaster")){
									p.sendMessage(StringStorage.st + ChatColor.GREEN + "This skill is automatically triggered when you hit with a stick or blaze rod!");
								}else if(skill.equalsIgnoreCase("ArrowLaunch")){
									SkillManager.arrowLaunchSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Fireball")){
									SkillManager.fireballSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Combustion")){
									int count = -1;
									for(Entity e : p.getEyeLocation().getBlock().getChunk().getEntities()){
										count = count + 1;
										if(e.getUniqueId() != p.getUniqueId()){
											break;
										}
									}
									if(count != 0){
										Entity re = p.getEyeLocation().getBlock().getChunk().getEntities()[count];
										if(re instanceof LivingEntity){
											LivingEntity e = (LivingEntity) re;
											SkillManager.combustionSkill(e, p);
											return true;
										}
									}else{
										p.sendMessage(StringStorage.st + ChatColor.RED + "There are no living entities in the chunk of the block you are looking at!");
										return true;
									}
								}else if(skill.equalsIgnoreCase("Burn")){
									SkillManager.burnSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Explosion")){
									SkillManager.explosionSkill(p.getEyeLocation(), p);
									return true;
								}else if(skill.equalsIgnoreCase("SelfDestruct")){
									SkillManager.selfDestructSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Invincibility")){
									SkillManager.selfDestructSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("DamageAura")){
									SkillManager.damageAuraSkill(p);
									return true;
								}
							}else if(p.hasPermission("as.rpg.class.ranger")){
								if(skill.equalsIgnoreCase("Jump")){
									SkillManager.jumpSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("Wolf")){
									SkillManager.wolfSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("SafeFall")){
									SkillManager.safeFallSkill(p);
									return true;
								}else if(skill.equalsIgnoreCase("MultiArrow")){
									SkillManager.multiArrowSkill(p);
									return true;
								}
							}
						}else{
							p.sendMessage(StringStorage.st + ChatColor.RED + "You don't have this skill! Skill names are case sensitive");
							return true;
						}
					}else{
						p.sendMessage(StringStorage.st + ChatColor.RED + "You don't have this skill!");
						return true;
					}
				}
			}else{
				p.sendMessage(StringStorage.st + ChatColor.RED + "Invalid usage! Usages: \"/skills list\" or \"/skills <skill>\"!");
				return true;
			}
		}
		
		return false;
	}
	
	public static void listSkills(Player p, List<String> skillList, String pClass){
		if(pClass == "Cleric"){
			if(skillList != null){
				for(String skillName : SkillManager.clericSkills()){
					if(skillList.contains(skillName)){
						p.sendMessage(skillName + " - " + SkillManager.clericSkillDescriptions().get(SkillManager.clericSkills().indexOf(skillName)));
					}else{
						if(skillName.equalsIgnoreCase("SpadeMaster")){
							p.sendMessage(skillName + ChatColor.RESET + " - " + SkillManager.clericSkillDescriptions().get(SkillManager.clericSkills().indexOf(skillName)));
						}else{
							p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.clericSkillDescriptions().get(SkillManager.clericSkills().indexOf(skillName)));
						}
					}
				}
			}else{
				for(String skillName : SkillManager.clericSkills()){
					p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.clericSkillDescriptions().get(SkillManager.clericSkills().indexOf(skillName)));
				}
			}
		}else if(pClass == "Fighter"){
			if(skillList != null){
				for(String skillName : SkillManager.fighterSkills()){
					if(skillList.contains(skillName)){
						p.sendMessage(skillName + " - " + SkillManager.fighterSkillDescriptions().get(SkillManager.fighterSkills().indexOf(skillName)));
					}else{
						p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.fighterSkillDescriptions().get(SkillManager.fighterSkills().indexOf(skillName)));
					}
				}
			}else{
				for(String skillName : SkillManager.fighterSkills()){
					p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.fighterSkillDescriptions().get(SkillManager.clericSkills().indexOf(skillName)));
				}
			}
		}else if(pClass == "Mage"){
			if(skillList != null){
				for(String skillName : SkillManager.mageSkills()){
					if(skillList.contains(skillName)){
						p.sendMessage(skillName + " - " + SkillManager.mageSkillDescriptions().get(SkillManager.mageSkills().indexOf(skillName)));
					}else{
						if(skillName.equalsIgnoreCase("StaffMaster")){
							p.sendMessage(skillName + ChatColor.RESET + " - " + SkillManager.mageSkillDescriptions().get(SkillManager.mageSkills().indexOf(skillName)));
						}else{
							p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.mageSkillDescriptions().get(SkillManager.mageSkills().indexOf(skillName)));
						}
					}
				}
			}else{
				for(String skillName : SkillManager.mageSkills()){
					p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.mageSkillDescriptions().get(SkillManager.mageSkills().indexOf(skillName)));
				}
			}
		}else if(pClass == "Ranger"){
			if(skillList != null){
				for(String skillName : SkillManager.rangerSkills()){
					if(skillList.contains(skillName)){
						p.sendMessage(skillName + " - " + SkillManager.rangerSkillDescriptions().get(SkillManager.rangerSkills().indexOf(skillName)));
					}else{
						p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.rangerSkillDescriptions().get(SkillManager.rangerSkills().indexOf(skillName)));
					}
				}
			}else{
				for(String skillName : SkillManager.rangerSkills()){
					p.sendMessage(ChatColor.STRIKETHROUGH + skillName + ChatColor.RESET + " - " + SkillManager.rangerSkillDescriptions().get(SkillManager.rangerSkills().indexOf(skillName)));
				}
			}
		}
	}
	
}
