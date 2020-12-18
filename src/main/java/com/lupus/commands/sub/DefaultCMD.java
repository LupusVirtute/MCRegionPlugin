package com.lupus.commands.sub;


import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.PlotGUI;
import com.lupus.utils.Usage;
import org.bukkit.entity.Player;

public class DefaultCMD extends PlayerCommand {
	public DefaultCMD(){
		super("pomoc", Usage.usage("dzialka"),"Panel działki",0);
	}
	@Override
	public void run(Player executor, String[] args) {
		PlotGUI gui = new PlotGUI(executor);
		gui.open(executor);
	}

}
