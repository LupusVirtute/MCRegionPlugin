package com.lupus.gui.manager.sub.gui;

import com.lupus.gui.GUI;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.gui.utils.ItemUtility;
import com.lupus.region.Region;
import com.lupus.gui.utils.TextUtility;
import com.lupus.utils.ChatColorToWool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerNameColorPlotGUI extends GUI {
	Region plot;
	ChatColor selected;

	public PlayerNameColorPlotGUI(Region r) {
		super(r.getName(), 27);
		plot = r;
		ChatColor[] colours = ChatColor.values();
		for (int i = 0; i < 16; i++) {
			ChatColor value = colours[i];
			inv.setItem(i,
				ItemUtility.setItemTitleAndLore(
					ChatColorToWool.cctw.getItemFromChatColor(value),
					value + "Kolor",
						Arrays.asList("&6Wejście na działke będzie wyglądało tak:",
								"&9Wchodzisz na działke: " + value + TextUtility.strip(r.getName()))
			));
		}
		ItemStack deny = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta meta = deny.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED+"Anuluj Zmiany");
		deny.setItemMeta(meta);

		ItemStack accept = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		meta = accept.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Akceptuj Zmiany");
		accept.setItemMeta(meta);
		inv.setItem(21,deny);
		inv.setItem(23,accept);
	}
	public void updateInfoMeta(){
		ItemStack stack = inv.getItem(23);
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(Arrays.asList(TextUtility.color(
				new String[]{
						"&6&lWejście na działke będzie wyglądało tak:",
						"&9Wchodzisz na działke: "+selected+ TextUtility.strip(plot.getName())
				}
		)));
	}
	@Override
	public void click(Player player, InventoryClickEvent e) {
		int clickedSlot = e.getRawSlot();
		switch (clickedSlot){
			case 21:{
				player.closeInventory();
				new PlotManagerGUI().open(player);
				return;
			}
			case 23:{
				updateRegion();
				player.closeInventory();
				new PlotManagerGUI().open(player);
				return;
			}
		}
		if (clickedSlot > 15){
			return;
		}
		selected = ChatColor.values()[clickedSlot];
		updateInfoMeta();
		player.updateInventory();
		return;
	}
	public void updateRegion(){
		plot.setPlotColor(selected);
	}
	@Override
	public void onClose(Player player) {
	}
}
