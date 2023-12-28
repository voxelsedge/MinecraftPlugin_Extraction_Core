package com.rater193.gamemode.extraction.core.commands.bunker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.GameModeExtraction;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;

public class CmdBunker implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			if(args.length<0) {
				CmdBunkerData.BunkerHomeData homeData = CmdBunkerData.GET_OR_CREATE_BUNKER(player);
				player.teleport(new Location(Bukkit.getWorld("world"), homeData.x, homeData.y, homeData.z));
				return false;
			}else {
				switch(args[0]) {
				case "test":
					player.sendMessage("Setting up bunker?");
					CmdBunkerData.BunkerHomeData homeData = CmdBunkerData.GET_OR_CREATE_BUNKER(player);
					player.sendMessage("Finished?");
					player.teleport(new Location(Bukkit.getWorld("world"), homeData.x, homeData.y, homeData.z));
					
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
