package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class PlayerFindCMD extends LupusCommand {
	public PlayerFindCMD() {
		super("player", usage("/plots player","[player]"), "Shows detailed info about player", 1);
	}

	@Override
	public void run(CommandSender commandSender, String[] args) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
		UUID playerUid = player.getUniqueId();
		Set<UUID> uuidSet = RegionManager.getPlayerMemberships(playerUid);
		String[] uuidArray = new String[uuidSet.size()];
		int i=0;
		for (UUID uuid : uuidSet) {
			uuidArray[i] = ChatColor.YELLOW+"-"+ ChatColor.DARK_PURPLE+RegionManager.findRegion(uuid).getName();
			i++;
		}
		commandSender.sendMessage(ColorUtil.text2Color("&8---------- &6"+args[0]+" &8----------"));
		commandSender.sendMessage(uuidArray);
	}
}
