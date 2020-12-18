package com.lupus.commands.admin;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.Usage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChangeOwnerCommand extends LupusCommand {
	public ChangeOwnerCommand(){
		super("changeowner",
				Usage.usage("/plots changeowner","[Gracz] [Dzialka]"),
				"&6Zmienia właściciela działki",
				2);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		Player p2 = Bukkit.getPlayer(args[0]);
		if (p2 == null) {
			executor.sendMessage(PlotMessages.PLAYER_OFFLINE.toString());
			return;
		}
		Region r = RegionManager.findRegion(args[1]);
		if(r == null){
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
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
			executor.sendMessage(ChatColor.BLUE+ "Udało się zmienić właściciela brawo komunizmowi");
		else
			executor.sendMessage(ChatColor.RED+ "Coś się kurwa popsuło nie było mnie słychać zjebało mnie przy zmianie właściciela");
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
