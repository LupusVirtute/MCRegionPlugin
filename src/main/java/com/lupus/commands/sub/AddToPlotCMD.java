package com.lupus.commands.sub;

import com.lupus.managers.InviteManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.Usage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AddToPlotCMD extends PlayerCommand {
	public AddToPlotCMD() {
		super("dodaj",
				Usage.usage("/dzialka dodaj", "[Gracz]"),
				"&6Dodajesz gracza do działki",
				1);
	}
	@Override
	public void run(Player executor, String[] args) {
		if(args.length < 1){
			executor.sendMessage(usage());
			return;
		}
		Player p2 = Bukkit.getPlayer(args[0]);
		if(p2 == null){
			executor.sendMessage(PlotMessages.PLAYER_OFFLINE.toString());
			return;
		}
		if(p2.equals(executor)){
			executor.sendMessage(PlotMessages.CANT_USE_ON_MYSELF.toString());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(PlotMessages.NO_PLOT.toString());
			return;
		}
		InviteManager.addInvite(p2,r);
		executor.sendMessage(PlotMessages.INVITE_SUCCESFUL.toString(p2.getName()));
	}
}
