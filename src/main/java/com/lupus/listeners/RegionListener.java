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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;

public class RegionListener implements Listener {
	@EventHandler
	public void onPlayerInteraction(PlayerInteractEvent e){
		if (e.hasBlock()) {
			Player p = e.getPlayer().getPlayer();
			if (p == null) {
				return;
			}
			boolean isBrokenIllegally = isIllegalAction(p, e.getClickedBlock().getLocation());
			if (isBrokenIllegally) {
				p.sendMessage(PlotMessages.INVALID_INTERACT.toString());
				e.setCancelled(true);
			}
		}
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
	public void onEntityDamageEntity(EntityDamageByEntityEvent e){
		if (!(e.getEntity() instanceof Player)){
			Entity damager = e.getDamager();
			if (damager instanceof Player){
				onPlayerDamageEntity(e,(Player) damager);
			}
			if (damager instanceof Projectile){
				Projectile projectile = (Projectile)damager;
				ProjectileSource source=  projectile.getShooter();
				if (source instanceof Player)
					onPlayerDamageEntity(e,(Player)source);
			}
		}
		if (!(e.getEntity() instanceof ItemFrame) || !(e.getEntity() instanceof ArmorStand))
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
		isIllegalAction(player,e.getEntity().getLocation());
	}
	public boolean isIllegalAction(Player p, Location location) {
		if (!p.hasPermission("plot.breakall")) {
			if (!p.getWorld().getUID().equals(WorldUtils.getMainWorld().getUID()))
				return false;
			//Region break check
			if (RegionManager.getRegionAmount() > 0) {
				for (int i = 0; i < RegionManager.getRegionAmount(); i++) {
					Region region = RegionManager.findRegion(i);
					if (region == null){
						continue;
					}
					if (region.contains(location)) {
						if (p.getName().compareTo(region.getOwnerName()) == 0) {
							return false;
						}
						return region.getMember(p) == null;
					}
				}
			}
		}
		return false;
	}
}
