package com.lupus.messages;

import com.lupus.utils.ColorUtil;
import org.bukkit.ChatColor;

public enum GeneralMessages {
	LOGO(ColorUtil.text2Color("&7[&8&lM&f&lO&7]")),
	CANT_USE_ON_MYSELF(ChatColor.RED + "Nie możesz użyć tej komendy na sobie"),
	NULL_PLAYER(ChatColor.RED + "Gracz nie istnieje"),
	INSUFFICIENT_MONEY(ChatColor.RED + "Nie wystarczające środki potrzebujesz jeszcze " + ChatColor.GOLD + "%string%$" + ChatColor.RED + " by kupić tą usługę");
	final String message;
	GeneralMessages(String message){
		this.message = message;
	}
	@Override
	public String toString(){
		return message;
	}
	public String toString(String string) {
		return message.replace("%string%",string);
	}
}
