package com.lupus.commands.sub;


import com.lupus.managers.InviteManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.Usage;
import org.bukkit.entity.Player;

public class AcceptInviteCMD extends PlayerCommand {

	public AcceptInviteCMD() {
		super("akceptuj",
				Usage.usage("/dzialka akceptuj"),
				"&6Akceptujesz zaproszenie do działki",
				1);

	}

	@Override
	public void run(Player executor, String[] args) {

		Region r = InviteManager.getInvite(executor);
		if(r == null){
			executor.sendMessage(PlotMessages.NO_INVITE.toString());
			return;
		}
		String ownerName = r.getOwnerName();
		if(ownerName != null){
			executor.sendMessage(PlotMessages.ACCEPT_INVITE.toString(ownerName));
			Player p2 = r.getOwner();
			if(p2 != null) {
				p2.sendMessage(PlotMessages.OWNER_ACCEPT_INVITE_MESSAGE.toString(executor.getName()));
			RegionManager.addPlayerToRegion(executor,r);
			}
		}else {
			executor.sendMessage(PlotMessages.DENY_INVITE.toString("Działka bez właściciela"));
		}
	}
}
