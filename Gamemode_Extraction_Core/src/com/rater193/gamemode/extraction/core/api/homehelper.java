package com.rater193.gamemode.extraction.core.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
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
	
	public static void PlaceSchematic(String schematicName, String worldName, int x, int y, int z) {

		Clipboard clipboard;
		//File schemFile = new File(GameModeExtraction.schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
		File schemFile = new File(GameModeExtraction.schematicsFolder.getPath() + "/"+schematicName);
		ClipboardFormat format = ClipboardFormats.findByFile(schemFile);
		try {
			ClipboardReader reader = format.getReader(new FileInputStream(schemFile));
		    clipboard = reader.read();
		    World world = BukkitAdapter.adapt(Bukkit.getWorld(worldName));
		    Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
		    
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
	}
}
