package com.rater193.gamemode.extraction.core.commands.extraction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CmdExtraction implements CommandExecutor {
	
	public static void LOAD_DATA() {
		
	}
	
	public static void SAVE_DATA() {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			
			return true;
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
