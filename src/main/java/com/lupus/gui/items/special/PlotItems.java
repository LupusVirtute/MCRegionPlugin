package com.lupus.gui.items.special;

import com.lupus.gui.PlotGUI;
import com.lupus.gui.SelectableItem;
import com.lupus.gui.selectables.SelectableItemCommand;
import com.lupus.messages.PlotStrings;
import com.lupus.utils.ItemStackUtil;
import com.lupus.utils.Skulls;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlotItems {
	static ItemStack[] items;
	static String[] playerSkullID =
			{
					"5a11ac87-c237-4c32-8609-337f2eae51f2",
					"6e79d58c-f622-4a11-8852-8b9f1e3ea6cc",
					"d2fc8616-0b3c-4602-b724-1b784824fa49",
					"3ef6780e-0625-4c1d-a63e-0879b793b7b8",
					"5e193aa2-292e-43c6-b92b-e823f6e0cc1e",
					"ecc120f0-59d0-49f5-9d5a-1ca99bb08669",
					"b8b9dcbc-0cab-4e6d-9102-e278aeeb3358",
			};
	static String[] textureValues =
			{
					"ZWFlYTVkNzEyNzIxNGVlMmNjYTFiODJlZjgyZjhhZTU1OTI5MzI1NzY2ZWE4NGRkZTE4YzExZGUwYzdkNTkxIn19fQ==",
					"NGU0YjhiOGQyMzYyYzg2NGUwNjIzMDE0ODdkOTRkMzI3MmE2YjU3MGFmYmY4MGMyYzViMTQ4Yzk1NDU3OWQ0NiJ9fX0=",
					"ZTVlOGNjOTliYjQyZGRhMmFhZmJmZjQ1Nzc1Njc3NmIyOGM4ZTM0ZWUyNDVjYzU1M2QyNjk0ZTZiMDRiNzIifX19",
					"OTUzMGE3NDM5M2Y5YmJkNmVkZjVmMzFmMzdjYzJiYzI3MTYzYTc2NjViZGRmY2M0YzdmOTI1OTJlZGYyNDM4In19fQ==",
					"ZjQ1NTlkNzU0NjRiMmU0MGE1MThlNGRlOGU2Y2YzMDg1ZjBhM2NhMGIxYjcwMTI2MTRjNGNkOTZmZWQ2MDM3OCJ9fX0=",
					"NDI0NjMyM2M5ZmIzMTkzMjZlZTJiZjNmNWI2M2VjM2Q5OWRmNzZhMTI0MzliZjBiNGMzYWIzMmQxM2ZkOSJ9fX0=",
					"NmQ4MjEwOTJjZTVlNzU1NzQ1MWM3MjNhMDM0MWU5MGI5M2UwNTY0ZTJiMDE0ODFkZTgxZWVhMjcxZjA0YzViNiJ9fX0=",
			};
	static String defTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
	public static SelectableItem[] init(Player p){
		int amount = textureValues.length+3;
		if (items != null){
			SelectableItem[] selectableItems = new SelectableItem[items.length];
			for (int j=0,i=items.length;j<i;j++){
				selectableItems[j] =  new SelectableItemCommand(items[j], PlotGUI.commands[j],p);
			}

			return selectableItems;
		}
		items = new ItemStack[amount];
		int i;
		for(i = 0; i < textureValues.length;i++)
		{
			items[i] = Skulls.createNameableSkull(playerSkullID[i], defTexture + textureValues[i], PlotStrings.plotGUIDisplayNames[i],PlotStrings.lores[i]);
		}
		ItemStack itemStack = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(PlotStrings.createPlotDisplayName);
		itemMeta.setLore(PlotStrings.createPlotLore);

		itemStack.setItemMeta(itemMeta);
		items[i] = itemStack;
		i++;
		itemStack = new ItemStack(Material.NAME_TAG);
		itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(PlotStrings.plotGUIDisplayNames[7]);
		itemMeta.setLore(Arrays.asList(PlotStrings.lores[7]));
		itemStack.setItemMeta(itemMeta);
		items[i] = itemStack;
		i++;
		itemStack = new ItemStack(Material.ITEM_FRAME);
		ItemStackUtil.setItemTitleAndLore(
				itemStack,
				"&6/dzialka panel",
				new String[]{
						"&9Panel działki w którym możesz robić wszystko",
				}
		);
		items[i] = itemStack;
		return PlotItems.init(p);
	}
}
