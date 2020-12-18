package com.lupus.gui.selectables;

import com.lupus.gui.SelectableItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SelectableItemCommand extends SelectableItem {
	public UUID playerToPerformCommand;
	public String commandToPerform;
	public SelectableItemCommand(ItemStack item, String command, Player p) {
		super(true, item);
		commandToPerform = command;
		playerToPerformCommand = p.getUniqueId();
	}

	@Override
	protected void execute(Object... args) {
		Bukkit.getPlayer(playerToPerformCommand).performCommand(commandToPerform);
	}
}
