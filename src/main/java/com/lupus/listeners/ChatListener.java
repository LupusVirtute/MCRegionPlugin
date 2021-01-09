package com.lupus.listeners;

import com.lupus.managers.ChatManager;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class ChatListener implements Listener {

	static String seeChatPerm = "plots.chat.see";
	@EventHandler(ignoreCancelled = true)
	public void onASyncChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		Region r = ChatManager.getPlayerRegion(p);
		if (r == null)
			return;
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(r.getName());
		builder.append("&f] ");
		builder.append(p.getName());
		builder.append(" &8&l>> &r");
		builder.append(e.getMessage());
		String message = ColorUtil.text2Color(builder.toString());
		Bukkit.broadcast(ChatColor.GRAY+"[AV] "+ ChatColor.RESET+message,seeChatPerm);
		Bukkit.getLogger().info(message);
		for (UUID member : r.getMembers()) {
			Player recipent = Bukkit.getPlayer(member);
			if (recipent == null)
				continue;
			if (!recipent.hasPermission(seeChatPerm))
				recipent.sendMessage(message);
		}
	}
}
