package com.lupus.commands.admin;


import com.lupus.command.framework.commands.arguments.UInteger;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ListCommand extends LupusCommand {
	public ListCommand(){
		super(
				"list",
				usage("/plots list","[strona]"),
				"&6Pokazuje liste działek",1);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) {
		Region r;
		if (RegionManager.getRegionAmount() <= 0) {
			executor.sendMessage(ChatColor.RED + "0 Plots");
			return;
		}
		int page = 0;
		try{
			page = args.getArg(UInteger.class,0).getInteger();
		}
		catch (Exception ignored){

		}
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
