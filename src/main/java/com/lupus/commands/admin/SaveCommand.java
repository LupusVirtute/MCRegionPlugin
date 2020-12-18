package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.utils.Usage;
import org.bukkit.command.CommandSender;

public class SaveCommand extends LupusCommand {
	public SaveCommand(){
		super("save", Usage.usage("/plots save"),"&6Samo tłumaczące się",0);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		RegionManager.saveRegions(false);
	}
}
