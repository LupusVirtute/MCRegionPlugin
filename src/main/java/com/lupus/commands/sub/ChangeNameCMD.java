package com.lupus.commands.sub;


import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.utils.TextUtility;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.entity.Player;

public class ChangeNameCMD extends PlayerCommand {
	public ChangeNameCMD(){
		super("nazwa", usage("/dzialka nazwa","[nowa nazwa]"),"&6Zmieniasz nazwe swojej działki",1);
	}
	@Override
	public void run(Player executor, ArgumentList args) {
		if(args.size() < 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(TextUtility.color("&cNie posiadasz działki"));
			return;
		}

		if(RegionManager.doesRegionNameAlreadyExist(args.get(0))){
			executor.sendMessage(GeneralMessages.PLOT_ALREADY_EXISTS.toString());
			return;
		}
		r.changeName(args.get(0));
		executor.sendMessage(TextUtility.color("&a&lPoprawnie zmieniono nazwe działki na : " + args.get(0)));
	}
}
