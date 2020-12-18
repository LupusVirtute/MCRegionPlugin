package com.lupus.commands.sub;


import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.Usage;
import com.lupus.messages.PlotMessages;
import org.bukkit.entity.Player;

public class ChangeNameCMD extends PlayerCommand {
	public ChangeNameCMD(){
		super("nazwa", Usage.usage("/dzialka nazwa","[nowa nazwa]"),"&6Zmieniasz nazwe swojej działki",1);
	}
	@Override
	public void run(Player executor, String[] args) {
		if(args.length < 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(ColorUtil.text2Color("&cNie posiadasz działki"));
			return;
		}
		if(RegionManager.doesRegionNameAlreadyExist(args[0])){
			executor.sendMessage(PlotMessages.PLOT_ALERADY_EXISTS.toString());
			return;
		}
		r.changeName(args[0]);
		executor.sendMessage(ColorUtil.text2Color("&a&lPoprawnie zmieniono nazwe działki na : " + args[0]));
	}
}
