/*
 * Copyright (C) 2020 Korwin at https://github.com/PuccyDestroyerxXx
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.lupus.gui.manager.sub.gui;


import com.lupus.gui.GUI;
import com.lupus.gui.SelectableItem;
import com.lupus.gui.selectables.SelectableItemCommand;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Korwin at https://github.com/PuccyDestroyerxXx
 */
public class PlotTeleportGUI extends GUI {
	public PlotTeleportGUI(@NotNull Player player) {
		super( ChatColor.AQUA + "Telep" + ChatColor.DARK_AQUA + "orty", RegionManager.getPlayerMemberships(player).size());
		HashSet<UUID> regionList = RegionManager.getPlayerMemberships(player);
		if (getInventory() == null)
			return;

		for(UUID regionUUID : regionList){
			if (inv.firstEmpty() == -1)
				break;
			Region r = RegionManager.findRegion(regionUUID);
			if (r == null)
				continue;
			String displayName = ChatColor.YELLOW + r.getName();
			
			List<String> plotInfo = new ArrayList<>();
			plotInfo.add(ChatColor.GOLD + "Nazwa: " +ChatColor.AQUA+ r.getName());
			plotInfo.add(ChatColor.GOLD + "Właściciel: " +ChatColor.AQUA+ r.getOwnerName());
			
			ItemStack itemStack = r.getIcon();
			if (itemStack == null)
				continue;
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(displayName);
			itemMeta.setLore(plotInfo);
			itemStack.setItemMeta(itemMeta);
			SelectableItemCommand commandItem = new SelectableItemCommand(itemStack,"dzialka teleport "+r.getName(),player);
			items.add(commandItem);
			inv.addItem(commandItem);
		}

		player.openInventory(getInventory());
	}
	@Override
	public void onClose(Player p){
		return;
	}
}
