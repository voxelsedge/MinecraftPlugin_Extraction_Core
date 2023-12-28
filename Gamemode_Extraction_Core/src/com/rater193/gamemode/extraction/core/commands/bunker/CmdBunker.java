package com.rater193.gamemode.extraction.core.commands.bunker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
				player.sendMessage("You do not have enough arguments");
				return false;
			}else {
				switch(args[0]) {
				case "test":
					sender.sendMessage("Pasting test schematic?");
					String schematicName = "Test";
					
					Clipboard clipboard;
					File schemFile = new File(GameModeExtraction.schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
					ClipboardFormat format = ClipboardFormats.findByFile(schemFile);
					try {
						ClipboardReader reader = format.getReader(new FileInputStream(schemFile));
					    clipboard = reader.read();
					    World world = BukkitAdapter.adapt(player.getWorld());
					    Location location = player.getLocation();
					    
					    try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
					        Operation operation = new ClipboardHolder(clipboard)
					                .createPaste(editSession)
					                .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
					                // configure here
					                .build();
					        try {
								Operations.complete(operation);
							} catch (WorldEditException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					    
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*
					try {
						ClipboardFormat clipboardFormat = ClipboardFormats.findByFile(GameModeExtraction.schematicBunkerStarterTest);
						Clipboard clipboard = clipboardFormat.getReader(new FileInputStream(GameModeExtraction.schematicBunkerStarterTest)).read();
						
						//Getting the players location
						Location playerLocation = player.getLocation();
						
						//Pasting the schematic
						World world = BukkitAdapter.adapt(player.getWorld());
						EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);
						ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
			            Operation operation = new ClipboardHolder(clipboard)
			                    .createPaste(editSession)
			                    .to(BlockVector3.at(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()))
			                    .ignoreAirBlocks(false)
			                    .build();
			            
			            try {
							Operations.complete(operation);
						} catch (WorldEditException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//clipboardHolder.createPaste(clipboard);
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
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
