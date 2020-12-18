package com.lupus.runnables;

import com.lupus.managers.RegionManager;
import org.bukkit.scheduler.BukkitRunnable;

public class BackupRunnable extends BukkitRunnable {
	@Override
	public void run() {
		RegionManager.saveRegions(true);
	}
}
