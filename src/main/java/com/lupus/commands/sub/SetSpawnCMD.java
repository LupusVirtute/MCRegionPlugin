package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawnCMD extends PlayerCommand {
	public SetSpawnCMD(){
		super("setspawn", usage("/dzialka setspawn"),"&6Ustawiasz spawn działki",0);
	}
	@Override
	public void run(Player executor, ArgumentList args) {
		if(executor == null){
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(GeneralMessages.NO_PLOT.toString());
			return;
		}
		if(!r.contains(executor.getLocation())){
			executor.sendMessage(GeneralMessages.NOT_INSIDE_REGION.toString());
			return;
		}
		Location playerLoc = executor.getLocation();
		r.setSpawn(playerLoc);
		executor.sendMessage(GeneralMessages.CHANGED_SPAWN_SUCCESSFULLY.toString());
	}
}
