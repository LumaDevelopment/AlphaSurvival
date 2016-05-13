package xyz.alphasurvival;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import xyz.alphasurvival.rpg.ManaManager;
import xyz.alphasurvival.rpg.RPGScoreboard;
import xyz.alphasurvival.rpg.StatsManager;

public class ASPlayer{

	Player player;
	
	public ASPlayer(Player p){
		player = p;
	}
	
	public Player getPlayer(){
		if(player != null){
			return player;
		}else{
			return null;
		}
	}
	
	public void sendMessage(String msg){
		if(this.getPlayer() != null){
			player.sendMessage(msg);
		}else{
			return;
		}
	}
	
	public void setupScoreboard(){
		if(player != null){
			RPGScoreboard.setupScoreboard(player);
		}else{
			return;
		}
	}
	
	public int getPowerStat(){
		if(player != null){
			return StatsManager.getPowerStat(player);
		}else{
			return 0;
		}
	}
	
	public int getDefenseStat(){
		if(player != null){
			return StatsManager.getDefenseStat(player);
		}else{
			return 0;
		}
	}
	
	public int getSpeedStat(){
		if(player != null){
			return StatsManager.getSpeedStat(player);
		}else{
			return 0;
		}
	}
	
	public int getManaSkillStat(){
		if(player != null){
			return StatsManager.getManaSkillStat(player);
		}else{
			return 0;
		}
	}
	
	public void setManaRegenSpeed(int speed){
		if(player != null){
			ManaManager.setManaRegenSpeed(speed, player);
		}else{
			return;
		}
	}

	public int getManaRegenSpeed(){
		if(player != null){
			return StatsManager.getManaSkillStat(player);
		}else{
			return 0;
		}
	}
	
	public boolean hasMana(double mana){
		if(player != null){
			if(ManaManager.getMana(player) >= mana){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void takeMana(Double mana){
		if(player != null){
			ManaManager.takeMana(mana, player);
		}else{
			return;
		}
	}
	
	public double getMana(){
		if(player != null){
			return ManaManager.getMana(player);
		}else{
			return 0;
		}
	}
	
	public void setMana(Double mana){
		if(player != null){
			ManaManager.setMana(mana, player);
		}else{
			return;
		}
	}
	
	public List<String> getRanks(){
		if(player != null){
			Player p = player;
			List<String> ranks = new ArrayList<String>();
			if(p.hasPermission("as.rank.mvp")){
				ranks.add("VIP");
			}
			if(p.hasPermission("as.rank.scorpius")){
				ranks.add("Scorpius");
			}
			if(p.hasPermission("as.rank.orion")){
				ranks.add("Orion");
			}
			if(p.hasPermission("as.rank.mvp+")){
				ranks.add("MVP+");
			}
			if(p.hasPermission("as.rank.donator")){
				ranks.add("Donator");
			}
			if(p.hasPermission("as.rank.donator+")){
				ranks.add("Donator+");
			}
			if(p.hasPermission("as.rank.builder")){
				ranks.add("Builder");
			}
			if(p.hasPermission("as.rank.mod")){
				ranks.add("Mod");
			}
			if(p.hasPermission("as.rank.admin")){
				ranks.add("Admin");
			}
			if(p.hasPermission("as.rank.owner")){
				ranks.add("Owner");
			}
			return ranks;
		}else{
			return null;
		}
	}
	
	public boolean hasRank(String rank){
		if(player != null){
			if(this.getRanks().contains(rank)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void giveRank(String rank){
		if(player != null){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " groups add " + rank);
		}else{
			return;
		}
	}
	
	public void resetRanks(){
		if(player != null){
			ASPlayer asp = new ASPlayer(player);
			for(String rank : asp.getRanks()){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " groups remove " + rank);
			}
		}else{
			return;
		}
	}
	
}
