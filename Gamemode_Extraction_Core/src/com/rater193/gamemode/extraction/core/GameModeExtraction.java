package com.rater193.gamemode.extraction.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.Nullable;

import com.rater193.gamemode.extraction.core.commands.extraction.*;
import com.rater193.gamemode.extraction.core.commands.bunker.*;

public class GameModeExtraction extends JavaPlugin {

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

	@Override
	public void onEnable() {
		
		//Command alias
		RegisterCommand_Extraction();
		RegisterCommand_Bunker();
	}

	@Override
	public void onDisable() {
		
	}
	
}
