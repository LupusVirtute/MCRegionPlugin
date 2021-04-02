package com.lupus.commands;


import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.SupCommand;
import com.lupus.commands.admin.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PlotsMainCMD extends SupCommand {
	public PlotsMainCMD() {
		super("plots", usage("/plots help"),
				"&6Komenda do zarządzania działkami",
				new ArrayList<>(),
				Arrays.asList("plots.admin"),
				1,
				new LupusCommand[]{
						new AddPremiumIcon(),
						new ChangeOwnerCommand(),
						new ForceAddCommand(),
						new FindRegionCMD(),
						new GoToCommand(),
						new HelpCMD(),
						new ListCommand(),
						new ReloadMessages(),
						new ReloadRegions(),
						new RemoveCommand(),
						new SaveCommand(),
						new SetSpawnCommand(),
						new PlayerFindCMD(),
				});
	}
}
