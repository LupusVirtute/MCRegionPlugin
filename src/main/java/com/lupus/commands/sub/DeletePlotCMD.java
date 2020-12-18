package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

// Todo Rest of these
public class DeletePlotCMD extends PlayerCommand {
	public DeletePlotCMD(){
		super("usun", Usage.usage("/dzialka usun"),"&6Usuwasz działke",0);
	}
	@Override
	public void run(Player executor, String[] args){
		Region reg = RegionManager.getRegionOfOwner(executor);
		if(reg == null){
			executor.sendMessage(PlotMessages.NO_PLOT.toString());
			return;
		}
		if(!reg.isDeleting()){
			executor.sendMessage(PlotMessages.DELETE_CONFIRMATION.toString(reg.getName()));
			reg.setDelete();
		}
		else{
			executor.sendMessage(ChatColor.RED +"Usunąłeś swoją działkę "+reg.getName());
			RegionManager.removeRegion(reg);
		}
	}
}
