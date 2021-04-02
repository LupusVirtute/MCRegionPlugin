package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.RegionManager;
import org.bukkit.command.CommandSender;

public class SaveCommand extends LupusCommand {
	public SaveCommand(){
		super("save", usage("/plots save"),"&6Samo tłumaczące się",0);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) {
		RegionManager.saveRegions(false);
	}
}
