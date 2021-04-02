package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.RegionManager;
import com.lupus.gui.utils.TextUtility;
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
	public void run(CommandSender commandSender, ArgumentList args) throws Exception {
		OfflinePlayer player = args.getArg(OfflinePlayer.class,0);
		UUID playerUid = player.getUniqueId();
		Set<UUID> uuidSet = RegionManager.getPlayerMemberships(playerUid);
		String[] uuidArray = new String[uuidSet.size()];
		int i=0;
		for (UUID uuid : uuidSet) {
			uuidArray[i] = ChatColor.YELLOW+"-"+ ChatColor.DARK_PURPLE+RegionManager.findRegion(uuid).getName();
			i++;
		}
		commandSender.sendMessage(TextUtility.color("&8---------- &6"+args.get(0)+" &8----------"));
		commandSender.sendMessage(uuidArray);
	}
}
