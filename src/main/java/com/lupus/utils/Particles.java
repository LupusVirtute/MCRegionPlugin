package com.lupus.utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Particles {
	private static final double RENDER_DISTANCE = 16.0;
	public static void spawnColoredParticleForPlayer(Player p, Particle particleToUse, Location loc, Particle.DustOptions options){
		p.spawnParticle(particleToUse, loc,1,options);
	}
	public static void visualizeRectangularBorderForPlayer(World warudo, int maxX, int maxZ, int minX, int minZ, Player p, Color color) {
		if (!p.isOnline()) {
			return;
		}
		if (!p.getLocation().getWorld().getUID().equals(warudo.getUID()))
			return;
		maxZ++;
		minX--;
		Location loc;
		Particle particleToUse = Particle.REDSTONE;
		Particle.DustOptions options = new Particle.DustOptions(color,4);
		final int pYLoc = p.getLocation().getBlockY();
		for (int i = minX; i <= maxX; i++) {
			loc = new Location(warudo, i, pYLoc, maxZ);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
			loc = new Location(warudo, i, pYLoc, minZ+1);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
		}
		for (int i = minZ; i <= maxZ; i++) {
			loc = new Location(warudo, minX+1, pYLoc, i);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
			loc = new Location(warudo, maxX, pYLoc, i);
			if (loc.distance(p.getLocation()) < 16.0) {
				spawnColoredParticleForPlayer(p,particleToUse,loc,options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
				spawnColoredParticleForPlayer(p,particleToUse,loc.add(0, 1, 0),options);
			}
		}
	}
	public static void VisualizeRectangularBorderForWorld(World warudo, int minX, int minZ, int maxX, int maxZ,int yCoordinate) {
		Location loc;
		Particle particleToUse = Particle.PORTAL;
		for (int i = maxX; i < minX; i++) {
			loc = new Location(warudo, i, yCoordinate, maxZ);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = maxZ; i < minZ; i++) {
			// z iteration
			loc = new Location(warudo, maxX, yCoordinate, i);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = minX; i > maxX; i--) {
			// x decrementation
			loc = new Location(warudo, i, yCoordinate, minZ);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
		for (int i = minZ; i > maxZ; i--) {
			// z decrementation
			loc = new Location(warudo, minX, yCoordinate, i);
			warudo.spawnParticle(particleToUse, loc, 0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0),0,0.0001d,1.0d,1d,1d);
			warudo.spawnParticle(particleToUse, loc.add(0, 1, 0), 0,0.0001d,1.0d,1d,1d);
		}
	}

}