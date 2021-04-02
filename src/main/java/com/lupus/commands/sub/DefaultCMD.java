package com.lupus.commands.sub;


import com.lupus.MCGUIFramework;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.IGUI;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

public class DefaultCMD extends PlayerCommand {
	public DefaultCMD(){
		super("pomoc", usage("dzialka"),"Panel działki",0);
	}
	@Override
	public void run(Player executor, ArgumentList args) {
		IGUI gui = MCGUIFramework.getManager().getGUI("PlotGUI");
		if (gui == null)
			return;
		gui.open(executor);
	}

}
