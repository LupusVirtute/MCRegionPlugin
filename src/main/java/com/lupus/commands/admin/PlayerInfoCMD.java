package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerInfoCMD extends LupusCommand {
	public PlayerInfoCMD() {
		super("info",usage("/plots info","[player]"), "gives info about player",1);
	}

	@Override
	public void run(CommandSender commandSender, String[] strings) {
		Player p = Bukkit.getPlayerExact(strings[0]);
		if (p == null) {
			commandSender.sendMessage(PlotMessages.PLAYER_OFFLINE.toString());
			return;
		}
		commandSender.sendMessage(ColorUtil.text2Color("&e ---- "+p.getName()+"&e ----"));
		commandSender.sendMessage(ColorUtil.text2Color("&bOwner of: " + RegionManager.getRegionOfOwner(p).getName()));
		commandSender.sendMessage(ColorUtil.text2Color("&bMemberships: "));
		for (UUID playerMembership : RegionManager.getPlayerMemberships(p)) {
			commandSender.sendMessage(
			"\t -"+	RegionManager.findRegion(playerMembership).getName()
			);
		}
	}
}
