package xyz.alphasurvival.rpg;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import xyz.alphasurvival.Main;

public class RPGScoreboard implements Listener{


	@EventHandler public void onPlayerJoin(PlayerJoinEvent e){ setupScoreboard(e.getPlayer()); }

	public static void setupScoreboard(Player p) {

		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard onJoin = sm.getNewScoreboard();
		Objective o = onJoin.registerNewObjective("dash", "dummy");

		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("" + ChatColor.BLUE + ChatColor.BOLD + "Alpha Survival");

		Score rpgScoreboard = null;
		Score lineone = null;
		Score classTitle = null;
		Score rpgClass = null;
		Score linetwo = null;
		Score manaTitle = null;
		Score mana = null;
		Score linethree = null;
		Score skillsTitle = null;
		Score linefour = null;
		Score end = null;
		
		List<String> skillList = Main.instance.getConfig().getStringList(p.getUniqueId() + ".Skills");

		try {
			
			if(skillList != null){
				rpgScoreboard = o.getScore(ChatColor.GOLD + "  RPG Scoreboard");
				rpgScoreboard.setScore(skillList.size() + 11);

				lineone = o.getScore(ChatColor.AQUA + "");
				lineone.setScore(skillList.size() + 10);

				classTitle = o.getScore(ChatColor.GREEN + "Class:");
				classTitle.setScore(skillList.size() + 9);

				if(p.hasPermission("as.rpg.class.cleric")){
					rpgClass = o.getScore(ChatColor.GOLD + "Cleric");
					rpgClass.setScore(skillList.size() + 8);
				}else if(p.hasPermission("as.rpg.class.fighter")){
					rpgClass = o.getScore(ChatColor.RED + "Fighter");
					rpgClass.setScore(skillList.size() + 8);
				}else if(p.hasPermission("as.rpg.class.mage")){
					rpgClass = o.getScore(ChatColor.LIGHT_PURPLE + "Mage");
					rpgClass.setScore(skillList.size() + 8);
				}else if(p.hasPermission("as.rpg.class.ranger")){
					rpgClass = o.getScore(ChatColor.GREEN + "Ranger");
					rpgClass.setScore(skillList.size() + 8);
				}else if(p.hasPermission("as.rpg.class.rouge")){
					rpgClass = o.getScore(ChatColor.DARK_RED + "Rouge");
					rpgClass.setScore(skillList.size() + 8);
				}else{
					rpgClass = o.getScore("Undecided");
					rpgClass.setScore(skillList.size() + 8);
				}

				linetwo = o.getScore(ChatColor.BLACK + "");
				linetwo.setScore(skillList.size() + 7);

				manaTitle = o.getScore(ChatColor.LIGHT_PURPLE + "Mana:");
				manaTitle.setScore(skillList.size() + 6);

				mana = o.getScore(ChatColor.AQUA + "" + ManaManager.getMana(p));
				mana.setScore(skillList.size() + 5);

				linethree = o.getScore(ChatColor.BLUE + "");
				linethree.setScore(skillList.size() + 4);
				
				skillsTitle = o.getScore(ChatColor.DARK_GREEN + "Skills:");
				skillsTitle.setScore(skillList.size() + 3);
				
				int count = 3;
				for(String skills : skillList){
					Score skill = o.getScore(skills);
					skill.setScore(count);
					count = count + 1;
				}
				
				linefour = o.getScore(ChatColor.DARK_AQUA + "");
				linefour.setScore(2);
				
				end = o.getScore(ChatColor.STRIKETHROUGH + "---------------");
				end.setScore(1);
			}else{
				Score noSkills = null;
				
				rpgScoreboard = o.getScore(ChatColor.GOLD + "RPG Scoreboard");
				rpgScoreboard.setScore(12);

				lineone = o.getScore(ChatColor.AQUA + "");
				lineone.setScore(11);

				classTitle = o.getScore(ChatColor.GREEN + "Class:");
				classTitle.setScore(10);

				if(p.hasPermission("as.rpg.class.cleric")){
					rpgClass = o.getScore(ChatColor.GOLD + "Cleric");
					rpgClass.setScore(9);
				}else if(p.hasPermission("as.rpg.class.fighter")){
					rpgClass = o.getScore(ChatColor.RED + "Fighter");
					rpgClass.setScore(9);
				}else if(p.hasPermission("as.rpg.class.mage")){
					rpgClass = o.getScore(ChatColor.LIGHT_PURPLE + "Mage");
					rpgClass.setScore(9);
				}else if(p.hasPermission("as.rpg.class.ranger")){
					rpgClass = o.getScore(ChatColor.GREEN + "Ranger");
					rpgClass.setScore(9);
				}else if(p.hasPermission("as.rpg.class.rouge")){
					rpgClass = o.getScore(ChatColor.DARK_RED + "Rouge");
					rpgClass.setScore(9);
				}else{
					rpgClass = o.getScore("Undecided");
					rpgClass.setScore(9);
				}

				linetwo = o.getScore(ChatColor.BLACK + "");
				linetwo.setScore(8);

				manaTitle = o.getScore(ChatColor.LIGHT_PURPLE + "Mana:");
				manaTitle.setScore(7);

				mana = o.getScore(ChatColor.AQUA + "" + ManaManager.getMana(p));
				mana.setScore(6);

				linethree = o.getScore(ChatColor.BLUE + "");
				linethree.setScore(5);
				
				skillsTitle = o.getScore(ChatColor.DARK_GREEN + "Skills:");
				skillsTitle.setScore(4);
				
				noSkills = o.getScore(ChatColor.RED + "You have no skills!");
				noSkills.setScore(3);
				
				linefour = o.getScore(ChatColor.DARK_AQUA + "");
				linefour.setScore(2);
				
				end = o.getScore(ChatColor.STRIKETHROUGH + "---------------");
				end.setScore(1);
			}

			p.setScoreboard(onJoin);

		} catch (Exception ex) {
			System.out.println(ex); 
		}
	}

}
