package com.lupus.commands.admin;

import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ForceAddCommand extends LupusCommand {
	public ForceAddCommand(){
		super("forceadd",
				usage("/plots forceadd","[Gracz] [Dzialka]"),
				"&6Dodajesz do działki gracza",
				2);
	}
	@Override
	public void run(CommandSender executor, ArgumentList args) throws Exception{
		Player p = args.getArg(Player.class,0);
		if(p == null) {
			executor.sendMessage(GeneralMessages.PLAYER_OFFLINE.toString());
			return;
		}
		Region r = args.getArg(Region.class,1);
		if(r == null){
			executor.sendMessage(GeneralMessages.NULL_PLOT.toString());
			return;
		}
		RegionManager.addPlayerToRegion(p,r);
		MessageReplaceQuery query = new MessageReplaceQuery().
				addString("player",p.getName()).
				addString("name",r.getName());
		executor.sendMessage(GeneralMessages.SUCCESSFUL_FORCE_ADD.toString(query));
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
