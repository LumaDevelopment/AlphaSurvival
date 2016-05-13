package xyz.alphasurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class HelpOverride implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	
		if(cmd.getName().equalsIgnoreCase("help")){
			CommandSender p = sender;
			p.sendMessage(ChatColor.BLACK + "----------[" + ChatColor.BLUE + "AS Help" + ChatColor.BLACK + "]----------");
			p.sendMessage("/rules - See the server rules.");
			p.sendMessage("/m/msg/message <player> <message> - Sends <message> to <player>");
			p.sendMessage("/rg define <region name> - Claim your region. To make a region, and then left click one corner block, next, right click another, and your region will be the space inside.");
			p.sendMessage("/rg flag <region name> use deny - If you have buttons or doors, then you can use this to make sure people don't use these items.");
			p.sendMessage("/warp help - See help for /warp");
			p.sendMessage("/home help - See help for /home.");
			p.sendMessage("/spawn - Use this command to go back to spawn!");
			p.sendMessage("/gamenotes - Use this command to see different game modifications.");
			p.sendMessage("/web - Use this command to get a link to our website.");
			p.sendMessage("/ranks - You can use this to see your ranks.");
			p.sendMessage("RPG Commands - See them here:");
			p.sendMessage("http://alphasurvivalweb.enjin.com/rpghelp#");
			p.sendMessage(ChatColor.BLACK + "------------------------------");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("?")){
			sender.sendMessage(ChatColor.BLACK + "[" + ChatColor.GRAY + "SMP" + ChatColor.BLACK + "]" + ChatColor.RED + " The command for help is \"/help\".");
			return true;
		}
		
		return false;
	}

}
