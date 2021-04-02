package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class LeaveCMD extends PlayerCommand {
	public LeaveCMD(){
		super("opusc", usage("/dzialka opusc","[dzialka]"),"&6Opuszczasz działke",1);
	}
	@Override
	public void run(Player executor, ArgumentList args) throws Exception {
		if(args.size() < 1){
			executor.sendMessage(usage("/dzialka opusc ", "[dzialka]"));
			return;
		}
		Region r = args.getArg(Region.class,0);
		if(r == null){
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		Set<UUID> memberships = RegionManager.getPlayerMemberships(executor);
		if (!memberships.contains(r.getUniqueId())){
			executor.sendMessage(GeneralMessages.NO_BELONG.toString());
			return;
		}
		RegionManager.removePlayerFromRegion(executor,r);
		MessageReplaceQuery query = new MessageReplaceQuery().addString("name",r.getName());
		executor.sendMessage(GeneralMessages.SUCCESSFUL_LEAVE.toString(query));
		if (r.getOwner() != null) {
			query = new MessageReplaceQuery().addString("player",executor.getName());
			r.getOwner().sendMessage(GeneralMessages.PLAYER_LEFT.toString(query));
		}
	}
}
