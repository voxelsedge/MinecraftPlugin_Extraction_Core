package com.rater193.gamemode.extraction.core.api;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class GameWorlds {
	public static String worldHomeName = "BunkerWorld";
	public static World worldHome;
	public static String worldExtractionName = "ExtractionWorld";
	public static World worldExtraction;
	
    // Custom chunk generator for a void world
    private static class VoidWorldGenerator extends ChunkGenerator { }
    
    //The logic for creating a new world
    public static World CREATEWORLD(String worldName) {
    	//Return the world if it already exists
    	if(Bukkit.getWorld(worldName) != null) return Bukkit.getWorld(worldName);
    	
    	//Otherwise continue to creating the world
    	WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.generator(new VoidWorldGenerator());
        return Bukkit.createWorld(worldCreator);
    }
	
	public static void INIT() {
        // Specify the name for your new world
        worldHome = CREATEWORLD(worldHomeName);
        worldExtraction = CREATEWORLD(worldExtractionName);
	}
}
