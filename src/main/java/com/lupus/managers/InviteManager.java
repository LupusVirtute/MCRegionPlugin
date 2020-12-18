package com.lupus.managers;

import com.lupus.region.Region;
import com.lupus.messages.PlotMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class InviteManager {
	static HashMap<UUID, Region> invites = new HashMap<>();
	public static void clear(){
		invites.clear();
	}
	public static void addInvite(Player player, Region region){
		addInvite(player.getUniqueId(),region);
	}
	public static void addInvite(UUID player, Region region){
		invites.put(player,region);
		Player p = Bukkit.getPlayer(player);
		if (p != null){
			p.sendMessage(PlotMessages.INVITE_SEND.toString(region.getOwnerName()));
		}
	}
	public static void removeInvite(Player player){
		removeInvite(player.getUniqueId());
	}
	public static void removeInvite(UUID player){
		invites.remove(player);
	}
	public static Region getInvite(Player player){
		return getInvite(player.getUniqueId());
	}
	public static Region getInvite(UUID player){
		return invites.get(player);
	}
}
