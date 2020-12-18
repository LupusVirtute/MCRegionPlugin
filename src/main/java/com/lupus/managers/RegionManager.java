/*
 * Copyright (C) 2020 Korwin at https://github.com/PuccyDestroyerxXx
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.lupus.managers;

import com.lupus.RegionPlugin;
import com.lupus.utils.ColorUtil;
import com.lupus.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korwin at https://github.com/PuccyDestroyerxXx
 */
public class RegionManager {
	private static final List<Region> regions = new ArrayList<>();
	private static final HashMap<UUID, HashSet<UUID>> playersInRegion = new HashMap<>();
	public static void clear(){
		playersInRegion.clear();
		regions.clear();
		InviteManager.clear();
	}
	public static boolean addPlayerToRegion(Player player,Region region){
		return addPlayerToRegion(player.getUniqueId(),region);
	}
	public static boolean addPlayerToRegion(UUID player,Region region){
		if (!playersInRegion.containsKey(player)) {
			HashSet<UUID> playerPlots = region.getMembers();
			playerPlots.add(region.getUniqueId());
			playersInRegion.put(player,playerPlots);
			region.addMember(player);
			return true;
		}
		HashSet<UUID> playerRegions = playersInRegion.get(player);
		for (UUID playerRegion : playerRegions) {
			if (playerRegion.equals(region.getUniqueId())) {
				return false;
			}
		}
		playerRegions.add(region.getUniqueId());
		region.addMember(player);
		return true;
	}
	public static boolean removePlayerFromRegion(UUID player,Region region){
		if(!playersInRegion.containsKey(player)){
			return false;
		}
		HashSet<UUID> playerRegions = playersInRegion.get(player);
		for (UUID playerRegion : playerRegions) {
			if (playerRegion.equals(region.getUniqueId())) {
				playerRegions.remove(playerRegion);
				region.removeMember(player);
				return true;
			}
		}
		return false;
	}
	public static boolean isSaving = false;
	public static void reloadRegions(){
		System.out.println("Initialize Regions");
		if(isSaving){
			System.out.println("Region are being saved aborting...");
			return;
		}
		File regionFile = new File(RegionPlugin.getMainDataFolder(),"Regions.yml");
		FileConfiguration regConfig = YamlConfiguration.loadConfiguration(regionFile);
		if (!doesFileExists(regionFile,regConfig)){
			return;
		}
		RegionManager.clearRegions();
		playersInRegion.clear();
		if(regConfig.contains("Regions")){
			for(String section : regConfig.getConfigurationSection("Regions").getKeys(false))
			{
				String currentSection = "Regions."+section;
				Region r = (Region)regConfig.get(currentSection);
				Bukkit.getLogger().info("Region " + r.getName() + " loaded");
				RegionManager.addRegion(r);
			}
		}
		System.out.println("Region are loaded");
		System.out.println("Loaded plots: " + regions.size());
	}
	private static boolean doesFileExists(File regionFile,FileConfiguration regConfig){
		if(!regionFile.exists()){
			regionFile.getParentFile().mkdirs();
			try {
				regConfig.createSection("Regions");
				regionFile.createNewFile();
				regConfig.save(regionFile);
			} catch (IOException ex) {
				Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "Wystąpiły problemy podczas zapisu działek...");
				Logger.getLogger(RegionManager.class.getName()).log(Level.SEVERE, null, ex);
				return false;
			}
			return true;
		}
		return true;
	}
	public static void saveRegions(boolean backup){
		if (isSaving){
			return;
		}
		String fileName = "Regions.yml";
		if(backup)
			fileName = "Regions"+ new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());
		long startOfSaving = System.currentTimeMillis();
		File regionFile;
		if (backup)
			regionFile = new File(RegionPlugin.getMainDataFolder()+"/backup",fileName);
		else
			regionFile = new File(RegionPlugin.getMainDataFolder(),fileName);

		if (!regionFile.getParentFile().exists()) {
			regionFile.getParentFile().mkdirs();
		}
		if (!regionFile.exists()) {
			try {
				regionFile.createNewFile();
			} catch (IOException ignored) {

			}
		}
		FileConfiguration regConfig = YamlConfiguration.loadConfiguration(regionFile);
		if (!doesFileExists(regionFile,regConfig)){
			return;
		}
		isSaving = true;
		regConfig.set("Regions",null);
		for (Region region : regions) {
			regConfig.set("Regions." + region.getUniqueId(),region);
		}
		try {
			regConfig.save(regionFile);
		} catch (IOException ex) {
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "Wystąpiły problemy podczas zapisu działek...");
			Logger.getLogger(RegionManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		startOfSaving = ((System.currentTimeMillis() - startOfSaving) / 1000);
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Poprawnie zapisano Działki czas: " + ChatColor.YELLOW + startOfSaving + "s");
		isSaving = false;
	}
	public static boolean removePlayerFromRegion(Player player,Region region){
		return removePlayerFromRegion(player.getUniqueId(),region);
	}
	public static HashSet<UUID> getPlayerMemberships(UUID p) {
		if(!playersInRegion.containsKey(p)){
			return new HashSet<>();
		}
		return playersInRegion.get(p);
	}
	public static HashSet<UUID> getPlayerMemberships(Player p){
		return getPlayerMemberships(p.getUniqueId());
	}
	public static boolean doesPlayerBelongInPlot(UUID p,UUID r){
		if(!playersInRegion.containsKey(p)){
			return false;
		}
		HashSet<UUID> plots = playersInRegion.get(p);
		return plots.contains(r);
	}
	public static boolean doesPlayerBelongInPlot(Player p,Region r){
		return doesPlayerBelongInPlot(p.getUniqueId(),r.getUniqueId());
	}
	public static Region getRegionOfOwner(Player p){
		return getRegionOfOwner(p.getUniqueId());
	}
	public static Region getRegionOfOwner(UUID p){
		return regions.
				stream().
				filter(o->o.getOwnerUUID().equals(p)).
				findFirst().
				orElse(null);
	}
	public static String getRegionOwner(Region r){
		return r.getOwnerName();
	}
	public static void clearRegions(){
		regions.clear();
	}

	public static int getRegionAmount(){
		return regions.size();
	}
	public static Region findRegion(int id){
		if(id < 0 || id > regions.size()){
			return null;
		}
		return regions.get(id);
	}
	public static List<String> findRegionsBeginningWith(String search){
		List<String> results = new ArrayList<>();
		String name;
		for (int i=0;i<regions.size();i++) {
			name = regions.get(i).getName();
			if (name.startsWith(search)) {
				results.add(ColorUtil.strip(name));
			}
		}
		return results;
	}
	public static List<String> findRegionsContaining(String search){
		List<String> results = new ArrayList<>();
		String name;
		for (int i=0;i<regions.size();i++) {
			name = regions.get(i).getName();
			if (name.contains(search)) {
				results.add(name);
			}
		}
		return results;
	}
	public static boolean removeRegion(Region region){
		if(region == null){
			return false;
		}
		regions.remove(region);
		playersInRegion.values().forEach(o-> {
			o.remove(region.getUniqueId());
		});
		return true;
	}
	public static Region findRegion(UUID region){
		if(region == null){
			return null;
		}
		for(int i=0;i<regions.size();i++){
			Region reg =regions.get(i);
			if(reg.getUniqueId().equals(region)){
				return reg;
			}
		}
		return null;
	}
	public static Region findRegion(String region){
		if(region == null){
			return null;
		}
		region = ColorUtil.strip(region);
		region = region.toLowerCase();
		for(int i=0;i<regions.size();i++){
			Region reg = regions.get(i);
			if(ColorUtil.strip(reg.getName().toLowerCase()).equals(region)){
				return reg;
			}
		}
		return null;
	}
	public static boolean addRegion(Region region){
		if(region != null){
			if(region.getOwnerName() != null){
				regions.add(region);
				HashSet<UUID> members = region.getMembers();
				for(UUID member : members){
					if (!playersInRegion.containsKey(member)) {
						playersInRegion.put(member,new HashSet<>());
					}
					playersInRegion.get(member).add(region.getUniqueId());
				}
				return true;
			}
		}
		return false;
	}
	public static boolean doesRegionNameAlreadyExist(String name){

		return findRegion(name) != null;
	}
}
