package com.lupus.gui;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Icon implements ConfigurationSerializable {
	ItemStack item;
	boolean isPremium;
	public Icon(ItemStack item){
		this.item = item;
	}
	@Override
	public Map<String, Object> serialize() {
		return null;
	}
}
