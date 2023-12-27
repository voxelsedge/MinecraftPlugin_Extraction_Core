package com.rater193.gamemode.extraction.core;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import com.rater193.gamemode.extraction.core.commands.extraction.CmdExtraction;
import com.rater193.gamemode.extraction.core.commands.extraction.CmdExtractionAutoComplete;

public class GameModeExtraction extends JavaPlugin {

	@Override
	public void onEnable() {
		
		//TPA is now working
		PluginCommand cmdExtraction = this.getCommand("extraction");
		PluginCommand cmdExt = this.getCommand("ext");
		
		cmdExtraction.setTabCompleter(new CmdExtractionAutoComplete());
		cmdExt.setTabCompleter(new CmdExtractionAutoComplete());
		
		cmdExtraction.setExecutor(new CmdExtraction());
		cmdExt.setExecutor(new CmdExtraction());
	}

	@Override
	public void onDisable() {
		
	}
	
}
