package com.rater193.gamemode.extraction.core.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Location;

import com.rater193.gamemode.extraction.core.GameModeExtraction;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
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

public class homehelper {
	
	public static World homeWorld;
	
	public static void INIT() {
		GameWorlds.INIT(); // This initalizes the home world
		SpawnGroups.INIT();
	}
	
	public static void PlaceSchematic(String schematicName, String worldName, int x, int y, int z) {
		// TODO: Make the schematics only load external files once.
		Clipboard clipboard;
		//File schemFile = new File(GameModeExtraction.schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
		File schemFile = new File(GameModeExtraction.schematicsFolder.getPath() + "/"+schematicName);
		ClipboardFormat format = ClipboardFormats.findByFile(schemFile);
		try {
			ClipboardReader reader = format.getReader(new FileInputStream(schemFile));
		    clipboard = reader.read();
		    World world = BukkitAdapter.adapt(GameWorlds.worldHome);
		    Location location = new Location(GameWorlds.worldHome, x, y, z);
		    
		    try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
		        Operation operation = new ClipboardHolder(clipboard)
		                .createPaste(editSession)
		                .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
		                // configure here
		                .build();
		        try {
					Operations.complete(operation);
				} catch (WorldEditException e) {
					e.printStackTrace();
				}
		    }
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
