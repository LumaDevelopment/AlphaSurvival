package xyz.alphasurvival.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class NPCMSGCommand implements CommandExecutor,
Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player){
			sender.sendMessage("Unknown command. Use \"/help\" for help.");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("npcmsg")){
			if(args.length == 0 || args.length == 1 || args.length == 3){
				sender.sendMessage("Use: /npcmsg <playername> <npcname> <message>");
				return true;
			}
			
			String msg = StringUtils.join(args, ' ', 2, args.length);
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null){
				sender.sendMessage(args[0] + " is not online!");
				return true;
			}
			
			String finalmsg = "" + ChatColor.GREEN + ChatColor.BOLD + "NPC " + ChatColor.RESET + ChatColor.AQUA + args[1] + ChatColor.GRAY + " >>> " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', msg);
			target.sendMessage(finalmsg);
			
			return true;
			
		}
		
		return false;
	}

}
