package com.rater193.gamemode.extraction.core.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.rater193.gamemode.extraction.core.api.GameWorlds;

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
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	
        Player player = event.getPlayer();

        if(player.getGameMode()!=GameMode.CREATIVE) {
        	if(player.getWorld() == GameWorlds.worldExtraction) {
                // Drop all items in the world
                dropPlayerItems(player);

                // Clear the player's inventory
                clearPlayerInventory(player);
        	}
        }
        
        //(This is to ensure players dont ghost spawn)
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
    }
    
    private void dropPlayerItems(Player player) {
        // Iterate through the player's inventory and drop each item in the world
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }

        // Clear the player's inventory after dropping items
        player.getInventory().clear();
    }

    private void clearPlayerInventory(Player player) {
        // Clear the player's inventory
        player.getInventory().clear();
    }
}
