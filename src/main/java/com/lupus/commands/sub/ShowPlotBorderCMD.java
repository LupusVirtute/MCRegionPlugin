package com.lupus.commands.sub;

import com.lupus.RegionPlugin;
import com.lupus.command.framework.commands.CommandLimiter;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.PlayerInsideRegionManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.runnables.BorderRunnable;
import com.lupus.utils.Usage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ShowPlotBorderCMD extends PlayerCommand {
	public ShowPlotBorderCMD() {
		super("granica", Usage.usage("/dzialka granica"), "&6Pokazuje granice działki",0);
	}

	@Override
	public void run(Player executor, String[] args) {
		long timeLimit = CommandLimiter.INSTANCE.getTimeLeft(executor,getName());
		if(timeLimit > 0) {
			executor.sendMessage(ChatColor.RED + "Możesz ponownie użyć tej komendy za " + ChatColor.YELLOW + timeLimit / 1000 + " sekund");
			return;
		}
		UUID regUID = PlayerInsideRegionManager.getPlayerInRegion(executor);
		if(regUID == null){
			executor.sendMessage(PlotMessages.NOT_INSIDE_REGION.toString());
			return;
		}
		Region r = RegionManager.findRegion(regUID);
		BorderRunnable border = new BorderRunnable(executor.getWorld(),r,executor,400);
		border.runTaskTimerAsynchronously(RegionPlugin.getMainPlugin(), 0,17);
		executor.sendMessage(PlotMessages.SHOWING_BORDER.toString(r));

		CommandLimiter.INSTANCE.addLimit(executor,getName(),480000);
	}

}
