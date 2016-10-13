package io.github.lxgaming.authentication.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class AcceptCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("accept") || cmd.getName().equalsIgnoreCase("aaccept")&& sender instanceof Player) {
			Player player = (Player) sender;
			String name = player.getPlayer().getName();
			
			if (!Authentication.instance.hasPlayerAccepted(player)) {
				Authentication.instance.setPlayerAccepted(player);
				List<String> list = Authentication.database.getStringList("Authentication.Database");
				list.add(name);
				Authentication.database.set("Authentication.Database", list);
				try {
					Authentication.database.save(Authentication.databaseFile);
					Authentication.instance.getLogger().info(name + " Was added to the Database");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				Authentication.instance.reloadConfig();
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands.AcceptCommand")));
				return true;
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands.AlreadyAccepted")));
					return true;
				}
		} return false;
	}
}