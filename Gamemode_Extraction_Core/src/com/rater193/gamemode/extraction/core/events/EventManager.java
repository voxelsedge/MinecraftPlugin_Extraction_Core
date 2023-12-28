package com.rater193.gamemode.extraction.core.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventManager implements Listener {
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Replace "YourSpawnWorld" with the actual name of your spawn world
        World spawnWorld = Bukkit.getWorld("World");

        if (spawnWorld != null) {
            // Replace the coordinates with the spawn location in your world
            Location spawnLocation = spawnWorld.getSpawnLocation();

            // Teleport the player to the spawn location
            player.teleport(spawnLocation);

            // Optionally, send a welcome message or perform other actions
            player.sendMessage("Welcome to the server!");
        } else {
            System.out.println("Spawn world not found!");
        }
    }
}
