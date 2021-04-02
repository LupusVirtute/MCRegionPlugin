package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

public class PanelCMD extends PlayerCommand {
	public PanelCMD(){
		super("panel", usage("/dzialka panel"),"&6Panel twojej działki",0);
	}
	@Override
	public void run(Player executor, ArgumentList args) {
		Region r = RegionManager.getRegionOfOwner(executor);
		if (r == null){
			new DefaultCMD().run(executor,args);
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		new PlotManagerGUI().open(executor);
	}
}
