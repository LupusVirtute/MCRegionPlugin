package com.lupus.commands.sub;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.manager.sub.gui.PlotTeleportGUI;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import com.lupus.gui.utils.TextUtility;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TeleportCMD extends PlayerCommand {
	public TeleportCMD(){
		super("teleport",
				usage("/dzialka teleport"),
				"&6Teleporty do działek do których jesteś dodany",
				Arrays.asList("tp","tele"),
				new ArrayList<>(),
				0);
	}
	@Override
	public void run(Player executor, ArgumentList args) throws Exception {

		if(args.size() < 1){
			new PlotTeleportGUI(executor);
			return;
		}
		Region r = args.getArg(Region.class,0);
		if(r == null){
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		if(r.getMember(executor) != null){
			Location spawn = r.getSpawn();
			if (isSafeLocation(spawn))
				executor.teleport(spawn);
			else
				spawn.setY(
						spawn.
							getWorld().
							getHighestBlockYAt(
									spawn.getBlockX(),
									spawn.getBlockZ()
							)
				);
		}
		else{
			executor.sendMessage(GeneralMessages.NO_BELONG.toString());
		}
	}
	public static boolean isSafeLocation(Location location) {
		Block feet = location.getBlock();
		if (!feet.getType().isAir() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isAir()) {
			return false;
		}
		Block head = feet.getRelative(BlockFace.UP);
		if (!head.getType().isAir()) {
			return false;
		}
		Block ground = feet.getRelative(BlockFace.DOWN);
		return ground.getType().isSolid();
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
			String name = TextUtility.strip(RegionManager.findRegion(uuid).getName());
			if (name.startsWith(lastWord)){
				answer.add(name);
			}
		}
		return answer;
	}
}
