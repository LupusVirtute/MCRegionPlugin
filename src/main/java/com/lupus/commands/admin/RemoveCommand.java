package com.lupus.commands.admin;


import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RemoveCommand extends LupusCommand {
	public RemoveCommand(){
		super(
				"remove",
				usage("/plots remove","[Dzialka]"),
				"&6Usuwa działke",
				0);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) throws Exception {
		if(args.size() != 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = args.getArg(Region.class,0);
		RegionManager.removeRegion(r);
		executor.sendMessage(ChatColor.RED + "Poprawnie usunięto działke");
	}
}
