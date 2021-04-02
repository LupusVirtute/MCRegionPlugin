package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.CacheManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerInfoCMD extends LupusCommand {
	public PlayerInfoCMD() {
		super("info",usage("/plots info","[player]"), "gives info about player",1);
	}

	@Override
	public void run(CommandSender commandSender, ArgumentList args) throws Exception {
		Player p = args.getArg(Player.class,0);
		commandSender.sendMessage(TextUtility.color("&e ---- "+p.getName()+"&e ----"));
		commandSender.sendMessage(TextUtility.color("&bOwner of: " + RegionManager.getRegionOfOwner(p).getName()));
		commandSender.sendMessage(TextUtility.color("&bMemberships: "));
		for (UUID playerMembership : RegionManager.getPlayerMemberships(p)) {
			commandSender.sendMessage(
			"\t -"+	RegionManager.findRegion(playerMembership).getName()
			);
		}
	}
}
