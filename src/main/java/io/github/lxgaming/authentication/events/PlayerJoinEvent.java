package io.github.lxgaming.authentication.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoinEvent implements Listener {
	
	public int Delay =  Authentication.config.getInt("Authentication.Events.PlayerJoin.MessageDelay");
	
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