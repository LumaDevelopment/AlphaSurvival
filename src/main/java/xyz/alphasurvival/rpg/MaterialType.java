package xyz.alphasurvival.rpg;

import org.bukkit.Material;

public class MaterialType {
	
	public static String getMaterialType(Material m){
		if(m == Material.WOOD_SWORD || m == Material.STONE_SWORD || m == Material.GOLD_SWORD || m == Material.IRON_SWORD || m == Material.DIAMOND_SWORD){
			return "SWORD";
		}else if(m == Material.BOW){
			return "BOW";
		}else if(m == Material.WOOD_PICKAXE || m == Material.STONE_PICKAXE || m == Material.GOLD_PICKAXE || m == Material.IRON_PICKAXE || m == Material.DIAMOND_SWORD){
			return "PICKAXE";
		}else if(m == Material.WOOD_AXE || m == Material.STONE_AXE || m == Material.GOLD_AXE || m == Material.IRON_AXE || m == Material.DIAMOND_AXE){
			return "AXE";
		}else if(m == Material.WOOD_SPADE || m == Material.STONE_SPADE || m == Material.GOLD_SPADE || m == Material.IRON_SPADE || m == Material.DIAMOND_SPADE){
			return "SHOVEL";
		}else{
			return "OTHER";
		}
	}
}
