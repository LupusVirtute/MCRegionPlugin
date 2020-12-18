package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.Usage;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class LeaveCMD extends PlayerCommand {
	public LeaveCMD(){
		super("opusc", Usage.usage("/dzialka opusc","[dzialka]"),"&6Opuszczasz działke",1);
	}
	@Override
	public void run(Player executor, String[] args) {
		if(args.length < 1){
			executor.sendMessage(Usage.usage("/dzialka opusc ", "[dzialka]"));
			return;
		}
		Region r = RegionManager.findRegion(args[0]);
		if(r == null){
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
			return;
		}
		Set<UUID> memberships = RegionManager.getPlayerMemberships(executor);
		if (!memberships.contains(r.getUniqueId())){
			executor.sendMessage(PlotMessages.NO_BELONG.toString());
			return;
		}
		RegionManager.removePlayerFromRegion(executor,r);
		executor.sendMessage(PlotMessages.SUCCESSFUL_LEAVE.toString(r.getName()));
		if (r.getOwner() != null) {
			r.getOwner().sendMessage(PlotMessages.PLAYER_LEFT.toString(executor.getName()));
		}
	}
}
