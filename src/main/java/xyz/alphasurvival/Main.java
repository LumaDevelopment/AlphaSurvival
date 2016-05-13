package xyz.alphasurvival;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import xyz.alphasurvival.commands.EnderChestCommand;
import xyz.alphasurvival.commands.FeedCommand;
import xyz.alphasurvival.commands.GameNotesCommand;
import xyz.alphasurvival.commands.GiveawayCommand;
import xyz.alphasurvival.commands.HealCommand;
import xyz.alphasurvival.commands.HelpOverride;
import xyz.alphasurvival.commands.HomeCommand;
import xyz.alphasurvival.commands.KeyGiveawayCommand;
import xyz.alphasurvival.commands.ListCommand;
import xyz.alphasurvival.commands.MessageCommand;
import xyz.alphasurvival.commands.NPCMSGCommand;
import xyz.alphasurvival.commands.NicknameCommand;
import xyz.alphasurvival.commands.RanksCommand;
import xyz.alphasurvival.commands.RulesCommand;
import xyz.alphasurvival.commands.SpawnCommand;
import xyz.alphasurvival.commands.WarpCommand;
import xyz.alphasurvival.commands.WebCommand;
import xyz.alphasurvival.commands.WorkbenchCommand;
import xyz.alphasurvival.listeners.CustomSign;
import xyz.alphasurvival.listeners.PlayerHeads;
import xyz.alphasurvival.listeners.PlayerListener;
import xyz.alphasurvival.listeners.PlayerRanks;
import xyz.alphasurvival.listeners.PointGiver;
import xyz.alphasurvival.listeners.TNTDisabler;
import xyz.alphasurvival.listeners.Title;
import xyz.alphasurvival.protection.OpOverride;
import xyz.alphasurvival.protection.PexProtection;
import xyz.alphasurvival.rpg.ClassCommand;
import xyz.alphasurvival.rpg.ItemUseManager;
import xyz.alphasurvival.rpg.LinkingCommands;
import xyz.alphasurvival.rpg.ManaManager;
import xyz.alphasurvival.rpg.RPGScoreboard;
import xyz.alphasurvival.rpg.SkillCommand;
import xyz.alphasurvival.rpg.SkillManager;
import xyz.alphasurvival.rpg.StatsCommand;
import xyz.alphasurvival.rpg.StatsManager;

public class Main extends JavaPlugin{
	
	public static int task = 0;
	public static HashMap<UUID, Integer> manaMap = new HashMap<UUID, Integer>();
	
	public static Main instance;
	
	public static Main getInstance(){
		return instance;
	}
	
	public void consoleBroadcast(String s){
		Bukkit.getServer().getLogger().info(s);
	}
	
	public void registerCommands(){
		getCommand("help").setExecutor(new HelpOverride());
		getCommand("?").setExecutor(new HelpOverride());
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("rules").setExecutor(new RulesCommand());
		getCommand("gamenotes").setExecutor(new GameNotesCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("web").setExecutor(new WebCommand());
		getCommand("giveaway").setExecutor(new GiveawayCommand());
		getCommand("keygiveaway").setExecutor(new KeyGiveawayCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("op").setExecutor(new OpOverride());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("workbench").setExecutor(new WorkbenchCommand());
		getCommand("enderchest").setExecutor(new EnderChestCommand());
		getCommand("nickname").setExecutor(new NicknameCommand());
		getCommand("npcmsg").setExecutor(new NPCMSGCommand());
		getCommand("class").setExecutor(new ClassCommand());
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("skills").setExecutor(new SkillCommand());
		getCommand("link").setExecutor(new LinkingCommands());
		getCommand("unlink").setExecutor(new LinkingCommands());
		getCommand("list").setExecutor(new ListCommand());
		getCommand("ranks").setExecutor(new RanksCommand());
	}
	
	public void registerClasses(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		//RPG Classes
		pm.registerEvents(new ClassCommand(), this);
		pm.registerEvents(new ManaManager(), this);
		pm.registerEvents(new RPGScoreboard(), this);
		pm.registerEvents(new SkillCommand(), this);
		pm.registerEvents(new SkillManager(), this);
		pm.registerEvents(new StatsCommand(), this);
		pm.registerEvents(new StatsManager(), this);
		pm.registerEvents(new LinkingCommands(), this);
		pm.registerEvents(new ItemUseManager(), this);
		
		//Essentials Classes
		pm.registerEvents(new HealCommand(), this);
		pm.registerEvents(new FeedCommand(), this);
		pm.registerEvents(new WorkbenchCommand(), this);
		pm.registerEvents(new EnderChestCommand(), this);
		pm.registerEvents(new NicknameCommand(), this);
		
		//Listener Classes
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new PlayerRanks(), this);
		pm.registerEvents(new TNTDisabler(), this);
		pm.registerEvents(new CustomSign(), this);
		pm.registerEvents(new PlayerHeads(), this);
		pm.registerEvents(new PointGiver(), this);
		
		//Packet Classes
		pm.registerEvents(new Title(), this);
		
		//Command Classes
		pm.registerEvents(new HelpOverride(), this);
		pm.registerEvents(new MessageCommand(), this);
		pm.registerEvents(new RulesCommand(), this);
		pm.registerEvents(new GameNotesCommand(), this);
		pm.registerEvents(new WebCommand(), this);
		pm.registerEvents(new GiveawayCommand(), this);
		pm.registerEvents(new KeyGiveawayCommand(), this);
		pm.registerEvents(new NPCMSGCommand(), this);
		pm.registerEvents(new ListCommand(), this);
		pm.registerEvents(new RanksCommand(), this);
		
		//Teleportation Classes
		pm.registerEvents(new SpawnCommand(), this);
		pm.registerEvents(new HomeCommand(), this);
		pm.registerEvents(new WarpCommand(), this);
		
		//Protection Classes
		pm.registerEvents(new OpOverride(), this);
		pm.registerEvents(new PexProtection(), this);
	}
	
	public void makePowerStatPotion(){
		ItemStack potion = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "Power Stat Potion");
		potion.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(potion);
		recipe.shape("ARA", "ADA", "ABA");
		
		recipe.setIngredient('A', Material.REDSTONE);
		recipe.setIngredient('R', Material.REDSTONE);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('B', Material.GLASS_BOTTLE);
		
		getServer().addRecipe(recipe);
	}
	
