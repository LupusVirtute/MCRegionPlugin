package com.lupus.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldUtils {
	public static World getMainWorld(){
		return Bukkit.getWorlds().get(0);
	}
}
