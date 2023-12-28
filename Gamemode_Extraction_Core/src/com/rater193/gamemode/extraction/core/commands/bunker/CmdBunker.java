package com.rater193.gamemode.extraction.core.commands.bunker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.api.GameWorlds;

public class CmdBunker implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			if(args.length<0) {
				CmdBunkerData.BunkerHomeData homeData = CmdBunkerData.GET_OR_CREATE_BUNKER(player);
				player.teleport(new Location(GameWorlds.worldHome, homeData.x, homeData.y, homeData.z));
				return false;
			}else {
				switch(args[0]) {
				case "debug":
					if(args.length>=2) {
						switch(args[1]) {
						case "teleport_to_world":
							if(args.length>=3) {
								Location l = new Location(Bukkit.getWorld(args[2]), 0, 0, 0);
								player.teleport(l);
							}else {
								player.sendMessage("Please specify a world to goto");
							}
							break;
						}
					}else {
						player.sendMessage("Debug command coming soon.");
						player.sendMessage("Your world name is: " + player.getWorld().getName());
					}
					break;
					
				case "home":
					player.sendMessage("Setting up bunker?");
					CmdBunkerData.BunkerHomeData homeData = CmdBunkerData.GET_OR_CREATE_BUNKER(player);
					player.sendMessage("Finished?");
					player.teleport(new Location(GameWorlds.worldHome, homeData.x+0.5d+8d, homeData.y+2d, homeData.z+0.5d+8d));
					break;
				}
			}
			
			return true;
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
