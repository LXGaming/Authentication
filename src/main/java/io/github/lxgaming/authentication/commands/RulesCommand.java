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

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RulesCommand implements CommandExecutor {
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rules") || cmd.getName().equalsIgnoreCase("arules") || cmd.getName().equalsIgnoreCase("serverrules") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                List<String> rules = Authentication.messages.getStringList("Authentication.Rules.Page" + args[0]);
                for (String rule : rules) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
                }
                return true;
            } else {
                List<String> rules = Authentication.messages.getStringList("Authentication.Rules.Page1");
                for (String rule : rules) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', rule));
                }
                return true;
            }
        }
        return false;
    }
}
