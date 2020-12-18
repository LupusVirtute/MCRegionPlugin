package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.Usage;
import org.bukkit.entity.Player;

public class PanelCMD extends PlayerCommand {
	public PanelCMD(){
		super("panel", Usage.usage("/dzialka panel"),"&6Panel twojej działki",0);
	}
	@Override
	public void run(Player executor, String[] args) {
		Region r = RegionManager.getRegionOfOwner(executor);
		if (r == null){
			new DefaultCMD().run(executor,args);
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
			return;
		}
		new PlotManagerGUI().open(executor);
	}
}
