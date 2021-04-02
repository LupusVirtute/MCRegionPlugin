package com.lupus.commands.sub;

import com.lupus.managers.CacheManager;
import com.lupus.managers.InviteManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AddToPlotCMD extends PlayerCommand {
	public AddToPlotCMD() {
		super("dodaj",
				usage("/dzialka dodaj", "[Gracz]"),
				"&6Dodajesz gracza do działki",
				1);
	}
	@Override
	public void run(Player executor, ArgumentList args) throws Exception {
		if(args.size() < 1){
			executor.sendMessage(usage());
			return;
		}
		Player p2 = args.getArg(Player.class,0);
		if(p2 == null){
			executor.sendMessage(GeneralMessages.PLAYER_OFFLINE.toString());
			return;
		}
		if(p2.equals(executor)){
			executor.sendMessage(GeneralMessages.CANT_USE_ON_MYSELF.toString());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(GeneralMessages.NO_PLOT.toString());
			return;
		}
		InviteManager.addInvite(p2,r);
		MessageReplaceQuery query = new MessageReplaceQuery().addString("player",p2.getName());
		executor.sendMessage(GeneralMessages.INVITE_SUCCESFUL.toString(query));
	}
}
