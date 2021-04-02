package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.StatsGUI;
import com.lupus.managers.PlayerInsideRegionManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.gui.utils.TextUtility;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatsCMD extends PlayerCommand {
	public StatsCMD(){
		super("stats", usage("/dzialka stats", "[nazwa dzialki]"),"&6Pokazuje statystyki działki",1);
	}
	@Override
	public void run(Player extractor, ArgumentList args) throws Exception {
		Region region;
		if(args.size() < 1){
			region = PlayerInsideRegionManager.getPlayerRegionHeIsInsideOf(extractor);
		}
		else{
			region = args.getArg(Region.class,0);
		}

		if(region == null){
			extractor.sendMessage(usage());
			return;
		}
		StatsGUI gui = new StatsGUI(region);
		gui.open(extractor);
	}
}
