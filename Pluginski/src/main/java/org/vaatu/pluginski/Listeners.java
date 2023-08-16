package org.vaatu.pluginski;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Listeners implements Listener {

    ArrayList<Player> activeChickenCannon = new ArrayList<>();

    @EventHandler
    @SuppressWarnings("unchecked")
    public void on(PlayerMoveEvent evt) {
        Player p = evt.getPlayer();
        Location playerLocation = p.getLocation();

        if (playerLocation.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE
                && playerLocation.subtract(0, 1, 0).getBlock().getType() == Material.REDSTONE_BLOCK) {
            p.setVelocity(p.getLocation().getDirection().multiply(15).setY(1.5));
        }
    }

    @EventHandler
    public void on(PlayerInteractEvent evt) {
        Player p = evt.getPlayer();


        if (evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material itemMaterial = p.getItemInHand().getType();

            //p.getServer().getScheduler().getPendingTasks(). complete: if pending task, end it and do nothing.

            switch (itemMaterial) {
                case WOODEN_AXE -> chickenCannon(p);
            }
        } else if (evt.getAction() == Action.LEFT_CLICK_AIR || evt.getAction() == Action.LEFT_CLICK_BLOCK) {
            Material itemMaterial = p.getItemInHand().getType();

            switch (itemMaterial) {
                case WOODEN_AXE -> p.performCommand("allahuakbar");
            }
        }
    }

    public void chickenCannon(Player p) {
        if (p.getItemInHand().getType() == Material.WOODEN_AXE) {
            Location playerLocation = p.getLocation();
            Vector direction = playerLocation.getDirection();
            Chicken c = (Chicken) p.getWorld().spawnEntity(playerLocation, EntityType.CHICKEN, true);
            c.setGliding(true);
            c.setInvulnerable(true);
            c.setVelocity(direction.multiply(3));

            p.getServer().getScheduler().scheduleSyncDelayedTask(
                    Bukkit.getPluginManager().getPlugin("Pluginski"),
                    () -> chickenCannon(p), 1
            );
        }
    }
}
