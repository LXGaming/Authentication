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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class PlayerBlockEvent implements Listener {
    
    @EventHandler
    public void onPlayerBlockDamage(BlockDamageEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    player.performCommand("serverrules");
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    player.performCommand("serverrules");
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.BlockPlace")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    player.performCommand("serverrules");
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockPlace")));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerEmptyBucket(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.BlockPlace")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    player.performCommand("serverrules");
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockPlace")));
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerFillBucket(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak")) {
            if (!Authentication.instance.hasPlayerAccepted(player)) {
                event.setCancelled(true);
                if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                    player.performCommand("serverrules");
                }
                
                if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
                    }
                }
            }
        }
    }
}