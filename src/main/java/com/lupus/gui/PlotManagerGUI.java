package com.lupus.gui;

import com.lupus.gui.manager.sub.gui.*;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.InventoryUtility;
import com.lupus.utils.ItemStackUtil;
import com.lupus.utils.Skulls;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.UUID;

public class PlotManagerGUI extends GUI {
	static String[] skullTextures = {
			// Golden Chalice with Blue Liquid
			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRkZTRkOTViYTRhNDZkYjdlNTY2YjM0NWY3ODk0ZDFkMjU4Zjg5M2ViOTJjNzgwYjNkYTc3NWVlZGY5MSJ9fX0=",
			// Golden Letter I
			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmViNzlhN2ZjOTU0MjhjYThjODhkNTIwMjgzOTljZDFmM2FkOTczNzY5YzNiOTFhODM2Mjc1NTE5ZjRmYjI5In19fQ==",
			// Golden Nugget
			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ1ZjQ3ZmViNGQ3NWNiMzMzOTE0YmZkYjk5OWE0ODljOWQwZTMyMGQ1NDhmMzEwNDE5YWQ3MzhkMWUyNGI5In19fQ==",
	};
	ItemStack[] items = {
			ItemStackUtil.setItemTitleAndLore(
					new ItemStack(Material.PLAYER_HEAD),
					ColorUtil.text2Color("&aCzłonkowie działki"),
					new String[]{
							"&aKliknij tu",
							"&aaby dowiedzieć się kto zamieszkuje twoją działke",
					}
			),
			ItemStackUtil.setItemTitleAndLore(
					new ItemStack(Material.ENDER_EYE),
					ColorUtil.text2Color("&9Ikony działki"),
					new String[]{
							"&aKliknij tutaj byś mógł",
							"&awybrać twoją ulubioną ikonę działki",
					}
			),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			ItemStackUtil.setItemTitleAndLore(
					Skulls.setSkullTexture(
							null,
							skullTextures[0]
					),
					"&1K&2o&3l&4o&5r&6y &bGranicy działki",
					new String[]{
							"&aZmieniasz kolor swojej granicy działki na inny O.o",
							"&6Dostępne tylko dla rangi SVip i wyżej",
					}
			),
			ItemStackUtil.setItemTitleAndLore(
					Skulls.setSkullTexture(
							null,
							skullTextures[1]
					),
					"&6Kolorowanie nazwy działki",
					new String[]{
							"&aZmieniasz kolor nazwy swojej działki",
							"&6Dostępne tylko dla rangi SVip i wyżej",
					}
			),
			ItemStackUtil.setItemTitleAndLore(
					Skulls.setSkullTexture(
							null,
							skullTextures[2]
					),
					"&6Ikony Premium",
					new String[]{
							"&aKliknij tutaj byś mógł",
							"&awybrać twoją ulubioną ikonę działki",
							"&6Dostępne tylko dla rangi SVip i wyżej",
					}
			),

	};


	public PlotManagerGUI() {
		super(ColorUtil.text2Color("&1Panel Działki"), 27);
		ItemStack exitGlass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemStackUtil.setItemTitle(exitGlass, GeneralMessages.LOGO.toString());
		InventoryUtility.fillBorder(exitGlass,inv);
		for(int i=0;i<items.length;i++){
			inv.setItem(10+i,items[i]);
		}
	}
	// TODO: Functions
	@Override
	public void click(Player player, InventoryClickEvent e) {
		int slot = e.getRawSlot()-10;
		if (slot > items.length){
			return;
		}
		Region r = RegionManager.getRegionOfOwner(player);
		if (r == null) {
			player.sendMessage(PlotMessages.NO_PLOT.toString());
			return;
		}
		switch(slot){
			case 0:{
				Set<UUID> set = r.getMembers();
				UUID[] plots;
				plots = set.toArray(new UUID[0]);
				new MemberPaginator(r.getName(),plots).open(player);
				break;
			}
			case 1: {
				new IconPaginator(r).open(player);
				break;
			}
			case 4:{
				if (!player.hasPermission("plot.premium")){
					player.sendMessage(ColorUtil.text2Color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
					break;
				}
				new PlotParticleColorGUI(r).open(player);
				break;
			}
			case 5:{
				if (!player.hasPermission("plot.premium")){
					player.sendMessage(ColorUtil.text2Color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
					break;
				}
				new PlayerNameColorPlotGUI(r).open(player);
				break;
			}
			case 6:{
				if (!player.hasPermission("plot.premium")){
					player.sendMessage(ColorUtil.text2Color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
					break;
				}
				new PremiumIconPaginator(r).open(player);
				break;
			}
		}
		return;
	}

	@Override
	public void onClose(Player player) {
		return;
	}
}
