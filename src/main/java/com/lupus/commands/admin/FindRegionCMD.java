package com.lupus.commands.admin;


import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.RegionManager;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.command.CommandSender;

public class FindRegionCMD extends LupusCommand {
	public FindRegionCMD(){
		super("find",
				usage("/plots find ","[nazwa]"),
				"&6Znajduje region zaczynający się na daną frazę",
				1);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args){
		if (args.size() < 1){
			executor.sendMessage(usage());
			return;
		}
		executor.sendMessage(TextUtility.color("&4&L --- RESULTS ---"));

		executor.sendMessage(RegionManager.findRegionsBeginningWith(args.get(0)).toArray(new String[0]));
	}
}