package com.lupus.commands;


import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.SupCommand;
import com.lupus.commands.sub.*;
import com.lupus.gui.PlotGUI;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class PlotMainSupCommand extends SupCommand {
	public PlotMainSupCommand(){
		super(
			"dzialka",
			Usage.usage("/dzialka"),
			"&6Komenda do zarządzania działkami",
				Arrays.asList("dz"),
				new ArrayList<>(),
				0,
			new LupusCommand[]{
				new AcceptInviteCMD(),
				new AddToPlotCMD(),
				new CreatePlotCMD(),
				new ChangeNameCMD(),
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
	public boolean optionalOperations(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+ "Komenda tylko dla graczy");
			return true;
		}
		Player p = (Player)sender;
		if (args.length < 1)
		{
			PlotGUI plotGUI = new PlotGUI(p);
			plotGUI.open(p);
			return true;
		}
		return false;
	}
}
