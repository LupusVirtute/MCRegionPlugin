package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.command.CommandSender;

public class ReloadRegions extends LupusCommand {
	public ReloadRegions(){
		super(
				"reload",
				usage("/plots reload"),
				"&6Samo tłumaczy się",
				0);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) {
		RegionManager.reloadRegions();
		executor.sendMessage(GeneralMessages.SUCCESSFUL_RELOAD_REGIONS.toString());
	}
}
