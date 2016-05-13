package xyz.alphasurvival.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TNTDisabler implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
    public void explodeHeight(EntityExplodeEvent e) {
        if(e.getEntityType() == EntityType.PRIMED_TNT) {
            e.blockList().clear();
        }
    }

}
