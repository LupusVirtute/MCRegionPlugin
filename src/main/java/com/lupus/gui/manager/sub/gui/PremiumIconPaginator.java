package com.lupus.gui.manager.sub.gui;

import com.lupus.gui.NonSelectableItem;
import com.lupus.gui.Paginator;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.gui.SelectableItem;
import com.lupus.region.Region;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PremiumIconPaginator extends Paginator {
	static List<ItemStack> premiumIcons = new ArrayList<>();
	public static void addPremiumIcon(ItemStack premium){
		if (premium == null)
			return;
		premiumIcons.add(premium);
	}
	public static void addPremiumIcons(List<ItemStack> premium){
		if (premium == null)
			return;
		List<ItemStack> items = new ArrayList<>(premium);
		premiumIcons.addAll(items);
	}
	public static List<ItemStack> getPremiumIcons(){
		return premiumIcons;
	}
	Region r;
	public PremiumIconPaginator(Region r) {
		super(r.getName());
		this.r = r;
		List<SelectableItem> list = new ArrayList<>();
		for (ItemStack premiumIcon : premiumIcons) {
			list.add(new NonSelectableItem(premiumIcon));
		}
		this.items = list;
		setPage(0);
	}

	@Override
	public void onSlotInteraction(Player player, InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;
		ItemStack iconNew = new ItemStack(e.getCurrentItem());
		r.setIcon(iconNew);
		player.sendMessage(TextUtility.color("&a&lPoprawnie ustawiono nową ikone"));
		new PlotManagerGUI().open(player);
	}

	@Override
	public void onClose(Player player) {
	}
}
