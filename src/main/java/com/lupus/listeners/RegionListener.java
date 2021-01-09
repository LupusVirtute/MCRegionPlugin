package com.lupus.listeners;

import com.lupus.managers.RegionManager;
import com.lupus.messages.PlotMessages;
import com.lupus.region.Region;
import com.lupus.utils.WorldUtils;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.projectiles.ProjectileSource;
import org.spigotmc.event.entity.EntityMountEvent;

public class RegionListener implements Listener {
	@EventHandler
	public void onPlayerInteraction(PlayerInteractEvent e){
		if (e.hasBlock()) {
			Player p = e.getPlayer().getPlayer();
			if (p == null) {
				return;
			}
			if (e.getClickedBlock() == null)
				return;
			boolean isBrokenIllegally = isIllegalAction(p, e.getClickedBlock().getLocation());
			if (isBrokenIllegally) {
				p.sendMessage(PlotMessages.INVALID_INTERACT.toString());
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onPlayerInteractWithEntity(PlayerInteractAtEntityEvent e){
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		Location entityLocation = entity.getLocation();
		boolean isIllegal = isIllegalAction(p,entityLocation);
		if (isIllegal){
			p.sendMessage(PlotMessages.INVALID_INTERACT.toString());
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent e){
		HumanEntity humanEntity = e.getWhoClicked();
		if (!(humanEntity instanceof Player)){
			return;
		}
		Player p = (Player)humanEntity;

		InventoryHolder holderInventory = e.getInventory().getHolder();

		if (!(holderInventory instanceof Entity))
			return;
		Entity entity = (Entity) holderInventory;
		Location loc = entity.getLocation();
		boolean isIllegal = isIllegalAction(p,loc);
		if (isIllegal)
			if (holderInventory instanceof Animals)
				e.setCancelled(true);
			else if (holderInventory instanceof AbstractVillager)
				e.setCancelled(true);
	}
	public void onPlayerDamageEntity(EntityDamageByEntityEvent e,Player p){
		if (isIllegalAction(p,e.getEntity().getLocation()))
			switch (e.getCause()){
				case ENTITY_ATTACK:
				case ENTITY_SWEEP_ATTACK:
				case ENTITY_EXPLOSION:
				case FALLING_BLOCK:
				case BLOCK_EXPLOSION:
				case LAVA:
				case POISON:
				case MELTING:
				case DROWNING:
				case FIRE_TICK:
				case HOT_FLOOR:
				case PROJECTILE:
					e.setCancelled(true);
					p.sendMessage(PlotMessages.INVALID_DAMAGE.toString());
					break;
				default:
					break;
			}
	}
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e){
		Projectile proj = e.getEntity();
		if (!(e.getEntity().getShooter() instanceof Player))
			return;
		Player p = (Player) e.getEntity().getShooter();
		boolean illegal = isIllegalAction(p,proj.getLocation());
		if (illegal){
			proj.remove();
			p.sendMessage(PlotMessages.INVALID_DAMAGE.toString());
		}
	}
	@EventHandler
	public void onPlayerMount(EntityMountEvent e){
		Entity entity = e.getEntity();
		if (!(entity instanceof Player))
			return;
		Location loc = e.getMount().getLocation();
		Player p = (Player) e.getEntity();
		boolean isIllegal = isIllegalAction(p,loc);
		if (isIllegal)
			e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerLeash(PlayerLeashEntityEvent e){
		Location loc = e.getEntity().getLocation();
		Player p = e.getPlayer();
		boolean isIllegal = isIllegalAction(p,loc);
		if (isIllegal)
			e.setCancelled(true);
	}
	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent e){
		if (!(e.getEntity() instanceof Player)){
			Entity damager = e.getDamager();
			if (damager instanceof Player){
				onPlayerDamageEntity(e,(Player) damager);
			}
			if (damager instanceof Projectile) {
				Projectile projectile = (Projectile)damager;
				ProjectileSource source =  projectile.getShooter();
				if (source instanceof Player)
					onPlayerDamageEntity(e,(Player)source);
			}
		}
		if (e.getEntity() instanceof Player)
			return;
		Player player;
		if (!(e.getDamager() instanceof Projectile)) {
			return;
		}
		Projectile projectile = (Projectile)e.getDamager();
		if (!(projectile.getShooter() instanceof Player)) {
			return;
		}
		player = (Player) projectile.getShooter();
		boolean illegal = isIllegalAction(player,e.getEntity().getLocation());
		if (illegal) {
			e.setCancelled(true);
			player.sendMessage(PlotMessages.INVALID_DAMAGE.toString());
		}
	}
	public boolean isIllegalAction(Player p, Location location) {
		if (!p.getWorld().getUID().equals(WorldUtils.getMainWorld().getUID()))
			return false;
		if (!p.hasPermission("plot.breakall")) {
			//Region break check
			if (RegionManager.getRegionAmount() > 0) {
				for (Region region : RegionManager.getRegions()) {
					if (region == null){
						continue;
					}
					if (region.contains(location)) {
						if (p.getName().compareTo(region.getOwnerName()) == 0) {
							return false;
						}
						return !region.containsMember(p);
					}
				}
			}
		}
		return false;
	}
}
