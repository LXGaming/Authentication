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

package io.github.lxgaming.authentication.events;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerChatEvent implements Listener {
    
    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.Chat")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    Bukkit.getScheduler().runTask(Authentication.instance, () -> player.performCommand("serverrules"));
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Chat")));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onCommandProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.Command")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                for (String Command : event.getMessage().toLowerCase().split(" ")) {
                    if (Authentication.config.getStringList("Authentication.CommandWhitelist").contains(Command)) {
                        return;
                    } else {
                        event.setCancelled(true);
                        if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                            player.performCommand("serverrules");
                        }
                        
                        if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                        } else {
                            if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Command")));
                            }
                        }
                    }
                }
            }
        }
    }
}