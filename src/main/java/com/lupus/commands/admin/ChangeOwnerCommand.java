package com.lupus.commands.admin;


import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.CacheManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChangeOwnerCommand extends LupusCommand {
	public ChangeOwnerCommand(){
		super("changeowner",
				usage("/plots changeowner","[Gracz] [Dzialka]"),
				"&6Zmienia właściciela działki",
				2);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) throws Exception {
		Player p2 = Bukkit.getPlayer(
				CacheManager.getInstance().getPlayer(args.get(0))
		);
		if (p2 == null) {
			executor.sendMessage(GeneralMessages.PLAYER_OFFLINE.toString());
			return;
		}
		Region r = args.getArg(Region.class,1);
		if(r == null){
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		Region ownerRegion = RegionManager.getRegionOfOwner(p2);
		if(ownerRegion != null){
			RegionManager.removeRegion(ownerRegion);
		}
		if(ownerRegion == r){
			executor.sendMessage(ChatColor.RED + "Gracz już ma tą działke");
			return;
		}
		boolean b = r.changeOwner(p2);
		if(b)
			executor.sendMessage(GeneralMessages.CHANGED_PLOT_OWNER.toString());
		else
			executor.sendMessage(GeneralMessages.FATAL_CRASH_XD.toString());
		return;
	}
	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (args.length == 2) {
			String lastWord = args[args.length - 1];
			return RegionManager.findRegionsBeginningWith(lastWord);
		}
		else
			return super.tabComplete(sender, alias, args);
	}
}
