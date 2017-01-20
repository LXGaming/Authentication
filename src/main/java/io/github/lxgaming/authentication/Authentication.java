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

package io.github.lxgaming.authentication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.lxgaming.authentication.commands.AcceptCommand;
import io.github.lxgaming.authentication.commands.AuthenticationCommand;
import io.github.lxgaming.authentication.commands.DenyCommand;
import io.github.lxgaming.authentication.commands.InfoCommand;
import io.github.lxgaming.authentication.commands.RulesCommand;
import io.github.lxgaming.authentication.events.PlayerBlockEvent;
import io.github.lxgaming.authentication.events.PlayerChatEvent;
import io.github.lxgaming.authentication.events.PlayerDamageEvent;
import io.github.lxgaming.authentication.events.PlayerInteractEvent;
import io.github.lxgaming.authentication.events.PlayerItemEvent;
import io.github.lxgaming.authentication.events.PlayerJoinEvent;

public class Authentication extends JavaPlugin {
	
	public static Authentication instance;
	public static FileConfiguration config, messages, database;
	public static File configFile, messagesFile, databaseFile;
	private HashSet<Player> AcceptedPlayers;

	@Override
	public void onEnable() {
		instance = this;
		AcceptedPlayers = new HashSet<Player>();
		loadConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerBlockEvent(), this);
		pm.registerEvents(new PlayerChatEvent(), this);
		pm.registerEvents(new PlayerDamageEvent(), this);
		pm.registerEvents(new PlayerInteractEvent(), this);
		pm.registerEvents(new PlayerItemEvent(), this);
		pm.registerEvents(new PlayerJoinEvent(), this);
		
		this.getCommand("authentication").setExecutor(new AuthenticationCommand());
		this.getCommand("auth").setExecutor(new AuthenticationCommand());
		this.getCommand("accept").setExecutor(new AcceptCommand());
		this.getCommand("aaccept").setExecutor(new AcceptCommand());
		this.getCommand("deny").setExecutor(new DenyCommand());
		this.getCommand("adeny").setExecutor(new DenyCommand());
		this.getCommand("rules").setExecutor(new RulesCommand());
		this.getCommand("arules").setExecutor(new RulesCommand());
		this.getCommand("serverrules").setExecutor(new RulesCommand());
		this.getCommand("info").setExecutor(new InfoCommand());
		this.getCommand("ainfo").setExecutor(new InfoCommand());
		this.getCommand("serverinfo").setExecutor(new InfoCommand());
		getLogger().info("Authentication Has Started!");
	}
	
	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("Authentication Has Stopped!");
	}
	
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		configFile = new File(getDataFolder(), "config.yml");
		databaseFile = new File(getDataFolder(), "database.yml");
		messagesFile = new File(getDataFolder(), "messages.yml");
		if (!configFile.exists()) {
			copy(getResource("config.yml"), configFile);
			getLogger().info("Config file created.");
		}
		if (!databaseFile.exists()) {
			copy(getResource("database.yml"), databaseFile);
			getLogger().info("Database file created.");
		}
		if (!messagesFile.exists()) {
			copy(getResource("messages.yml"), messagesFile);
			getLogger().info("Messages file created.");
		}
		config = new YamlConfiguration();
		database = new YamlConfiguration();
		messages = new YamlConfiguration();
		try {
			config.load(configFile);
			database.load(databaseFile);
			messages.load(messagesFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to load files!");
		}
	}
	
	public void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to save files!");
		}
	}
	
	public boolean hasPlayerAccepted(Player player) {
		return AcceptedPlayers.contains(player);
	}
	
	public boolean setPlayerAccepted(Player player) {
		boolean success = AcceptedPlayers.add(player);
		return success;
	}
	
	public boolean setPlayerDeny(Player player) {
		boolean success = AcceptedPlayers.remove(player);
		return success;
	}
}
