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
