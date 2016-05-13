package xyz.alphasurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PointGiver implements Listener{

	@EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Monster)
        {
            Monster monsterEnt = (Monster) event.getEntity();
            if(monsterEnt.getKiller() instanceof Player)
            {
            	Player p = monsterEnt.getKiller();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "enjin addpoints " + p.getName() + " 1");
            }
        }
    }
	
}
