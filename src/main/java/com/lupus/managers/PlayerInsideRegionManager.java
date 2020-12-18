package com.lupus.managers;

import com.lupus.region.Region;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInsideRegionManager {
	public static HashMap<UUID,UUID> playerRegionLocalizator = new HashMap<>();
	public static void setPlayerRegion(Player p, Region r){
		if (r != null)
			setPlayerRegion(p.getUniqueId(),r.getUniqueId());
		else
			setPlayerRegion(p.getUniqueId(),null);
	}
	public static void setPlayerRegion(UUID p,UUID r){
		playerRegionLocalizator.put(p,r);
	}
	public static Region getPlayerRegionHeIsInsideOf(Player p){
		return RegionManager.findRegion(
				getPlayerInRegion(p.getUniqueId())
		);
	}
	public static UUID getPlayerInRegion(Player p){
		return getPlayerInRegion(p.getUniqueId());
	}
	public static UUID getPlayerInRegion(UUID p){
		return playerRegionLocalizator.get(p);
	}
}
