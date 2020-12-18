package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.StatsGUI;
import com.lupus.managers.PlayerInsideRegionManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatsCMD extends PlayerCommand {
	public StatsCMD(){
		super("stats", Usage.usage("/dzialka stats", "[nazwa dzialki]"),"&6Pokazuje statystyki działki",1);
	}
	@Override
	public void run(Player extractor, String[] args){
		Region region;
		if(args.length < 1){
			region = PlayerInsideRegionManager.getPlayerRegionHeIsInsideOf(extractor);
		}
		else{
			region = RegionManager.findRegion(args[0]);
		}

		if(region == null){
			extractor.sendMessage(usage());
			return;
		}
		StatsGUI gui = new StatsGUI(region);
		gui.open(extractor);
	}
}
