package com.lupus.commands.sub;


import com.lupus.managers.InviteManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.utils.Usage;
import com.lupus.messages.PlotMessages;
import org.bukkit.entity.Player;

public class DeclineInviteCMD extends PlayerCommand {
	public DeclineInviteCMD(){
		super("odrzuc", Usage.usage("/dzialka odrzuc"),"Odrzuca zaproszenie o przyjęcie do działki",0);
	}
	@Override
	public void run(Player executor, String[] args){
		Region r = InviteManager.getInvite(executor);
		if(r == null){
			executor.sendMessage(PlotMessages.NO_INVITE.toString());
			return;
		}
		String ownerName = r.getOwnerName();
		if(ownerName != null){
			executor.sendMessage(PlotMessages.DENY_INVITE.toString(r.getOwnerName()));
			Player p2 = r.getOwner();
			if(p2 != null) {
				p2.sendMessage(PlotMessages.OWNER_DENY_INVITE_MESSAGE.toString(executor.getName()));
			}
		}else {
			executor.sendMessage(PlotMessages.DENY_INVITE.toString("Wlasciciel nie istnieje"));
		}
	}
}
