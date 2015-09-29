package com.hotmail.langbach.nicholas.norskekommandoer;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static HashMap<String, String> tpa = new HashMap<String, String>();

	public static Plugin plugin;
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public void onEnable() {
		plugin = this;
		settings.setup(this);
		
		
		this.saveDefaultConfig();



		registerCommands();
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new StabSamtale(), this);
		pm.registerEvents(new Pvp(), this);
	}

	
	public void onDisable() {
		plugin = null;
		
	}
	
	
	public void registerCommands() {
		getCommand("settspawn").setExecutor(new SettSpawn());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("fly").setExecutor(new Fly());
		getCommand("stuck").setExecutor(new Stuck());
		getCommand("location").setExecutor(new Loc());
		getCommand("openinv").setExecutor(new ÅpneInv());
		getCommand("ping").setExecutor(new Ping());
		getCommand("nk").setExecutor(new NorskeKommandoer());
		getCommand("spillerinfo").setExecutor(new SpillerInfo());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("stabsamtale").setExecutor(new StabSamtale());
		getCommand("avvis").setExecutor(new Avvis());
		getCommand("godta").setExecutor(new Godta());
		getCommand("kick").setExecutor(new Kick());
		getCommand("pvp").setExecutor(new Pvp());
		getCommand("stabhjelp").setExecutor(new StabHjelp());
		getCommand("rens").setExecutor(new Rens());
	}

}
