package com.rater193.gamemode.extraction.core.commands.spawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.rater193.gamemode.extraction.core.api.GameWorlds;
import com.rater193.gamemode.extraction.core.api.SpawnGroups;

public class CmdSpawnAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		switch(args.length) {
		case 1:
			
			//This adds spawn support for letting players teleport to extraction spawn groups
			list.add("spawn");
			for(String key : SpawnGroups.groups.keySet()) {
				list.add(key);
			}
			break;
		}
		return list;
	}

}
