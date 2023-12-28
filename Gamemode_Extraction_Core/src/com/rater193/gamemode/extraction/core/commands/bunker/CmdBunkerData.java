package com.rater193.gamemode.extraction.core.commands.bunker;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.api.homehelper;

public class CmdBunkerData {
	
	public static int nextSpawnX = 0;
	
	public static class BunkerHomeData {
		public int x;
		public int y;
		public int z;
		public float gasRemaining = 100f;
		public float gasMax = 100f;
		
	}
	
	public static HashMap<String, BunkerHomeData> storedBunkers;
	
	public static void LOAD_DATA() {
		storedBunkers = new HashMap<String, BunkerHomeData>();
	}
	
	public static void SAVE_DATA() {
		
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
			//System.out.println("Creating new bunker");
			//player.sendMessage("Creating new bunker at " + nextSpawnX + " 32 50000 - " + player.getPlayerProfile().getUniqueId().toString());
			BunkerHomeData homeData = new BunkerHomeData();
			homeData.x = nextSpawnX + 8;
			homeData.y = 16 + 4;
			homeData.z = 50000 + 8;
			
			homehelper.PlaceSchematic("Bunker_Starter_Test.schem", "world", nextSpawnX, 32, 50000);
			
			
			nextSpawnX += 48;
			
			storedBunkers.put(player.getPlayerProfile().getUniqueId().toString(), homeData);
			
			return homeData;
		}
	}
}
