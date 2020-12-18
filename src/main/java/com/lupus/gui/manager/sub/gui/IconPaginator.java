package com.lupus.gui.manager.sub.gui;

import com.lupus.gui.Paginator;
import com.lupus.gui.PlotManagerGUI;
import com.lupus.gui.selectables.SelectableIcon;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class IconPaginator extends Paginator {
	Region r;
	public IconPaginator(Region region) {
		super(region.getName());
		r = region;
		Material[] materials = Material.values();
		for(int i=0;i<materials.length;i++){
			ItemStack icon = new ItemStack(materials[i]);
			if (icon.getType() != Material.AIR){
				addItemStack(new SelectableIcon(icon,region));
			}
		}
		setPage(0);
	}

	@Override
	public void onClose(Player player) {

	}
}
