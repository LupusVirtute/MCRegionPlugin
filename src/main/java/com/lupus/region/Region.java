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
package com.lupus.region;

import com.lupus.gui.SelectableItem;
import com.lupus.managers.RegionManager;
import org.bukkit.*;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 *
 * @author Korwin at https://github.com/PuccyDestroyerxXx
 */
public class Region implements ConfigurationSerializable {
	private final World world;
	private String name;
	private Location spawn;
	private final UUID uniqueID;
	private UUID ownerUUID;
	private int level;
	private int minX;
	private int minZ;
	private int maxX;
	private int maxZ;
	private int centerX;
	private int centerZ;
	private final HashSet<UUID> members;
	private ItemStack icon;
	private Color particleColor;
	private ChatColor plotColor;
	private boolean deleting = false;

	@SuppressWarnings("unchecked")
	public Region(Map<String,Object> serialized){
		UUID worldUID = UUID.fromString((String)serialized.get("worlduid"));
		world = Bukkit.getWorld(worldUID);
		minX = (int)serialized.get("minX");
		minZ = (int)serialized.get("minZ");
		maxX = (int)serialized.get("maxX");
		maxZ = (int)serialized.get("maxZ");
		if(serialized.containsKey("ownerUUID")) {
			ownerUUID = UUID.fromString((String) serialized.get("ownerUUID"));
		}else{
			ownerUUID = Bukkit.getOfflinePlayer((String)serialized.get("owner")).getUniqueId();
		}
		uniqueID = UUID.fromString((String)serialized.get("uid"));
		if(serialized.containsKey("name")) {
			name = (String) serialized.get("name");
		}
		else{
			name = "null";
		}
		if(serialized.containsKey("icon")){
			icon = new ItemStack((ItemStack)serialized.get("icon"));
		}
		else {
			icon = new ItemStack(Material.ENDER_EYE);
		}
		if (serialized.containsKey("color")){
			plotColor = ChatColor.getByChar((String)serialized.get("color"));
		}else{
			plotColor = ChatColor.YELLOW;
		}
		if (serialized.containsKey("r") && serialized.containsKey("g") && serialized.containsKey("b")){
			particleColor = Color.fromRGB(
					(int)serialized.get("r"),
					(int)serialized.get("g"),
					(int)serialized.get("b")
			);
		}else {
			particleColor = Color.fromRGB(0,255,255);
		}
		members = new HashSet<>();
		if(serialized.containsKey("members")) {
			//Unchecked cast but i am pretty sure it can't get bad
			List<String> memberUUID = (List<String>) serialized.get("members");
			for(int i =0 ;i < memberUUID.size();i++){
				UUID fromString = UUID.fromString(memberUUID.get(i));
				members.add(fromString);
				RegionManager.addPlayerToRegion(fromString,this);
			}

		}
		if(serialized.containsKey("spawn")) {
			spawn = (Location) serialized.get("spawn");
		}
		if(serialized.containsKey("level")) {
			level = (int)serialized.get("level");
		}
		calculateCenter();
	}
	public Region(World world,String name,Location spawn,int level,UUID ownerUUID){
		this.world = world;
		this.spawn = spawn.clone();
		this.name = name;
		this.level = level;
		particleColor = Color.fromRGB(0,255,255);
		this.minX = -(level*16)	+spawn.getBlockX();
		this.maxX = level*16	+spawn.getBlockX();
		this.minZ = -(level*16)	+spawn.getBlockZ();
		this.maxZ = level*16	+spawn.getBlockZ();

		calculateCenter();

		plotColor = ChatColor.YELLOW;

		icon = new ItemStack(Material.ENDER_EYE);

		this.ownerUUID = ownerUUID;
		uniqueID = UUID.randomUUID();
		members = new HashSet<>();
		members.add(ownerUUID);
		Player p = Bukkit.getPlayer(ownerUUID);
	}
	private void calculateCenter(){
		centerX = (minX + maxX)/2;
		centerZ = (minZ + maxZ)/2;
	}
	public int getMinZ(){
		return minZ;
	}
	public int getMaxZ(){
		return maxZ;
	}
	public int getMinX(){
		return minX;
	}
	public int getMaxX(){
		return maxX;
	}
	public ItemStack getIcon(){
		return icon;
	}
	public Color getParticleColor(){
		return particleColor;
	}
	public void setParticleColor(Color color){
		particleColor = color;
	}
	public ChatColor getPlotColor(){
		return plotColor;
	}
	public void setPlotColor(ChatColor color){
		if (color == null)
			return;
		plotColor = color;
	}
	public void setIcon(ItemStack item){
		if (item == null || item.getType() == Material.AIR)
			return;
		icon = item;
	}
	public void addMember(UUID p){
		members.add(p);
	}
	public void addMember(Player p){
		addMember(p.getUniqueId());
	}
	public HashSet<UUID> getMembers(){
		return members;
	}
	public UUID getMember(Player p){
		if(p == null){
			return null;
		}
		UUID playerUUID = p.getUniqueId();
		return members.contains(playerUUID) ? playerUUID : null;
	}
	public UUID getUniqueId(){
		return uniqueID;
	}
	public int getLevel(){
		return level;
	}
	public int getArea(){
		return (int) Math.abs(
				Math.sqrt(
						Math.pow(minX - maxX,2)
								+ Math.pow(minZ - maxZ,2)
				)
		);
	}
	public boolean setDelete(){
		return deleting = !deleting;
	}
	public boolean isDeleting(){
		return deleting;
	}
	public String getOwnerName() {
		Player p = Bukkit.getPlayer(ownerUUID);
		if(p == null){
			OfflinePlayer offP = Bukkit.getOfflinePlayer(ownerUUID);
			if(offP == null){
				return null;
			}
			return offP.getName();
		}
		else{
			return p.getName();
		}
	}
	public UUID getOwnerUUID(){
		return ownerUUID;
	}
	public Player getOwner(){
		return Bukkit.getPlayer(ownerUUID);
	}
	public World getWorld() {
		return world;
	}
	public Location getSpawn(){
		return spawn.clone();
	}
	public boolean isLocationInsideRegion(Location loc){
		return loc.getWorld().equals(loc.getWorld()) &&
				loc.getBlockX() < maxX &&
				loc.getBlockX() > minX &&
				loc.getBlockZ() < maxZ &&
				loc.getBlockZ() > minZ;
	}
	public boolean isLocationNearRegion(@org.jetbrains.annotations.NotNull Location loc){
		return loc.getWorld().equals(loc.getWorld()) &&
				!(
						loc.getBlockX() > (centerX + 160) ||
								loc.getBlockX() < (centerX - 160) ||
								loc.getBlockZ() > (centerZ + 160) ||
								loc.getBlockZ() < (centerZ - 160)
				);
	}
	/*
	public boolean isLocationNearRegion(@org.jetbrains.annotations.NotNull Location loc){
		return loc.getWorld().equals(loc.getWorld()) &&
				!(loc.getBlockX() > (maxX + 128) ||
				loc.getBlockX() < (minX - 128) ||
				loc.getBlockZ() > (maxZ + 128) ||
				loc.getBlockZ() < (minZ - 128));
	}
	*/
	public void delete(){
		members.forEach(uuid -> {
			RegionManager.removePlayerFromRegion(uuid,this);
		});
	}
	public boolean contains(Location location){
		return contains(location.getBlockX(), location.getBlockZ());
	}
	public boolean contains(Region region){
		return region.getWorld().equals(world) && region.minX >= minX && region.maxX <= maxX && region.minZ >= minZ && region.maxZ <= maxZ;
	}
	public boolean contains(int x,int z){
		return x >= minX && x <= maxX && z >= minZ && z <= maxZ;
	}
	public void setSpawn(Location spawn){
		if(spawn == null){
			return;
		}
		if(isLocationInsideRegion(spawn)){
			this.spawn = spawn.clone();
		}
	}
	public void changeName(String newName){
		if(newName.isEmpty()){
			return;
		}
		name = newName;
	}
	public void removeMember(UUID p){
		members.remove(p);
	}
	public void removeMember(Player p){
		UUID memberUID = p.getUniqueId();
		removeMember(memberUID);
	}
	public String getName(){
		if(name == null){
			return "null";
		}
		return plotColor + name;
	}
	public boolean changeOwner(@NotNull Player p){
		UUID ownerNewUUID = p.getUniqueId();
		if(ownerNewUUID == ownerUUID){
			return false;
		}
		ownerUUID = ownerNewUUID;
		return true;
	}
	public void expand(int xAxis,int zAxis){
		minX -= xAxis;
		minZ -= zAxis;
		maxX += xAxis;
		maxZ += zAxis;
		level++;
		calculateCenter();
	}
	@Override
	public Map<String,Object> serialize(){
		Map<String,Object> serializedMap = new HashMap<>();

		serializedMap.put("name", name);
		serializedMap.put("uid", uniqueID.toString());
		serializedMap.put("ownerUUID", ownerUUID.toString());
		serializedMap.put("worlduid",world.getUID().toString());
		serializedMap.put("level",level);
		serializedMap.put("spawn",spawn);
		serializedMap.put("minX",minX);
		serializedMap.put("minZ",minZ);
		serializedMap.put("maxX",maxX);
		serializedMap.put("maxZ",maxZ);
		if (icon == null)
			icon = new ItemStack(Material.ENDER_EYE);
		if (icon instanceof SelectableItem)
			icon = new ItemStack(icon);
		serializedMap.put("icon",icon);
		serializedMap.put("color",plotColor.getChar());
		serializedMap.put("r", particleColor.getRed());
		serializedMap.put("g", particleColor.getBlue());
		serializedMap.put("b", particleColor.getGreen());
		List<String> tempMembersString = new ArrayList<>();
		for (UUID uuid : members) {
			tempMembersString.add(uuid.toString());
		}
		serializedMap.put("members",tempMembersString);
		return serializedMap;
	}

}
