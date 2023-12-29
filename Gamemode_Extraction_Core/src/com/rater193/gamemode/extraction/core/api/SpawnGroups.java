package com.rater193.gamemode.extraction.core.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;

public class SpawnGroups {
	public static HashMap<String, ArrayList<Location>> groups;
	
	
	public static void INIT() {
		groups = new HashMap<String, ArrayList<Location>>();
	}
	
	
	public static ArrayList<Location> GET_GROUP(String groupName) {
		return groups.get(groupName);
	}
	
	
	public static boolean GROUP_EXISTS(String groupName) {
		return groups.containsKey(groupName);
	}
	
	
	public static ArrayList<Location> GET_OR_CREATE_GROUP(String groupName) {
		if(GROUP_EXISTS(groupName)) return GET_GROUP(groupName);
		ArrayList<Location> newGroupList = new ArrayList<Location>();
		groups.put(groupName, newGroupList);
		return newGroupList;
	}
	
    private static <T> T GET_RANDOM_ITEM(ArrayList<T> list) {
        // Check if the list is not empty
        if (list != null && !list.isEmpty()) {
            // Generate a random index within the bounds of the list
            Random random = new Random();
            int randomIndex = random.nextInt(list.size());

            // Return the item at the random index
            return list.get(randomIndex);
        } else {
            // Return null or handle the case where the list is empty
            return null;
        }
    }
	
	public static Location GET_RANDOM_LOCATION(String groupName) {
		ArrayList<Location> locations = GET_GROUP(groupName);
		if(locations!=null) {
			return GET_RANDOM_ITEM(locations);
		}
		return null;
	}
}
