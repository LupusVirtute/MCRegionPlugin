package com.lupus.gui.manager.sub.gui;


import com.lupus.gui.PlayerPaginator;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MemberPaginator extends PlayerPaginator {
	public MemberPaginator(String regionName, UUID[] members) {
		super(regionName, members);
		addToSpecialLore(new String[]{
			TextUtility.color("&9Ten gracz należy do twojej działki"),
			TextUtility.color("&4jeśli chcesz go wyrzucić naciśnij tu"),
		});
		setPage(0);
	}

	@Override
	public void onClickPlayer(Player player, OfflinePlayer clickedOn) {
		player.performCommand("dzialka wyrzuc "+clickedOn.getName());
	}

	@Override
	public void onClose(Player player) {
	}
}
