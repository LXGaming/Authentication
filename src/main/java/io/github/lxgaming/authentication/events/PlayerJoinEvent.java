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

public class PlayerJoinEvent implements Listener {
    
    public int Delay = Authentication.config.getInt("Authentication.Events.PlayerJoin.MessageDelay");
    
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent PJ) {
        Player PJP = PJ.getPlayer();
        String PJPName = PJ.getPlayer().getName();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Authentication.instance, new Runnable() {
            public void run() {
                if (PJP.hasPermission("Authentication.Bypass") || (PJP.isOp()) || Authentication.database.getStringList("Authentication.Database").contains(PJPName)) {
                    Authentication.instance.setPlayerAccepted(PJP);
                    if (Authentication.config.getBoolean("Authentication.Events.PlayerJoin.RulesAccepted") == true) {
                        PJP.performCommand("serverrules");
                    }
                    if (Authentication.config.getBoolean("Authentication.Events.PlayerJoin.WelcomeAccepted") == true) {
                        PJP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.WelcomeAccepted")));
                    }
                } else {
                    if (Authentication.config.getBoolean("Authentication.Events.PlayerJoin.RulesNotAccepted") == true) {
                        PJP.performCommand("serverrules");
                    }
                    if (Authentication.config.getBoolean("Authentication.Events.PlayerJoin.WelcomeNotAccepted") == true) {
                        PJP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.WelcomeNotAccepted")));
                    }
                }
            }
        }, Delay);
    }
}
