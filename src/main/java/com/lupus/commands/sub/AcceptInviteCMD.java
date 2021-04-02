package com.lupus.commands.sub;


import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.InviteManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

public class AcceptInviteCMD extends PlayerCommand {

	public AcceptInviteCMD() {
		super("akceptuj",
				usage("/dzialka akceptuj"),
				"&6Akceptujesz zaproszenie do działki",
				1);

	}

	@Override
	public void run(Player executor, ArgumentList args) {

		Region r = InviteManager.getInvite(executor);
		if(r == null){
			executor.sendMessage(GeneralMessages.NO_INVITE.toString());
			return;
		}
		String ownerName = r.getOwnerName();
		if(ownerName != null){
			MessageReplaceQuery query = new MessageReplaceQuery().addString("player",ownerName);
			executor.sendMessage(GeneralMessages.ACCEPT_INVITE.toString(query));
			Player p2 = r.getOwner();
			if(p2 != null) {
				query = new MessageReplaceQuery().addString("player",executor.getName());
				p2.sendMessage(GeneralMessages.OWNER_ACCEPT_INVITE_MESSAGE.toString(query));
				RegionManager.addPlayerToRegion(executor,r);
				InviteManager.removeInvite(executor);
			}
		}else {
			MessageReplaceQuery query = new MessageReplaceQuery().addString("player","Działka bez właściciela");
			executor.sendMessage(GeneralMessages.DENY_INVITE.toString(query));
		}
	}
}
