package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

// Todo Rest of these
public class DeletePlotCMD extends PlayerCommand {
	public DeletePlotCMD(){
		super("usun", usage("/dzialka usun"),"&6Usuwasz działke",0);
	}
	@Override
	public void run(Player executor, ArgumentList args){
		Region reg = RegionManager.getRegionOfOwner(executor);
		if(reg == null){
			executor.sendMessage(GeneralMessages.NO_PLOT.toString());
			return;
		}
		if(!reg.isDeleting()){
			MessageReplaceQuery query = new MessageReplaceQuery().
					addString("name",reg.getName());
			executor.sendMessage(GeneralMessages.DELETE_CONFIRMATION.toString(query));
			reg.setDelete();
		}
		else{
			MessageReplaceQuery query = new MessageReplaceQuery().
					addString("name",reg.getName());
			executor.sendMessage(GeneralMessages.DELETE_SUCCESS_PLOT_MESSAGE.toString(query));
			RegionManager.removeRegion(reg);
		}
	}
}
