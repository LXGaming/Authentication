package io.github.lxgaming.authentication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerBlockEvent implements Listener {
	
	@EventHandler
	public void onPlayerBlockDamage(BlockDamageEvent BD) {
		Player BDP = BD.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak") == true) {
			if (!Authentication.instance.hasPlayerAccepted(BDP)) {
				BD.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					BDP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					BDP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						BDP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent BB) {
		Player BBP = BB.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak") == true) {
			if (!Authentication.instance.hasPlayerAccepted(BBP)) {
				BB.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					BBP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					BBP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						BBP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent BP) {
		Player BPP = BP.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.BlockPlace") == true) {
			if (!Authentication.instance.hasPlayerAccepted(BPP)) {
				BP.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					BPP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					BPP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						BPP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockPlace")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerEmptyBucket(PlayerBucketEmptyEvent PBE) {
		Player PBEP = PBE.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.BlockPlace") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PBEP)) {
				PBE.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PBEP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PBEP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PBEP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockPlace")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerFillBucket(PlayerBucketFillEvent PBF) {
		Player PBFP = PBF.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.BlockBreak") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PBFP)) {
				PBF.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PBFP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PBFP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PBFP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.BlockBreak")));
					}
				}
			}
		}
	}
}