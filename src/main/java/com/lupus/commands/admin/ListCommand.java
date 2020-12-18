package com.lupus.commands.admin;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.utils.Usage;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ListCommand extends LupusCommand {
	public ListCommand(){
		super(
				"list",
				Usage.usage("/plots list","[strona]"),
				"&6Pokazuje liste działek",1);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		Region r;
		if (RegionManager.getRegionAmount() <= 0) {
			executor.sendMessage(ChatColor.RED + "0 Działek");
			return;
		}

		int page = args.length >= 1 ? (NumberUtils.isNumber(args[0]) ? Integer.parseInt(args[0]) : 0) : 0;
		int pageCounter = (page*5)+5;
		executor.sendMessage(ChatColor.YELLOW + "---- "
				+ChatColor.RED+"Plots "+ page +"/"+ RegionManager.getRegionAmount()/5
				+ChatColor.YELLOW +" ----");
		for (int i = page*5; i < RegionManager.getRegionAmount(); i++) {
			if(i > pageCounter){
				break;
			}
			r = RegionManager.findRegion(i);
			if(r != null)
				executor.sendMessage(r.getName() + " dID: " + i);
		}
	}
}
