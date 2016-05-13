package xyz.alphasurvival.commands;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.alphasurvival.StringStorage;

public class WebCommand implements Listener, CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(StringStorage.smpt + ChatColor.RED + "You must be a player to use this command!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("web")){
			try {
				Constructor<?> constructor = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"));;
				Object comp = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"Alpha Survival's Website\",\"color\":\"aqua\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://web.alphasurvival.xyz/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click me to go to AlphaSurvival's website!\",\"color\":\"green\"}]}}}");
				
				Object packet = constructor.newInstance(comp);
				sendPacket(player, packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	//Reflection Methods
	
		public void sendPacket(Player player, Object packet){
			try {
				Object handle = player.getClass().getMethod("getHandle").invoke(player);
				Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
				playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Class<?> getNMSClass(String name){
			String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
			try {
				return Class.forName("net.minecraft.server." + version + "." + name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

}
