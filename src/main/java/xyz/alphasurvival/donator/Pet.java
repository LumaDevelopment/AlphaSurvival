package xyz.alphasurvival.donator;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import xyz.alphasurvival.Main;

public class Pet {

	Entity pet;
	String name;
	UUID owner_uuid;
	
	public Pet(Entity e){
		pet = e;
	}

	public Entity getEntity(){
		if(this.hasEntity()){
			return pet;
		}else{
			return null;
		}
	}
	
	public UUID getOwnerUUID(){
		if(owner_uuid != null){
			return owner_uuid;
		}else{
			return null;
		}
	}
	
	public String getEntityName(boolean spaces){
		if(this.hasEntity()){
			Entity ent = this.getEntity();
			String ent_name = ent.getName();
			ent_name.toLowerCase();
			StringUtils.capitalize(ent_name);
			if(spaces == true){
				return ent_name;
			}else{
				ent_name.trim();
				return ent_name;
			}
		}else{
			return null;
		}
	}
	
	public void setName(String new_name){
		if(this.hasEntity()){
			name = new_name;
			Main.instance.getConfig().set(this.getOwnerUUID() + "-" + this.getEntityName(false) + ".Name", new_name);
			Main.instance.saveConfig();
			this.getEntity().setCustomName(new_name);
		}
	}
	
	public boolean isEntityPet(Entity e){
		if(pet.getUniqueId() == e.getUniqueId()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasEntity(){
		if(pet != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void updatePet(){
		if(this.hasEntity()){
			Player p = Bukkit.getPlayer(this.getOwnerUUID());
			if(p != null){
				this.getEntity().setCustomName(Main.instance.getConfig().getString(this.getOwnerUUID() + "-" + this.getEntityName(false) + ".Name"));
				this.getEntity().setCustomNameVisible(true);
				this.getEntity().teleport(p);
				Creature creature = (Creature) this.getEntity();
				LivingEntity pentity = (LivingEntity) p;
				creature.setTarget(pentity);
			}
		}else{
			this.getEntity().remove();
			return;
		}
	}
	
	public void createData(UUID p_uuid){
		Main.instance.getConfig().set(this.getOwnerUUID() + "-" + this.getEntityName(false) + ".Name", this.getEntityName(true));
		Main.instance.getConfig().set(this.getOwnerUUID() + "-" + this.getEntityName(false) + ".UUID", p_uuid);
		Main.instance.saveConfig();
		this.getEntity().setCustomName(this.getOwnerUUID() + "-" + this.getEntityName(false) + ".Name");
		this.getEntity().setCustomNameVisible(true);
		this.updatePet();
	}
	
	public boolean isPetDataNull(){
		if(this.hasEntity()){
			if(Main.instance.getConfig().getConfigurationSection(this.getOwnerUUID() + "-" + this.getEntityName(false)) != null){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
