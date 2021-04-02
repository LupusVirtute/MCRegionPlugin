package com.lupus.gui;

import com.lupus.gui.manager.sub.gui.*;
import com.lupus.gui.utils.*;
import com.lupus.gui.utils.coordinates.Vector2D;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
// TODO REFACTOR THIS MF SHIT
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
			ItemUtility.setItemTitleAndLore(
					new ItemStack(Material.PLAYER_HEAD),
					TextUtility.color("&aCzłonkowie działki"),
					Arrays.asList("&aKliknij tu",
							"&aaby dowiedzieć się kto zamieszkuje twoją działke")
			),
			ItemUtility.setItemTitleAndLore(
					new ItemStack(Material.ENDER_EYE),
					TextUtility.color("&9Ikony działki"),
					Arrays.asList(
							"&aKliknij tutaj byś mógł",
							"&awybrać twoją ulubioną ikonę działki"
					)
			),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			ItemUtility.setItemTitleAndLore(
					SkullUtility.getFromTextureB64(
							skullTextures[0]
					),
					"&1K&2o&3l&4o&5r&6y &bGranicy działki",
					Arrays.asList("&aZmieniasz kolor swojej granicy działki na inny O.o",
							"&6Dostępne tylko dla rangi SVip i wyżej")
			),
			ItemUtility.setItemTitleAndLore(
					SkullUtility.getFromTextureB64(
							skullTextures[1]
					),
					"&6Kolorowanie nazwy działki",
					Arrays.asList("&aZmieniasz kolor nazwy swojej działki",
							"&6Dostępne tylko dla rangi SVip i wyżej")
			),
			ItemUtility.setItemTitleAndLore(
					SkullUtility.getFromTextureB64(
							skullTextures[2]
					),
					"&6Ikony Premium",
					Arrays.asList("&aKliknij tutaj byś mógł",
							"&awybrać twoją ulubioną ikonę działki",
							"&6Dostępne tylko dla rangi SVip i wyżej")
			),

	};


	public PlotManagerGUI() {
		super(TextUtility.color("&1Panel Działki"), 27);
		ItemStack exitGlass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		ItemUtility.setItemTitle(exitGlass, GeneralMessages.LOGO.toString());
		SlotUtility.fillBorder(inv,new Vector2D<>(0,0),new Vector2D<>(8,3),exitGlass);
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
			player.sendMessage(GeneralMessages.NO_PLOT.toString());
			return;
		}
		switch(slot){
			case 0:{
				Set<UUID> set = r.getMembers();
				UUID[] plots;
				plots = set.toArray(new UUID[0]);
				for (UUID uuid : plots){
					OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
					if (p.getName() == null) {
						RegionManager.removePlayerFromRegion(uuid,r);
					}
				}
				set = r.getMembers();
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
					player.sendMessage(TextUtility.color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
					break;
				}
				new PlotParticleColorGUI(r).open(player);
				break;
			}
			case 5:{
				if (!player.hasPermission("plot.premium")){
					player.sendMessage(TextUtility.color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
					break;
				}
				new PlayerNameColorPlotGUI(r).open(player);
				break;
			}
			case 6:{
				if (!player.hasPermission("plot.premium")){
					player.sendMessage(TextUtility.color("&cTa opcja jest tylko dla graczy z rangą &6&lSVIP &ci wyżej"));
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
