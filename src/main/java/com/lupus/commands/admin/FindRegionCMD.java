package com.lupus.commands.admin;


import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.managers.RegionManager;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.Usage;
import org.bukkit.command.CommandSender;

public class FindRegionCMD extends LupusCommand {
	public FindRegionCMD(){
		super("find",
				Usage.usage("/plots find ","[nazwa]"),
				"&6Znajduje region zaczynający się na daną frazę",
				1);
	}
	@Override
	public void run(CommandSender executor, String[] args){
		if (args.length < 1){
			executor.sendMessage(usage());
			return;
		}
		executor.sendMessage(ColorUtil.text2Color("&4&L --- RESULTS ---"));
		executor.sendMessage(RegionManager.findRegionsBeginningWith(args[0]).toArray(new String[0]));
	}
}