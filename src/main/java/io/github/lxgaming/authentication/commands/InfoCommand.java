package io.github.lxgaming.authentication.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class InfoCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("info") || cmd.getName().equalsIgnoreCase("ainfo") || cmd.getName().equalsIgnoreCase("serverinfo") && sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				List<String> infos = Authentication.messages.getStringList("Authentication.Info.Page" + args[0]);
				for(String info : infos) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
				}
				return true;
			} else {
				List<String> infos = Authentication.messages.getStringList("Authentication.Info.Page1");
				for(String info : infos) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
				}
				return true;
			}
		}
		return false;
	}
}