package com.lupus.commands.sub;


import com.lupus.managers.InviteManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

public class DeclineInviteCMD extends PlayerCommand {
	public DeclineInviteCMD(){
		super("odrzuc", usage("/dzialka odrzuc"),"Odrzuca zaproszenie o przyjęcie do działki",0);
	}
	@Override
	public void run(Player executor, ArgumentList args){
		Region r = InviteManager.getInvite(executor);
		if(r == null){
			executor.sendMessage(GeneralMessages.NO_INVITE.toString());
			return;
		}
		String ownerName = r.getOwnerName();
		if(ownerName != null){
			MessageReplaceQuery query = new MessageReplaceQuery().
					addString("player",r.getOwnerName());
			executor.sendMessage(GeneralMessages.DENY_INVITE.toString(query));
			Player p2 = r.getOwner();
			if(p2 != null) {
				query = new MessageReplaceQuery().addString("player",executor.getName());
				p2.sendMessage(GeneralMessages.OWNER_DENY_INVITE_MESSAGE.toString(query));
			}
			InviteManager.removeInvite(executor);
		}else {
			MessageReplaceQuery query = new MessageReplaceQuery().
					addString("player","Wlasciciel nie istnieje");
			executor.sendMessage(GeneralMessages.DENY_INVITE.toString());
		}
	}
}
