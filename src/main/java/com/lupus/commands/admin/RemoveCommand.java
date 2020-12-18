package com.lupus.commands.admin;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RemoveCommand extends LupusCommand {
	public RemoveCommand(){
		super(
				"remove",
				Usage.usage("/plots remove","[Dzialka]"),
				"&6Usuwa działke",
				0);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		if(args.length != 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.findRegion(args[0]);
		if(r == null){
			executor.sendMessage(ChatColor.RED + "Działka nie istnieje");
			return;
		}
		RegionManager.removeRegion(r);
		executor.sendMessage(ChatColor.RED + "Poprawnie usunięto działke");
	}
}
