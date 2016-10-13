package io.github.lxgaming.authentication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerItemEvent implements Listener {
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent PDI) {
		Player PDIP = PDI.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.ItemDrop") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PDIP)) {
				PDI.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PDIP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PDIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PDIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.ItemDrop")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent PPI) {
		Player PPIP = PPI.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.ItemPickup") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PPIP)) {
				PPI.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PPIP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PPIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PPIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.ItemPickup")));
					}
				}
			}
		}
	}
}