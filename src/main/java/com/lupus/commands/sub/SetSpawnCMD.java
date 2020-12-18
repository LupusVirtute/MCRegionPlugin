package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.Usage;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawnCMD extends PlayerCommand {
	public SetSpawnCMD(){
		super("setspawn", Usage.usage("/dzialka setspawn"),"&6Ustawiasz spawn działki",0);
	}
	@Override
	public void run(Player executor, String[] args) {
		if(executor == null){
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(PlotMessages.NO_PLOT.toString());
			return;
		}
		if(!r.contains(executor.getLocation())){
			executor.sendMessage(PlotMessages.NOT_INSIDE_REGION.toString());
			return;
		}
		Location playerLoc = executor.getLocation();
		r.setSpawn(playerLoc);
		executor.sendMessage(PlotMessages.CHANGED_SPAWN_SUCCESFULLY.toString());
	}
}
