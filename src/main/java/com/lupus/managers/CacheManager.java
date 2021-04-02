package com.lupus.managers;

import com.lupus.RegionPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CacheManager implements ConfigurationSerializable {
	public CacheManager(Map<String,Object> map){
		for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
			if (!(stringObjectEntry.getValue() instanceof String))
				continue;
			playerUUIDHash.put(stringObjectEntry.getKey(),UUID.fromString((String)stringObjectEntry.getValue()) );
		}
	}
	private static CacheManager INSTANCE = new CacheManager();
	private CacheManager(){
		File file = new File(RegionPlugin.getMainDataFolder(),"cache.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
	}
	private Map<String,UUID> playerUUIDHash = new HashMap<>();
	public static CacheManager getInstance() {
		return INSTANCE;
	}
	public UUID getPlayer(String nick){
		return playerUUIDHash.get(nick);
	}
	public void setPLayer(String nick, UUID playerUUID){
		playerUUIDHash.put(nick,playerUUID);
	}
	public void load(){
		File file = new File(RegionPlugin.getMainDataFolder(),"cache.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.get("");
	}
	public void save(){
		File file = new File(RegionPlugin.getMainDataFolder(),"cache.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("",this);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@NotNull
	@Override
	public Map<String, Object> serialize() {
		Map<String,Object> map = new HashMap<>();
		for (Map.Entry<String, UUID> stringUUIDEntry : playerUUIDHash.entrySet()) {
			map.put(stringUUIDEntry.getKey(),stringUUIDEntry.getValue().toString());
		}
		return map;
	}
}
