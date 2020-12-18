package com.lupus.region;


import com.lupus.managers.RegionManager;
import com.lupus.utils.WorldUtils;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Korwin at https://github.com/PuccyDestroyerxXx
 */
public class RegionUtil {
	static Plugin worldGuard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	public static boolean isValidNewRegionLocation(Location loc) {
		if(!WorldUtils.getMainWorld().getUID().equals(loc.getWorld().getUID()))
		{
			return false;
		}
		for (int i = 0; i < RegionManager.getRegionAmount(); i++) {
			Region r = RegionManager.findRegion(i);
			if (r == null) {
				continue;
			}
			if (r.isLocationNearRegion(loc)) {
				return false;
			}
		}
		if (worldGuard == null)
			return true;
		if (!(worldGuard instanceof WorldGuardPlugin)) {
			return true;
		}
		WorldGuardPlugin worldGuardPlugin = (WorldGuardPlugin) worldGuard;
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		com.sk89q.worldguard.protection.managers.RegionManager regs = container.get(WorldGuard.
				getInstance().
				getPlatform().
				getMatcher().
				getWorldByName(WorldUtils.
						getMainWorld().
						getName()
				)
		);
		if (regs == null) {
			return true;
		}
		for (ProtectedRegion region : regs.getRegions().values()) {
			boolean result1 = region.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			boolean result2 = region.contains(loc.getBlockX() + 96, loc.getBlockY() + 96, loc.getBlockZ() + 96);
			boolean result3 = region.contains(loc.getBlockX() - 96, loc.getBlockY() - 96, loc.getBlockZ() - 96);
			if (result1 || result2 || result3) {
				return false;
			}
		}
		return true;
	}
}
