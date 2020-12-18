package com.lupus.commands.admin;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetSpawnCommand extends PlayerCommand {
	public SetSpawnCommand(){
		super("setspawn",
				Usage.usage("/plots setspawn","[dzialka]"),
				"&6Ustawia spawn działki",
				1);
	}
	@Override
	public void run(Player executor, String[] args) {
		if (args.length < 1) {
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.findRegion(args[0]);
		if (r == null) {
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
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
