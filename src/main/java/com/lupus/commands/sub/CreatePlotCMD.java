package com.lupus.commands.sub;


import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.region.RegionUtil;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.utils.TextUtility;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CreatePlotCMD extends PlayerCommand {
	public CreatePlotCMD(){
		super("stworz",
				usage("/dzialka stworz", "[Nazwa]"),
				TextUtility.color("&6Tworzy nową działke"),
				1);
	}
	@Override
	public void run(Player executor, ArgumentList args) {
		if(args.size() < 1){
			executor.sendMessage(usage());
			return;
		}
		String name = args.get(0);
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r != null){
			executor.sendMessage(GeneralMessages.ALERADY_OWNS_PLOT.toString());
			return;
		}
		if(RegionManager.doesRegionNameAlreadyExist(name)){
			executor.sendMessage(GeneralMessages.PLOT_ALERADY_EXISTS.toString());
			return;
		}
		Location playerLoc = executor.getLocation();
		boolean isValid = RegionUtil.isValidNewRegionLocation(playerLoc);
		if(!isValid){
			executor.sendMessage(GeneralMessages.REGION_LOCATION_INVALID.toString());
			return;
		}
		r = new Region(executor.getWorld(),name,playerLoc,1,executor.getUniqueId());
		RegionManager.addRegion(r);
		r.changeOwner(executor);
		RegionManager.addPlayerToRegion(executor,r);

		MessageReplaceQuery query = new MessageReplaceQuery().addString("name",r.getName());
		executor.sendMessage(GeneralMessages.SUCCESFUL_CREATION.toString(query));
	}
}
