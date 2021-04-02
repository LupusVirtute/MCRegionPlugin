package com.lupus.commands.admin;

import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GoToCommand extends PlayerCommand {
	public GoToCommand(){
		super("goto", usage("/plots goto","[Dzialka]"),"&6Idziesz do działki",1);
	}

	@Override
	protected void run(Player player,ArgumentList args) throws Exception {
		Region r = args.getArg(Region.class,0);
		Location spawn = r.getSpawn();
		player.teleport(spawn);
	}
	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (args.length >= 1) {
			String lastWord = args[args.length - 1];
			return RegionManager.findRegionsBeginningWith(lastWord);
		}
		else
			return super.tabComplete(sender, alias, args);
	}
}
