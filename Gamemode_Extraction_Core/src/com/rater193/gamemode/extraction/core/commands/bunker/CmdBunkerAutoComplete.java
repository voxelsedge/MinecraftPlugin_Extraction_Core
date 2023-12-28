package com.rater193.gamemode.extraction.core.commands.bunker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.rater193.gamemode.extraction.core.api.GameWorlds;

public class CmdBunkerAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		switch(args.length) {
		case 1:
			list.add("debug");
			list.add("home");
			break;
			
		case 2:
			switch(args[0]) {
			case "debug":
				list.add("remove_player");
				list.add("reset_player");
				list.add("reset_bunker");
				list.add("list_bunkers");
				list.add("list_players");
				list.add("teleport_to_world");
				list.add("test");
				break;
				
			case "home":
				break;
			}
			break;
			
		case 3:
			switch(args[0]) {
			case "debug":
				switch(args[1]) {
				case "remove_player":
					break;
				case "reset_player":
					break;
				case "reset_bunker":
					break;
				case "list_bunkers":
					break;
				case "list_players":
					break;
				case "teleport_to_world":
					list.add("world");
					list.add("world_nether");
					list.add("world_the_end");
					list.add(GameWorlds.worldHomeName);
					list.add(GameWorlds.worldExtractionName);
					break;
				case "test":
					break;
				}
				break;
				
			case "home":
				break;
			}
			break;
		}
		return list;
	}

}
