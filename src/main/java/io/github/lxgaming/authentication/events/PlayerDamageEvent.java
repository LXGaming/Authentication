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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener {
    
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!Authentication.config.getBoolean("Authentication.Events.PlayerAttack")) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (!Authentication.instance.hasPlayerAccepted(player)) {
                    event.setCancelled(true);
                    if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                        player.performCommand("serverrules");
                    }
                    
                    if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                    } else {
                        if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Attack")));
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!Authentication.config.getBoolean("Authentication.Events.PlayerDamage")) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (!Authentication.instance.hasPlayerAccepted(player)) {
                    if (event.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)) {
                        player.setFoodLevel(20);
                    }
                    
                    event.setCancelled(true);
                    if (Authentication.config.getBoolean("Authentication.Events.RulesMessage")) {
                        player.performCommand("serverrules");
                    }
                    
                    if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
                    } else {
                        if (Authentication.config.getBoolean("Authentication.Events.EventMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Damage")));
                        }
                    }
                }
            }
        }
    }
}