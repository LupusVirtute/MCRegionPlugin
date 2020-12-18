package com.lupus.gui.manager.sub.gui;

import com.lupus.gui.GUI;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.region.Region;
import com.lupus.utils.ItemStackUtil;
import com.lupus.utils.Skulls;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlotParticleColorGUI extends GUI {
	int rValue = 0;
	int gValue = 0;
	int bValue = 0;
	Region plot;
	public static final int MAX_VALUE = 256;
	public PlotParticleColorGUI(Region plot) {
		super(plot.getName(), 27);
		this.plot = plot;
		ItemStack redWool = new ItemStack(Material.RED_WOOL);
		ItemStack greenWool = new ItemStack(Material.GREEN_WOOL);
		ItemStack blueWool = new ItemStack(Material.BLUE_WOOL);
		ItemStackUtil.setItemTitleAndLore(
			redWool,
			"&4Ilość Czerwieni",
			new String[]{
				"&9Klikając na liczby po prawej stronie możesz",
				"&9Zarządzać jak &4czerwone&9 mają być cząsteczki"
			}
		);
		ItemStackUtil.setItemTitleAndLore(
			greenWool,
			"&aIlość Zieleni",
			new String[]{
				"&9Klikając na liczby po prawej stronie możesz",
				"&9Zarządzać jak &azielone&9 mają być cząsteczki"
			}
		);
		ItemStackUtil.setItemTitleAndLore(
			blueWool,
			"&bIlość Niebieskiego",
			new String[]{
				"&9Klikając na liczby po prawej stronie możesz",
				"&9Zarządzać jak &bniebieskie &9mają być cząsteczki"
			}
		);
		ItemStack deny = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta meta = deny.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED+"Anuluj Zmiany");
		deny.setItemMeta(meta);

		ItemStack accept = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		meta = accept.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Akceptuj Zmiany");
		accept.setItemMeta(meta);
		inv.setItem(8,deny);
		inv.setItem(17,accept);


		inv.setItem(0,redWool);
		inv.setItem(9,greenWool);
		inv.setItem(18,blueWool);
		addRValue(plot.getParticleColor().getRed());
		addGValue(plot.getParticleColor().getGreen());
		addBValue(plot.getParticleColor().getBlue());
	}

	@Override
	public void click(Player player, InventoryClickEvent e) {
		ItemStack clickedItem = e.getCurrentItem();
		if (clickedItem == null)
			return;
		int clickedSlot = e.getRawSlot();
		switch (clickedSlot) {
			case 8:{
				player.closeInventory();
				new PlotManagerGUI().open(player);
				e.setCancelled(false);
				return;
			}
			case 17:{
				updateRegion();
				player.closeInventory();
				new PlotManagerGUI().open(player);
				e.setCancelled(false);
				return;
			}
		}
		if (Skulls.isThisItemANumberSkull(clickedItem)) {
			int value;
			double pow = Math.pow(10, Math.abs(clickedSlot % 9 - 3));
			if (e.getClick().isLeftClick()) {
				value = (int) pow;
			}
			else if (e.getClick().isRightClick()){
				value = (int)-pow;
			}
			else {
				e.setCancelled(true);
				return;
			}
			if (clickedSlot <=3)
				addRValue(value);
			else if (clickedSlot <= 12)
				addGValue(value);
			else if (clickedSlot <= 21)
				addBValue(value);
			e.setCancelled(true);
		}
	}
	public void updateRegion(){
		plot.setParticleColor(Color.fromRGB(rValue,gValue,bValue));
	}
	public void addRValue(int valueAmount){
		rValue += valueAmount;
		if (rValue >= MAX_VALUE)
			rValue = 1;
		else if(rValue < 0) {
			rValue = 1;
		}
		Skulls.intToSkullConverter(inv,rValue,1,3);
	}
	public void addGValue(int valueAmount){
		gValue += valueAmount;
		if (gValue >= MAX_VALUE)
			gValue = 1;
		else if(gValue < 0) {
			gValue = 1;
		}
		Skulls.intToSkullConverter(inv,gValue,10,12);
	}
	public void addBValue(int valueAmount){
		bValue += valueAmount;
		if (bValue >= MAX_VALUE)
			bValue = 1;
		else if(bValue < 0) {
			bValue = 1;
		}
		Skulls.intToSkullConverter(inv,bValue,19,21);
	}
	@Override
	public void onClose(Player player) {
		return;
	}
}
