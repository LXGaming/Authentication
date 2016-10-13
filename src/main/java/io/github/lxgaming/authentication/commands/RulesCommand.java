package io.github.lxgaming.authentication.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class RulesCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("rules") || cmd.getName().equalsIgnoreCase("arules") || cmd.getName().equalsIgnoreCase("serverrules") && sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				List<String> rules = Authentication.messages.getStringList("Authentication.Rules.Page" + args[0]);
				for(String rule : rules) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
				}
				return true;
			} else {
				List<String> rules = Authentication.messages.getStringList("Authentication.Rules.Page1");
				for(String rule : rules) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
				}
				return true;
			}
		}
		return false;
	}
}