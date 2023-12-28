package com.rater193.gamemode.extraction.core.commands.bunker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.GameModeExtraction;
import com.rater193.gamemode.extraction.core.api.homehelper;

public class CmdBunkerData {
	
	public static int nextSpawnX = 0;
	
	public static class BunkerHomeData {
		public int x;
		public int y;
		public int z;
		public float gasRemaining = 100f;
		public float gasMax = 100f;
		public String uuid = "UNSET";
		
		public void save() {
			YamlConfiguration config = new YamlConfiguration();
			config.set("x", x);
			config.set("y", y);
			config.set("z", z);
			config.set("gasRemaining", gasRemaining);
			config.set("gasMax", gasMax);
			config.set("uuid", uuid);
			
			File fileData = new File(GameModeExtraction.dataFolder.getPath()+"/"+uuid+".yml");
			
			try {
				config.save(fileData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void load(File f) {
			YamlConfiguration config = new YamlConfiguration();
			try {
				config.load(f);
				x = config.getInt("x");
				y = config.getInt("y");
				z = config.getInt("z");
				gasRemaining = (float) config.getDouble("gasRemaining");
				gasMax = (float) config.getDouble("gasMax");
				uuid = config.getString("uuid");
			} catch (IOException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static HashMap<String, BunkerHomeData> storedBunkers;
	
	public static void LOAD_DATA() {
		
		storedBunkers = new HashMap<String, BunkerHomeData>();
		
		//Here we are loading our "nextSpawnX"
		YamlConfiguration config = new YamlConfiguration();
		File fileData = new File(GameModeExtraction.rootFolder+"/logic.yml");
		try {
			config.load(fileData);
			nextSpawnX = config.getInt("nextSpawnX");
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SAVE_DATA() {
		YamlConfiguration config = new YamlConfiguration();
		config.set("nextSpawnX", nextSpawnX);
		
		File fileData = new File(GameModeExtraction.rootFolder+"/logic.yml");
		
		try {
			config.save(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BunkerHomeData GET_BUNKER(Player player) {
		return storedBunkers.get(player.getPlayerProfile().getUniqueId().toString());
	}
	
	public static boolean BUNKER_EXIST(Player player) {
		return storedBunkers.containsKey(player.getPlayerProfile().getUniqueId().toString());
	}
	
	public static BunkerHomeData GET_OR_CREATE_BUNKER(Player player) {
		if(BUNKER_EXIST(player)) {
			//player.sendMessage("Getting bunker - " + player.getPlayerProfile().getUniqueId().toString());
			return GET_BUNKER(player);
		}else {
			
			String uuid = player.getPlayerProfile().getUniqueId().toString();
			File fileData = new File(GameModeExtraction.dataFolder.getPath()+"/"+uuid+".yml");
			BunkerHomeData homeData;
			if(fileData.exists()) {
				
				player.sendMessage("Player data already saved, loading data");
				
				homeData = new BunkerHomeData();
				homeData.load(fileData);
				
				
			}else {


				player.sendMessage("Creating new bunker");
				//System.out.println("Creating new bunker");
				//player.sendMessage("Creating new bunker at " + nextSpawnX + " 32 50000 - " + player.getPlayerProfile().getUniqueId().toString());
				homeData = new BunkerHomeData();
				homeData.x = nextSpawnX;
				homeData.y = 16;
				homeData.z = nextSpawnX;
				homeData.uuid = uuid;
				
				homehelper.PlaceSchematic("Bunker_Starter_Test.schem", GameModeExtraction.homeWorldName, nextSpawnX, 32, nextSpawnX);
				
				
				nextSpawnX += 800;
				
				homeData.save();
				SAVE_DATA();
			}

			storedBunkers.put(uuid, homeData);
			
			return homeData;
		}
	}
}
