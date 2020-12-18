package com.lupus.commands.sub;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.region.RegionUtil;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.Usage;
import com.lupus.messages.PlotMessages;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CreatePlotCMD extends PlayerCommand {
	public CreatePlotCMD(){
		super("stworz",
				Usage.usage("/dzialka stworz", "[Nazwa]"),
				ColorUtil.text2Color("&6Tworzy nową działke"),
				1);
	}
	@Override
	public void run(Player executor, String[] args) {
		if(args.length < 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r != null){
			executor.sendMessage(PlotMessages.ALERADY_OWNS_PLOT.toString());
			return;
		}
		if(RegionManager.doesRegionNameAlreadyExist(args[0])){
			executor.sendMessage(PlotMessages.PLOT_ALERADY_EXISTS.toString());
			return;
		}
		Location playerLoc = executor.getLocation();
		boolean isValid = RegionUtil.isValidNewRegionLocation(playerLoc);
		if(!isValid){
			executor.sendMessage(PlotMessages.REGION_LOCATION_INVALID.toString());
			return;
		}
		r = new Region(executor.getWorld(),args[0],playerLoc,1,executor.getUniqueId());
		RegionManager.addRegion(r);
		r.changeOwner(executor);
		RegionManager.addPlayerToRegion(executor,r);
		executor.sendMessage(PlotMessages.SUCCESFUL_CREATION.toString(r));
	}
}
