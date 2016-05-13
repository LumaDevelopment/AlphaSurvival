package xyz.alphasurvival.listeners;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHeads implements Listener{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(e.getEntity().getName());
        meta.setDisplayName(e.getEntity().getName() + "'s Head");
        skull.setItemMeta(meta);
		e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), skull);
	}
	
}
