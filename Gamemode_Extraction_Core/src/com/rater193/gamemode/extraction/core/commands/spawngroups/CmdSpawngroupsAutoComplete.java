package com.rater193.gamemode.extraction.core.commands.spawngroups;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.rater193.gamemode.extraction.core.api.GameWorlds;

public class CmdSpawngroupsAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		switch(args.length) {
		case 1:
			list.add("list");
			list.add("add");
			list.add("remove");
			list.add("group");
			break;
			
		case 2:
			switch(args[0]) {
			case "add":
				list.add("<groupname>");
				break;

			case "remove":
				list.add("<groupname>");
				break;

			case "group":
				list.add("addSpawnPoint");
				list.add("removeSpawnPoint");
				list.add("listSpawnPoints");
				break;
			}
			break;
			
		case 3:
			switch(args[0]) {

			case "group":
				switch(args[1]) {
				case "addSpawnPoint":
					break;
					
				case "removeSpawnPoint":
					list.add("\"<spawn list number>\"");
					break;
					
				case "listSpawnPoints":
					break;
				}
				break;
			}
			break;
		}
		return list;
	}

}
