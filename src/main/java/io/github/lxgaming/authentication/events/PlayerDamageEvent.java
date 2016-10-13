package io.github.lxgaming.authentication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerDamageEvent implements Listener {
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent PA) {
		if (!Authentication.config.getBoolean("Authentication.Events.PlayerAttack") == true) {
			if (PA.getDamager() instanceof Player) {
				Player PAP = (Player)PA.getDamager();
				if (!Authentication.instance.hasPlayerAccepted(PAP)) {
					PA.setCancelled(true);
					if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
						PAP.performCommand("serverrules");
					}
					if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
						PAP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
					} else {
						if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
							PAP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Attack")));
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent ED) {
		if (!Authentication.config.getBoolean("Authentication.Events.PlayerDamage") == true) {
			if (ED.getEntity() instanceof Player) {
				Player EDP = (Player)ED.getEntity();
				if (!Authentication.instance.hasPlayerAccepted(EDP)) {
					if(ED.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)) {
						EDP.setFoodLevel(20);
					}
					ED.setCancelled(true);
					if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
						EDP.performCommand("serverrules");
					}
					if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
						EDP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
					} else {
						if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
							EDP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Damage")));
						}
					}	
				}
			}
		}
	}
}