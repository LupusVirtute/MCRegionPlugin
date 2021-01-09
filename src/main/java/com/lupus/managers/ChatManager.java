package com.lupus.managers;

import com.lupus.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ChatManager {
	static HashMap<UUID,UUID> playerRegionChat = new HashMap<>();
	public static Region getPlayerRegion(Player p){
		return getPlayerRegion(p.getUniqueId());
	}
	public static Region getPlayerRegion(UUID p){
		if (!playerRegionChat.containsKey(p))
			return null;
		UUID value = playerRegionChat.get(p);
		if (value == null)
			return null;
		return RegionManager.findRegion(value);
	}
	/**
	 * Removes default chat messages redirection
	 * @param p
	 */
	public static void removePlayerChat(Player p){
		removePlayerChat(p.getUniqueId());
	}
	public static void removePlayerChat(UUID p){
		playerRegionChat.remove(p);
	}

	/**
	 * Changes the default player chat that he will communicate through
	 * @param r Region members that players would communicate
	 * @param p Player that chat will be replaced
	 */
	public static void changePlayerRegionChat(@NotNull Region r,@NotNull Player p) {
		changePlayerRegionChat(r.getUniqueId(), p.getUniqueId());
	}
	/**
	 * Changes the default player chat that he will communicate through
	 * @param r Region members that players would communicate
	 * @param p Player that chat will be replaced
	 */
	public static void changePlayerRegionChat(@NotNull UUID r,@NotNull UUID p){
		playerRegionChat.put(p,r);
	}

}
