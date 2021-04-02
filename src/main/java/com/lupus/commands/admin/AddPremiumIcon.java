package com.lupus.commands.admin;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.gui.manager.sub.gui.PremiumIconPaginator;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class AddPremiumIcon extends PlayerCommand {
	public AddPremiumIcon() {
		super("addprem",usage("/plots addprem"), TextUtility.color("&5&lAdds Premium icon you hold to menu"),0);
	}

	@Override
	protected void run(Player player, ArgumentList strings) {
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if (itemStack == null || itemStack.getType() == Material.AIR) {
			player.sendMessage(TextUtility.color("&4&lNic nie masz w łapie"));
			return;
		}
		if (!(itemStack.getItemMeta() instanceof SkullMeta)) {
			player.sendMessage(TextUtility.color("&4&lNie będziemy im dawać jakiegoś g****"));
			return;
		}
		PremiumIconPaginator.addPremiumIcon(itemStack);
		player.sendMessage(TextUtility.color("&a&lNowa główka dodana"));
	}
}
