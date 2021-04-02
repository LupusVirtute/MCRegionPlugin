package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.commands.PlotsMainCMD;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCMD extends LupusCommand {
	public HelpCMD(){
		super("help",
				usage("/plots help"),
				"&6Pomoc",0);
	}
	static PlotsMainCMD cmds = new PlotsMainCMD();
	@Override
	public void run(CommandSender executor, ArgumentList args) {
		executor.sendMessage(ChatColor.YELLOW + "==="+ChatColor.RED+" Plots "+ChatColor.YELLOW + "===");
		LupusCommand[] subCommands = cmds.getSubCommands();
		for(int i = 0; i < cmds.getSubCommands().length; i++){
			executor.sendMessage(subCommands[i].getUsageDesc());
		}
	}
}
