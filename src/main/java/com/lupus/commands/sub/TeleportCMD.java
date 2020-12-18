package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.manager.sub.gui.PlotTeleportGUI;
import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.LocationsUtil;
import com.lupus.utils.Usage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TeleportCMD extends PlayerCommand {
	public TeleportCMD(){
		super("teleport",
				Usage.usage("/dzialka teleport"),
				"&6Teleporty do działek do których jesteś dodany",
				Arrays.asList("tp","tele"),
				new ArrayList<>(),
				0);
	}
	@Override
	public void run(Player executor, String[] args) {

		if(args.length < 1){
			new PlotTeleportGUI(executor);
			return;
		}
		Region r = RegionManager.findRegion(args[0]);
		if(r == null){
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
			return;
		}
		if(r.getMember(executor) != null){
			LocationsUtil.teleportPlayer(executor, r.getSpawn());
		}
		else{
			executor.sendMessage(PlotMessages.NO_BELONG.toString());
		}
		return;
	}

	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (!(sender instanceof Player))
			return super.tabComplete(sender, alias, args);
		List<String> answer = new ArrayList<>();
		Set<UUID> regionUUIDSet = RegionManager.getPlayerMemberships((Player)sender);
		String lastWord = args[args.length - 1];
		for (UUID uuid : regionUUIDSet) {
			String name = ColorUtil.strip(RegionManager.findRegion(uuid).getName());
			if (name.startsWith(lastWord)){
				answer.add(name);
			}
		}
		return answer;
	}
}
