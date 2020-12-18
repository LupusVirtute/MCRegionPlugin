package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.utils.Usage;
import org.bukkit.command.CommandSender;

public class ReloadRegions extends LupusCommand {
	public ReloadRegions(){
		super(
				"reload",
				Usage.usage("/plots reload"),
				"&6Samo tłumaczy się",
				0);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		RegionManager.reloadRegions();
	}
}
