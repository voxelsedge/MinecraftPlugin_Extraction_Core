package com.rater193.gamemode.extraction.core.commands.extraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CmdExtractionAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		List<String> completions = new ArrayList<>();
		
        // Check if the argument length is 1
		switch(args.length) {
		//1
		case 1:
            // Add possible completions based on the first argument
            completions.add("points");
            completions.add("config");
            completions.add("debug");
            completions.add("save");
            completions.add("reload");
            completions.add("help");
            // Add more options as needed
			break;
			
			//Processing the second argument
		//2
		case 2:
			switch(args[0]) {
			case "points":
	            completions.add("create");
	            completions.add("delete");
	            completions.add("set");
	            completions.add("list");
				break;
				
			case "config":
	            completions.add("set");
				break;
				
			case "debug":
	            completions.add("reset");
				break;
				
			case "save":
				break;
				
			case "reload":
				break;
				
			case "help":
				break;
			}
			break;
		}
		
		return completions;
	}

}
