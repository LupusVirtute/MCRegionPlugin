package com.lupus;

import com.lupus.command.framework.commands.ILupusCommand;
import com.lupus.commands.PlotMainSupCommand;
import com.lupus.commands.PlotsMainCMD;
import com.lupus.gui.SelectableItem;
import com.lupus.gui.manager.sub.gui.PremiumIconPaginator;
import com.lupus.listeners.RegionListener;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.runnables.BackupRunnable;
import com.lupus.runnables.RegionCheckRunnable;
import com.lupus.utils.Skulls;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Plugin(name="LupusPlot", version="1.0-SNAPSHOT")
@Description(value = "Simple plot creator")
@Author(value = "LupusVirtute")
@Website(value="github.com/PuccyDestroyerxXx")

@Dependency(value = "WorldGuard")
@Dependency(value = "WorldEdit")
@Dependency(value = "Vault")
@Dependency(value = "LupusUtils")
@Dependency(value = "LupusCommandFramework")
@ApiVersion(value = ApiVersion.Target.v1_15)

@Permission(name = "plot.vip")
@Permission(name = "plot.premium")
@Permission(name = "plot.admin")
@Permission(name = "plot.breakall")
/*
@org.bukkit.plugin.java.annotation.command.Command(name = "dzialka",desc = "Plot command",aliases = {"dz"})
@org.bukkit.plugin.java.annotation.command.Command(name = "plots",desc = "Admin Plot command")
*/
public class RegionPlugin extends JavaPlugin {
	static File dataFolder;
	static JavaPlugin plugin;
	private static Economy econ = null;
	static BukkitTask regionCheckRunnable;
	public static File getMainDataFolder(){
		return dataFolder;
	}

	public static JavaPlugin getMainPlugin() {
		return plugin;
	}
	public static Economy getEconomy(){
		return econ;
	}

	@Override
	public void onEnable() {
		System.out.println("Lupus Region Manager Turning on...");
		if (!setupEconomy() ) {
			getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		dataFolder = this.getDataFolder();
		plugin = this;
		initSerializable();
		getServer().getPluginManager().registerEvents(new RegionListener(),this);
		RegionManager.reloadRegions();
		regionCheckRunnable = new RegionCheckRunnable().runTaskTimerAsynchronously(this,0,10);
		System.out.println("Lupus Region Manager Turned on");
		try {
			loadPremiumIcons();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onDisable(){
		System.out.println("Lupus Region Manager Turning off");
		regionCheckRunnable.cancel();
		System.out.println("Saving Regions");
		RegionManager.saveRegions(false);
		System.out.println("Regions Saved");
		RegionManager.clear();
		System.out.println("Region Manager cleared");
		System.out.println("Lupus Region Manager Turned off");
		try {
			savePremiumIcons();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void initSerializable(){
		ConfigurationSerialization.registerClass(Region.class);
	}
	private void loadPremiumIcons() throws IOException {
		File file = new File(this.getDataFolder(),"PremiumIcons.yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (!config.contains("Skulls"))
			config.createSection("Skulls");
		List<String> b64PremiumIcons = config.getStringList("Skulls");
		if (b64PremiumIcons.size() <= 0)
			return;
		List<ItemStack> premiumIcons = new ArrayList<>();
		for (String b64PremiumIcon : b64PremiumIcons) {
			premiumIcons.add(
				Skulls.setSkullTexture(new ItemStack(Material.PLAYER_HEAD),b64PremiumIcon)
			);
		}
		PremiumIconPaginator.addPremiumIcons(premiumIcons);
	}
	private void savePremiumIcons() throws IOException {
		List<ItemStack> items = PremiumIconPaginator.getPremiumIcons();
		if (items.size() <=0 )
			return;
		List<String> b64Textures = new ArrayList<>();
		for (ItemStack item : items) {
			String b64 = Skulls.getB64SkullTexture(item);
			b64Textures.add(b64);
		}
		File file = new File(this.getDataFolder(),"PremiumIcons.yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (!config.contains("Skulls"))
			config.createSection("Skulls");
		config.set("Skulls",b64Textures);
		config.save(file);
	}
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
/*	static ILupusCommand[] commands = {
			new PlotMainSupCommand(),
			new PlotsMainCMD()
	};
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String cmd = command.getName().toLowerCase();
		for (ILupusCommand lupCommand : commands){
			if (lupCommand.isMatch(cmd)) {
				lupCommand.execute(sender,args);
				break;
			}
		}
		return super.onCommand(sender, command, label, args);
	}*/
}
