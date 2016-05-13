package xyz.alphasurvival.listeners;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Title implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(e.getPlayer().hasPlayedBefore() == false){
			try {
				Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
				Object titletext = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.GOLD + "Welcome to the server," + "\"}");
				
				Object enumSubTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
				Object subtitletext = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.AQUA + e.getPlayer().getDisplayName() + "\"}");
				
				Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
				
				Object packet = titleConstructor.newInstance(enumTitle, titletext, 10, 20, 10);
				Object packet2 = titleConstructor.newInstance(enumSubTitle, subtitletext, 10, 20, 10);
				
				sendPacket(e.getPlayer(), packet);
				sendPacket(e.getPlayer(), packet2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			try {
				Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
				Object titletext = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.GREEN + "Welcome back to AlphaSurvival," + "\"}");
				
				Object enumSubTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
				Object subtitletext = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.AQUA + e.getPlayer().getDisplayName() + "\"}");
				
				Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
				
				Object packet = titleConstructor.newInstance(enumTitle, titletext, 10, 20, 10);
				Object packet2 = titleConstructor.newInstance(enumSubTitle, subtitletext, 10, 20, 10);
				
				sendPacket(e.getPlayer(), packet);
				sendPacket(e.getPlayer(), packet2);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
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
