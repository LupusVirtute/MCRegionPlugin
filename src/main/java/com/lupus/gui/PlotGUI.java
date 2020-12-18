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
package com.lupus.gui;

import com.lupus.gui.items.special.PlotItems;
import com.lupus.messages.PlotStrings;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

/**
 *
 * @author Korwin at https://github.com/PuccyDestroyerxXx
 */
public class PlotGUI extends GUI {
	@Override
	public void open(Player p) {
		if(inv == null || p == null){
			return;
		}
		p.openInventory(inv);
	}
	public PlotGUI(Player p){
		super(PlotStrings.plotGUITitle,19);
		SelectableItem[] sel = PlotItems.init(p);
		items.addAll(Arrays.asList(sel));
		inv.addItem(sel);
	}
	public static String[] commands = {
			"dzialka dodaj",
			"dzialka wyrzuc",
			"dzialka teleport",
			"dzialka ulepsz",
			"dzialka stats",
			"dzialka granica",
			"dzialka usun",
			"dzialka stworz",
			"dzialka nazwa",
			"dzialka panel",
	};
	public String getInventoryName(){
		return "dzialka";
	}
	@Override
	public void onClose(Player p){
	}
}
