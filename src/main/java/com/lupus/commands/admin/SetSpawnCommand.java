package com.lupus.commands.admin;


import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetSpawnCommand extends PlayerCommand {
	public SetSpawnCommand(){
		super("setspawn",
				usage("/plots setspawn","[dzialka]"),
				"&6Ustawia spawn działki",
				1);
	}
	@Override
	public void run(Player executor, ArgumentList args) throws Exception {
		if (args.size() < 1) {
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.findRegion(args.getArg(String.class,0));
		if (r == null) {
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		r.setSpawn(executor.getLocation());
		executor.sendMessage(ChatColor.GREEN+ "Poprawnie ustawiono spawn działce " +r.getName());
	}

	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (args.length == 1) {
			String lastWord = args[args.length - 1];
			return RegionManager.findRegionsBeginningWith(lastWord);
		}
		else
			return super.tabComplete(sender, alias, args);
	}
}
