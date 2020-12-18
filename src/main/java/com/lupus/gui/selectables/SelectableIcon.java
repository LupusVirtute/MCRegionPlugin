package com.lupus.gui.selectables;

import com.lupus.gui.PlayerSelectableItem;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.gui.SelectableItem;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SelectableIcon extends PlayerSelectableItem {
	Region r;
	public SelectableIcon(ItemStack item,Region r) {
		super(true, item);
		this.r = r;
	}

	@Override
	protected void execute(Player player,Object... args) {
		ItemStack iconNew = this.clone();
		r.setIcon(iconNew);
		player.sendMessage(ColorUtil.text2Color("&a&lPoprawnie ustawiono nową ikone"));
		new PlotManagerGUI().open(player);
	}
}
