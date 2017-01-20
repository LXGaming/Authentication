/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