	public void makeSpeedStatPotion(){
		ItemStack potion = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Speed Stat Potion");
		potion.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(potion);
		recipe.shape("ARA", "ADA", "ABA");
		
		recipe.setIngredient('A', Material.SUGAR);
		recipe.setIngredient('R', Material.SUGAR);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('B', Material.GLASS_BOTTLE);
		
		getServer().addRecipe(recipe);
	}
	
	public void makeDefenseStatPotion(){
		ItemStack potion = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName("" + ChatColor.BLUE + ChatColor.BOLD + "Defense Stat Potion");
		potion.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(potion);
		recipe.shape("ARA", "ADA", "ABA");
		
		recipe.setIngredient('A', Material.WOOD);
		recipe.setIngredient('R', Material.SHIELD);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('B', Material.GLASS_BOTTLE);
		
		getServer().addRecipe(recipe);
	}
	
	public void makeManaSkillStatPotion(){
		ItemStack potion = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta meta = potion.getItemMeta();
		meta.setDisplayName("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Mana Skill Stat Potion");
		potion.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(potion);
		recipe.shape("ARA", "ADA", "ABA");
		
		recipe.setIngredient('A', Material.BLAZE_POWDER);
		recipe.setIngredient('R', Material.EYE_OF_ENDER);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('B', Material.GLASS_BOTTLE);
		
		getServer().addRecipe(recipe);
	}
	
	public void registerRecipes(){
		makePowerStatPotion();
		makeSpeedStatPotion();
		makeDefenseStatPotion();
		makeManaSkillStatPotion();
	}
	
	public void onEnable(){
	    PluginDescriptionFile pdf = getDescription();
		consoleBroadcast("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " by " + pdf.getAuthors() + " has been enabled.");
		consoleBroadcast("Description: " + pdf.getDescription());
		instance = this;
		registerClasses();
		registerCommands();
		getConfig().options().copyDefaults(true);   
		saveDefaultConfig();
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					if(!manaMap.containsKey(p.getUniqueId())){
						manaMap.put(p.getUniqueId(), 0);
					}
					manaMap.replace(p.getUniqueId(), manaMap.get(p.getUniqueId()) + 1);
					ASPlayer asp = new ASPlayer(p);
					if(manaMap.get(p.getUniqueId()) >= 10 - (asp.getManaRegenSpeed() / 5)){
						manaMap.replace(p.getUniqueId(), 0);
						if(asp.getMana() >= 10){
							return;
						}
						asp.setMana(asp.getMana() + 1);
						asp.setupScoreboard();
					}else{
						return;
					}
				}
			}
		}, 0, 1 * 20);
		
		registerRecipes();
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = getDescription();
		consoleBroadcast("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " by " + pdf.getAuthors() + " has been disabled.");
		saveConfig();
		instance = null;
	}
	
	public static Vector getVectorForPoints(Location l1, Location l2) {
		double g = -0.08;
        double d = l2.distance(l1);
        double t = d;
        double vX = (1.0+0.07 * t) * (l2.getX() - l1.getX()) / t;
        double vY = (1.0+0.03 * t) * (l2.getY() - l1.getY()) / t - 0.5 * g * t;
        double vZ = (1.0+0.07 * t) * (l2.getZ() - l1.getZ()) / t;
        return new Vector(vX, vY, vZ);
	}
	
}
