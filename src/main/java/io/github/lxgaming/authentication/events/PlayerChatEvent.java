package io.github.lxgaming.authentication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerChatEvent implements Listener {
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent APC) {
		Player APCP = APC.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.Chat") == true) {
			if (!Authentication.instance.hasPlayerAccepted(APCP)) {
				APC.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					APCP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					APCP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						APCP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Chat")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onCommandProcess(PlayerCommandPreprocessEvent PCP) {
		Player PCPP = PCP.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.Command") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PCPP)) {
				for (String Command : PCP.getMessage().toLowerCase().split(" ")) {
					if (Authentication.config.getStringList("Authentication.CommandWhitelist").contains(Command)) {
						return;
					} else {
						PCP.setCancelled(true);
						if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
							PCPP.performCommand("serverrules");
						}
						if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
							PCPP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
						} else {
							if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
								PCPP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Command")));
							}
						}
					}
				}
			}
		}
	}
}