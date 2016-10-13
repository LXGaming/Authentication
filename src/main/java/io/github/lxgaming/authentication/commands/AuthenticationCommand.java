package io.github.lxgaming.authentication.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuthenticationCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("authentication") || cmd.getName().equalsIgnoreCase("auth") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Authentication" + ChatColor.GOLD + " ===== ");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Version 0.2.5");
			player.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Commands" + ChatColor.GOLD + " ===== ");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Authentication / Auth");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Accept / AAccept");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Deny / ADeny");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Rules / ARules /ServerRules");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Info / AInfo /ServerInfo");
			return true;
		}
		return false;
	}

}