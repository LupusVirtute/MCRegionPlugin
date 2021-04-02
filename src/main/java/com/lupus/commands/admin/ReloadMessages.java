package com.lupus.commands.admin;

import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.messages.GeneralMessages;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.command.CommandSender;

public class ReloadMessages extends LupusCommand {
	public ReloadMessages() {
		super(
				"reloadmsg",
				usage("/plots reloadmsg"),
				"&6Przeładowywuje plik messages.yml",
				0);	}

	@Override
	public void run(CommandSender commandSender,ArgumentList strings) {
		GeneralMessages.reloadMessages();
		commandSender.sendMessage(GeneralMessages.SUCCESSFUL_RELOAD_MESSAGES.toString());
	}
}
