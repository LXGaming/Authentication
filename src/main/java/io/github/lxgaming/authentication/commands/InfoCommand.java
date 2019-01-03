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

public class InfoCommand implements CommandExecutor {
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("info") || cmd.getName().equalsIgnoreCase("ainfo") || cmd.getName().equalsIgnoreCase("serverinfo") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                List<String> infos = Authentication.messages.getStringList("Authentication.Info.Page" + args[0]);
                for (String info : infos) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
                }
                
                return true;
            } else {
                List<String> infos = Authentication.messages.getStringList("Authentication.Info.Page1");
                for (String info : infos) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
                }
                
                return true;
            }
        }
        
        return false;
    }
}