package com.lupus.commands.admin;

import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.gui.manager.sub.gui.PremiumIconPaginator;
import com.lupus.utils.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Skull;
import com.lupus.utils.Skulls;

public class AddPremiumIcon extends PlayerCommand {
	public AddPremiumIcon() {
		super("addprem",usage("/plots addprem"), ColorUtil.text2Color("&5&lAdds Premium icon you hold to menu"),0);
	}

	@Override
	protected void run(Player player, String[] strings) {
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if (itemStack == null || itemStack.getType() == Material.AIR) {
			player.sendMessage(ColorUtil.text2Color("&4&lNic nie masz w łapie"));
			return;
		}
		if (!(itemStack.getItemMeta() instanceof SkullMeta)) {
			player.sendMessage(ColorUtil.text2Color("&4&lNie będziemy im dawać jakiegoś g****"));
			return;
		}
		PremiumIconPaginator.addPremiumIcon(itemStack);
		player.sendMessage(ColorUtil.text2Color("&a&lNowa główka dodana"));
	}
}
