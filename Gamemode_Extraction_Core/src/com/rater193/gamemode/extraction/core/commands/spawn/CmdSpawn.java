package com.rater193.gamemode.extraction.core.commands.spawn;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rater193.gamemode.extraction.core.api.GameWorlds;

public class CmdSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			//Here we execute code if the player was the one whom
			//excecuted the command
			
			Player player = (Player) sender;
			
			if(player.getGameMode()!=GameMode.CREATIVE) {
				if(player.getWorld().getName().equals(GameWorlds.worldExtractionName)) {
					player.sendMessage("You can not teleport to spawn, you must use /kill to commit suicide to teleport out.");
				}
			}
			
			player.teleport(Bukkit.getWorld("world").getSpawnLocation());
			
			return true;
		}else {
			System.out.println("The command, " + label + ", command can only be executed by a player");
		}
		return false;
	}

}
