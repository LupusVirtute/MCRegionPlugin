package com.lupus.commands.sub;

import com.lupus.RegionPlugin;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.economy.Prices;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.Usage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class UpgradeCMD extends PlayerCommand {
	public UpgradeCMD(){
		super("ulepsz", Usage.usage("/dzialka ulepsz"),"&6Ulepszasz działke",0);
	}
	@Override
	public void run(Player executor, String[] args){
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(PlotMessages.NO_PLOT.toString());
			return;
		}
		if(r.getLevel() >= 6 && !executor.hasPermission("plot.vip")){
			executor.sendMessage(PlotMessages.MAX_LEVEL_FOR_PLAYER.toString());
			return;
		}
		if(r.getLevel() >= 9){
			executor.sendMessage(PlotMessages.MAX_LEVEL.toString());
			return;
		}
		double kesz = RegionPlugin.getEconomy().getBalance(executor);
		double price = r.getLevel() * Prices.regionUpgrade;
		if(kesz < price){
			String needed = String.valueOf((int)(price-kesz));
			executor.sendMessage(GeneralMessages.INSUFFICIENT_MONEY.toString(needed));
			return;
		}
		RegionPlugin.getEconomy().withdrawPlayer(executor,price);
		executor.playSound(executor.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1.0f,1.0f);
		r.expand(8,8);
		executor.sendMessage(PlotMessages.SUCCESSFUL_UPGRADE.toString(String.valueOf(r.getLevel())));
	}
}
