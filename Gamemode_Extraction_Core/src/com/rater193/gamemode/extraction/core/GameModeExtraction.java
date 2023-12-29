package com.rater193.gamemode.extraction.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.rater193.gamemode.extraction.core.commands.extraction.*;
import com.rater193.gamemode.extraction.core.commands.spawn.CmdSpawn;
import com.rater193.gamemode.extraction.core.commands.spawn.CmdSpawnAutoComplete;
import com.rater193.gamemode.extraction.core.commands.spawngroups.CmdSpawngroups;
import com.rater193.gamemode.extraction.core.commands.spawngroups.CmdSpawngroupsAutoComplete;
import com.rater193.gamemode.extraction.core.events.EventManager;
import com.rater193.gamemode.extraction.core.api.homehelper;
import com.rater193.gamemode.extraction.core.commands.bunker.*;

public class GameModeExtraction extends JavaPlugin {
	
	public static String homeWorldName;
	public static World homeWorld;
	public static File configFile;
	public static File schematicsFolder;//This is where the server stores the default schematics
	public static File dataFolder;//This is where players store their bunker data
	public static File schematicBunkerStarterTest;
	public static File rootFolder;

	//This method allows for you to register sub commands to the same cmd instances to keep memory down for the server
	public void RegisterCommand(List<String> commands, CommandExecutor cmdExecutor, TabCompleter tabCompleter) {
		for(String cmdName : commands) {
			RegisterCommand(cmdName, cmdExecutor, tabCompleter);
		}
	}
	
	//You can also register just a single command as well, this one is used for clean code
	public void RegisterCommand(String command, CommandExecutor cmdExecutor, TabCompleter tabCompleter) {
		PluginCommand cmd = this.getCommand(command);
		cmd.setExecutor(cmdExecutor);
		cmd.setTabCompleter(tabCompleter);
	}

	//////////////////////////////////////////////////////////////////////////
	//                         COMMAND REGISTRATION                         //
	//////////////////////////////////////////////////////////////////////////
	
	public void RegisterCommand_Extraction() {
		//The commands with the alias
		ArrayList<String> extractionCommands = new ArrayList<String>();
		extractionCommands.add("extraction");
		extractionCommands.add("ext");
		
		RegisterCommand(extractionCommands, new CmdExtraction(), new CmdExtractionAutoComplete());
	}
	
	public void RegisterCommand_Bunker() {
		RegisterCommand("bunker", new CmdBunker(), new CmdBunkerAutoComplete());
	}
	
	public void RegisterCommand_Spawn() {
		RegisterCommand("spawn", new CmdSpawn(), new CmdSpawnAutoComplete());
	}
	
	public void RegisterCommand_Spawngroups() {
		ArrayList<String> extractionCommands = new ArrayList<String>();
		extractionCommands.add("spawngroups");
		extractionCommands.add("sg");
		
		RegisterCommand(extractionCommands, new CmdSpawngroups(), new CmdSpawngroupsAutoComplete());
	}
	
	//Saves a copy of the default config
	public void saveDefaultConfig() {
		try (InputStream resourceStream = getResource("config.yml")) {
            Files.copy(resourceStream, configFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveResourceFile(String resourceLocation, String saveLocation) {
		try (InputStream resourceStream = getResource(resourceLocation)) {
            Files.copy(resourceStream, new File(saveLocation).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void LoadConfig() {
		//Making sure out directory exists
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        
        rootFolder = getDataFolder();
        
        //Here we are setting up some core folders and references
        schematicsFolder = new File(getDataFolder(), "schematics");
        if (!schematicsFolder.exists()) {
        	schematicsFolder.mkdirs();
        }
        
        dataFolder = new File(getDataFolder(), "dataFolder");
        if (!dataFolder.exists()) {
        	dataFolder.mkdirs();
        }
        
        configFile = new File(getDataFolder() + "/config.yml");
        if(!configFile.exists()) {
        	saveDefaultConfig();
        }
        
        schematicBunkerStarterTest = new File(schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
        if (!schematicBunkerStarterTest.exists()) {
        	saveResourceFile("Bunker_Starter_Test.schem", schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
        	schematicBunkerStarterTest = new File(schematicsFolder.getPath() + "/Bunker_Starter_Test.schem");
        }
        
        LoadData();
        
        //Now to load the data
	}

	@Override
	public void onEnable() {
		
		Bukkit.getPluginManager().registerEvents(new EventManager(), this);
		homehelper.INIT();
		
		homeWorldName = "home_world";
		homeWorld = Bukkit.getWorld(homeWorldName);
		
		//Config
		LoadConfig();
		
		//Command alias
		RegisterCommand_Extraction();
		RegisterCommand_Bunker();
		RegisterCommand_Spawn();
		RegisterCommand_Spawngroups();
	}

	@Override
	public void onDisable() {
		
	}

	public static void LoadData() {
        CmdExtractionData.LOAD_DATA();
        CmdBunkerData.LOAD_DATA();
		
	}

	public static void SaveData() {
        CmdExtractionData.SAVE_DATA();
        CmdBunkerData.SAVE_DATA();
		
	}
	
}
