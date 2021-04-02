package com.lupus.commands;


import com.lupus.MCGUIFramework;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.SupCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.commands.sub.*;
import com.lupus.gui.IGUI;
import com.lupus.messages.GeneralMessages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlotMainSupCommand extends SupCommand {
	public PlotMainSupCommand(){
		super(
			"dzialka",
			usage("/dzialka"),
			"&6Komenda do zarządzania działkami",
				Arrays.asList("dz"),
				new ArrayList<>(),
				0,
			new LupusCommand[]{
				new AcceptInviteCMD(),
				new AddToPlotCMD(),
				new CreatePlotCMD(),
				new ChangeNameCMD(),
				new ChatCMD(),
				new DefaultCMD(),
				new DeletePlotCMD(),
				new DeclineInviteCMD(),
				new LeaveCMD(),
				new SetSpawnCMD(),
				new ShowPlotBorderCMD(),
				new StatsCMD(),
				new PanelCMD(),
				new TeleportCMD(),
				new ThrowOutCMD(),
				new UpgradeCMD()
			}
		);
	}

	@Override
	public boolean optionalOperations(CommandSender sender, ArgumentList args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(GeneralMessages.PLAYER_ONLY.toString());
			return true;
		}
		Player p = (Player)sender;
		if (args.size() < 1)
		{
			IGUI gui = MCGUIFramework.getManager().getGUI("PlotGUI");
			if (gui == null)
				return true;
			gui.open(p);
			return true;
		}
		return false;
	}
}
