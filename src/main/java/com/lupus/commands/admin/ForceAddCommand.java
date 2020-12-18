package com.lupus.commands.admin;

import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.messages.PlotMessages;
import com.lupus.utils.Usage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ForceAddCommand extends LupusCommand {
	public ForceAddCommand(){
		super("forceadd",
				Usage.usage("/plots forceadd","[Gracz] [Dzialka]"),
				"&6Dodajesz do działki gracza",
				2);
	}
	@Override
	public void run(CommandSender executor, String[] args) {
		Player p = Bukkit.getPlayerExact(args[0]);
		if(p == null) {
			executor.sendMessage(PlotMessages.PLAYER_OFFLINE.toString());
			return;
		}
		Region r = RegionManager.findRegion(args[1]);
		if(r == null){
			executor.sendMessage(PlotMessages.NULL_PLOT.toString());
			return;
		}
		RegionManager.addPlayerToRegion(p,r);
		executor.sendMessage("Poprawnie dodano gracza "+p.getName()+" do działki "+r.getName());
	}
	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (args.length == 2) {
			String lastWord = args[args.length - 1];
			return RegionManager.findRegionsBeginningWith(lastWord);
		}
		else
			return super.tabComplete(sender, alias, args);
	}
}
