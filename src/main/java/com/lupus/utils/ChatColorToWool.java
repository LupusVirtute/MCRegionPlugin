package com.lupus.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ChatColorToWool {
	public static ChatColorToWool cctw = new ChatColorToWool();
	static HashMap<ChatColor, ItemStack> colors;
	private ChatColorToWool(){
		colors = new HashMap<>();
		colors.put(ChatColor.AQUA,new ItemStack(Material.LEGACY_WOOL,1,(short) 9));
		colors.put(ChatColor.BLACK,new ItemStack(Material.LEGACY_WOOL,1,(short) 15));
		colors.put(ChatColor.BLUE,new ItemStack(Material.LEGACY_WOOL,1,(short) 3));
		colors.put(ChatColor.DARK_AQUA,new ItemStack(Material.LEGACY_WOOL,1,(short) 9));
		colors.put(ChatColor.DARK_BLUE,new ItemStack(Material.LEGACY_WOOL,1,(short) 11));
		colors.put(ChatColor.DARK_GRAY,new ItemStack(Material.LEGACY_WOOL,1,(short) 7));
		colors.put(ChatColor.DARK_GREEN,new ItemStack(Material.LEGACY_WOOL,1,(short) 13));
		colors.put(ChatColor.DARK_PURPLE,new ItemStack(Material.LEGACY_WOOL,1,(short) 2));
		colors.put(ChatColor.DARK_RED,new ItemStack(Material.LEGACY_WOOL,1,(short) 14));
		colors.put(ChatColor.GOLD,new ItemStack(Material.LEGACY_WOOL,1,(short) 4));
		colors.put(ChatColor.GREEN,new ItemStack(Material.LEGACY_WOOL,1,(short) 13));
		colors.put(ChatColor.LIGHT_PURPLE,new ItemStack(Material.LEGACY_WOOL,1,(short) 10));
		colors.put(ChatColor.RED,new ItemStack(Material.LEGACY_WOOL,1,(short) 6));
		colors.put(ChatColor.WHITE,new ItemStack(Material.LEGACY_WOOL,1,(short) 0));
		colors.put(ChatColor.YELLOW,new ItemStack(Material.LEGACY_WOOL,1,(short) 4));
		colors.put(ChatColor.GRAY,new ItemStack(Material.LEGACY_WOOL,1,(short) 8));
	}
	public ItemStack getItemFromChatColor(ChatColor color){
		return colors.get(color);
	}
}
