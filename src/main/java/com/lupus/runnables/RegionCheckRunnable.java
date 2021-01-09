package com.lupus.runnables;

import com.lupus.managers.PlayerInsideRegionManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RegionCheckRunnable extends BukkitRunnable {
	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(p == null){
				continue;
			}
			if(!p.getWorld().getName().equals(WorldUtils.getMainWorld().getName()))
				continue;
			boolean contains = false;
			Location loc = p.getLocation();
			for (Region region : RegionManager.getRegions()) {
				if(region == null){
					continue;
				}
				if (region.contains(loc)) {
					if (PlayerInsideRegionManager.getPlayerInRegion(p) != null) {
						contains = true;
						break;
					}
					p.sendMessage(ChatColor.BLUE + "Wchodzisz na działke " + region.getName());
					PlayerInsideRegionManager.setPlayerRegion(p,region);
					contains = true;
					break;
				}
			}

			if (!contains && PlayerInsideRegionManager.getPlayerInRegion(p) != null) {
				p.sendMessage(ChatColor.BLUE + "Wyszedłeś z działki");
				PlayerInsideRegionManager.setPlayerRegion(p,null);
			}
		}
	}
}
