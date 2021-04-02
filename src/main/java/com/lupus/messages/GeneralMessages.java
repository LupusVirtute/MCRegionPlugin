package com.lupus.messages;

import com.lupus.RegionPlugin;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public enum GeneralMessages {
	LOGO(),
	CANT_USE_ON_MYSELF(),
	NULL_PLAYER(),
	INSUFFICIENT_MONEY(),

	// Plot
	PLAYER_OFFLINE(),
	PLOT_ALERADY_EXISTS(),
	PLAYER_ONLY(),
	SUCCESSFUL_LEAVE(),
	PLAYER_LEFT(),
	NO_PLOT(),
	NOT_INSIDE_REGION(),
	CHANGED_SPAWN_SUCCESFULLY(),
	NO_INVITE(),
	NULL_PLOT(),
	NO_BELONG(),
	SOMETHING_WENT_WRONG(),
	MAX_LEVEL(),
	MAX_LEVEL_FOR_PLAYER(),
	ALERADY_OWNS_PLOT(),
	REGION_LOCATION_INVALID(),
	INVALID_INTERACT(),
	INVALID_DAMAGE(),
	ACCEPT_INVITE(),
	DENY_INVITE(),
	OWNER_ACCEPT_INVITE_MESSAGE(),
	INVITE_SEND(),
	INVITE_SUCCESFUL(),
	SUCCESFUL_CREATION(),
	OWNER_DENY_INVITE_MESSAGE(),
	SHOWING_BORDER(),
	SUCCESSFUL_REMOVE(),
	SUCCESSFUL_UPGRADE(),
	DELETE_CONFIRMATION(),
	SUCCESFULL_FORCE_ADD(),
	DELETE_SUCCESS_PLOT_MESSAGE(),
	SUCCESSFUL_CHAT_TURN_OFF(),
	THIS_IS_NOT_PLOT_NAME(),
	NOT_ADDED_TO_PLOT(),
	YOURE_NOW_TALKING_ON_CHAT_PLOT(),
	SUCCESSFUL_RELOAD_MESSAGES(),
	SUCCESSFUL_RELOAD_REGIONS(),
	CHANGED_PLOT_OWNER,
	FATAL_CRASH_XD,
	PLAYER_ONLY_COMMAND;
	GeneralMessages(String text){
		this.text = TextUtility.color(text);
	}
	GeneralMessages(){
		text = this.name();
	}
	public static void reloadMessages(){
		FileConfiguration messages = null;
		try {
			File file = new File(
					RegionPlugin.getMainDataFolder(),"messages.yml"
			);
			messages = YamlConfiguration.loadConfiguration(
				file
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (messages != null) {
			for (GeneralMessages value : GeneralMessages.values()) {
				String textValue = messages.getString(value.name());
				if (textValue != null)
					value.setText(TextUtility.color(textValue));
			}
		}
	}
	private void setText(String text){
		this.text = text;
	}
	String text;
	public String toString(){
		if(this != LOGO)
			return LOGO +" "+ text;
		return text;
	}
	public String toString(MessageReplaceQuery query){
		return toString(query.getQueryMap());
	}
	public String toString(Map<String,String> replacements){
		String copiedText = text;
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			copiedText = copiedText.replace("%"+entry.getKey()+"%",entry.getValue());
		}
		if(this != LOGO)
			return LOGO +" "+ text;
		return text;
	}
}
