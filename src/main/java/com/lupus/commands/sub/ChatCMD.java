package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.ChatManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import org.bukkit.entity.Player;

public class ChatCMD extends PlayerCommand {
	public ChatCMD() {
		super("chat", usage("/dzialka chat","[dzialka]"), colorText("&eWłączasz czat działkowy"), 0);
	}

	@Override
	protected void run(Player player, String[] strings) {
		if (strings.length < 1){
			ChatManager.removePlayerChat(player);
			player.sendMessage(colorText("&aPoprawnie wyłączyłeś czat działki"));
			return;
		}
		Region r = RegionManager.findRegion(strings[0]);
		if (r == null)
		{
			String message = colorText("&4"+strings[0]+" &cNie jest to nazwa działki");
			player.sendMessage(message);
			return;
		}
		if (r.containsMember(player)){
			String message = colorText("&cNie jesteś dodany do działki");
			player.sendMessage(message);
			return;
		}
		ChatManager.changePlayerRegionChat(r,player);
		String message = colorText("&aTeraz gadasz na czacie działki : "+r.getName());
		player.sendMessage(message);
	}
}
