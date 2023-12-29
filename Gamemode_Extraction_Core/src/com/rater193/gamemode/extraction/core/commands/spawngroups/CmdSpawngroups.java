package com.rater193.gamemode.extraction.core.commands.spawngroups;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.api.SpawnGroups;

public class CmdSpawngroups implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			switch(args.length) {
			case 0:
				player.sendMessage("not enough arguments provided");
				return false;
				
			case 1:
				
				break;
				
			case 2:
				
				break;
			}
			
			if(args.length>=1) {
				switch(args[0]) {
				case "teleport":
					if(args.length>=2) {
						switch(args[1]) {
						case "random":
							player.sendMessage("Teleporting to random spawn group");
							break;
							
						default:
							if(SpawnGroups.GROUP_EXISTS(args[1])) {
								player.sendMessage("Teleporting to spawn group: " + args[1]);
								Location l = SpawnGroups.GET_RANDOM_LOCATION(args[1]);
								player.teleport(l);
							}else {
								player.sendMessage("Spawn group does not exist: " + args[1]);
							}
							break;
						}
					} else {
						player.sendMessage("Not enough arguments");
						player.sendMessage("/sg teleport <location>");
						return false;
					}
					return true;
					
				case "list":
					String msg = "Groups: ";
					for(String key : SpawnGroups.groups.keySet()) {
						msg = msg + key + ", ";
					}
					player.sendMessage(msg);
					return true;
					
				case "add":
					if(args.length>=2) {
						String groupName = args[1];
						if(SpawnGroups.GROUP_EXISTS(groupName)) {
							player.sendMessage("Group already exists: " + groupName);
						}else {
							player.sendMessage("Creating group: " + groupName);
							SpawnGroups.GET_OR_CREATE_GROUP(groupName);
						}
						
						return true;
					}else {
						player.sendMessage("Not enough arguments provided!");
						player.sendMessage("/sg add <add>");
						return false;
					}
					
				case "remove":
					if(args.length>=2) {
						String groupName = args[1];
						if(!SpawnGroups.GROUP_EXISTS(groupName)) {
							player.sendMessage("Group does not exist: " + groupName);
						}else {
							player.sendMessage("Removing group: " + groupName);
							SpawnGroups.REMOVE_GROUP(groupName);
						}
						
						return true;
					}else {
						player.sendMessage("Not enough arguments provided!");
						player.sendMessage("/sg remove <add>");
						return false;
					}
					
				case "group":
					if(args.length>=2) {
						switch(args[1]) {
						case "addSpawnPoint":
							if(args.length>=3) {
								String spawnPointGroup = args[2];
								List<Location> spawnPoints = SpawnGroups.GET_GROUP(spawnPointGroup);
								if(spawnPoints!=null) {
									spawnPoints.add(new Location(
											player.getLocation().getWorld(),
											player.getLocation().getX(),
											player.getLocation().getY(),
											player.getLocation().getZ()
											));
								}
								player.sendMessage("Added player position to list: " + args[2]);
								return true;
							}
							
							player.sendMessage("/sg group addSpawnPoint <groupName>");
							player.sendMessage("Not enough arguments!" + args[2]);
							return false;
							
						case "removeSpawnPoint":
							if(args.length>=4) {
								String spawnPointGroup = args[2];
								String spawnIndex = args[3];
								List<Location> spawnPoints = SpawnGroups.GET_GROUP(spawnPointGroup);
								if(spawnPoints!=null) {
									spawnPoints.remove(Integer.parseInt(spawnIndex));
								}
								player.sendMessage("Removed index from list: " + args[2]);
								return true;
							}

							player.sendMessage("Not enough arguments provided!");
							player.sendMessage("/sg group removeSpawnPoint <groupName> <listIndex>");
							return false;
							
						case "listSpawnPoints":
							if(args.length>=3) {
								String spawnPointGroup = args[2];
								List<Location> spawnPoints = SpawnGroups.GET_GROUP(spawnPointGroup);
								
								player.sendMessage("Added player position to list: " + args[2]);
								int index = 0;
								for(Location l : spawnPoints) {
									player.sendMessage("Index: " + index + ", world:"+l.getWorld().getName()+", x:"+l.getX()+", y:"+l.getY()+", z:"+l.getZ());
									index++;
								}
								return true;
							}
							
							player.sendMessage("/sg group listSpawnPoints <groupName>");
							player.sendMessage("Not enough arguments!" + args[2]);
							return false;
							
						default:
							player.sendMessage("Unhandled subcommand: " + args[1]);
							return false;
						}
					}else {
						player.sendMessage("Not enough arguments provided!");
						player.sendMessage("/sg group <addSpawnPoint|removeSpawnPoint|listSpawnPoints>");
						return false;
					}
					
				default:
					player.sendMessage("Unhandled subcommand: " + args[0]);
					return false;
				}
			}else {
				player.sendMessage("Not enough arguments");
				return false;
			}
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
