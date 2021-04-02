package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.ChatManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import org.bukkit.entity.Player;

public class ChatCMD extends PlayerCommand {
	public ChatCMD() {
		super("chat", usage("/dzialka chat","[dzialka]"), colorText("&eWłączasz czat działkowy"), 0);
	}

	@Override
	protected void run(Player player, ArgumentList args) throws Exception {
		if (args.size() < 1){
			ChatManager.removePlayerChat(player);
			player.sendMessage(GeneralMessages.SUCCESSFUL_CHAT_TURN_OFF.toString());
			return;
		}
		Region r = args.getArg(Region.class,0);
		if (r == null)
		{
			MessageReplaceQuery query = new MessageReplaceQuery().addString("name",args.get(0));
			player.sendMessage(GeneralMessages.THIS_IS_NOT_PLOT_NAME.toString(query));
			return;
		}
		if (!r.containsMember(player)){
			player.sendMessage(GeneralMessages.NOT_ADDED_TO_PLOT.toString());
			return;
		}
		ChatManager.changePlayerRegionChat(r,player);
		MessageReplaceQuery query = new MessageReplaceQuery().addString("name",r.getName());
		player.sendMessage(GeneralMessages.YOURE_NOW_TALKING_ON_CHAT_PLOT.toString(query));
	}
}
