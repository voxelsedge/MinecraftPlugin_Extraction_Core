package com.rater193.gamemode.extraction.core.commands.extraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.GameModeExtraction;

public class CmdExtraction implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			
			if(args.length>0) {
				switch(args[0]) {
				case "save":
					GameModeExtraction.SaveData();
					break;
					
				case "load":
					GameModeExtraction.LoadData();
					break;
					
					default:
						player.sendMessage("Subcommand " + args[0] + " not found");
						return false;
				}
			}else {
				player.sendMessage("Not enough arguments supplied");
				return false;
			}
			
			return true;
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
