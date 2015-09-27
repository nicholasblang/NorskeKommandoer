package com.hotmail.langbach.nicholas.norskekommandoer;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.Fly;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.Loc;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.NorskeKommandoer;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.Ping;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.SettSpawn;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.Spawn;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.SpillerInfo;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.Stuck;
import com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer.ÅpneInv;

public class Main extends JavaPlugin {
	HashMap<String, String> tpa = new HashMap<String, String>();
	public static Plugin plugin;

	public void onEnable() {

		this.saveDefaultConfig();

		plugin = this;

		registerCommands();
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
		getCommand("norskekommandoer").setExecutor(new NorskeKommandoer());
		getCommand("spillerinfo").setExecutor(new SpillerInfo());
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if (plugin.getConfig().getBoolean("tpa") == false) {
				sender.sendMessage(ChatColor.RED
						+ "Error: Kommandoen /tpa er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
				return true;
			} else if (!(sender.hasPermission("norskekommandoer.tpa"))) {
				sender.sendMessage(
						ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
				return true;
			} else

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre denne kommandoen!");
				return true;
			}
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Error: Husk å skriv navn! /tpa <spiller>");

				return true;
			} else {
				if (!(Bukkit.getServer().getPlayer(args[0]) == null)) {
					p.sendMessage(ChatColor.RED + "Kunne ikke finne spilleren " + ChatColor.GOLD + args[0]);
					return true;
				}

				Player t = Bukkit.getServer().getPlayer(args[0]);
				Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						if (tpa.containsKey(t.getName())) {
							tpa.remove(t.getName(), p.getName());
							p.sendMessage(ChatColor.RED + "TPA forspørselen fra " + ChatColor.BLUE + t.getName()
									+ ChatColor.RED + " utløpte!");
							t.sendMessage(ChatColor.RED + "Din TPA forspørsel utløpte!");
						}

					}

				}, this.getConfig().getInt("Sekunder") * 20);
				tpa.put(p.getName(), t.getName());
				t.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.GREEN
						+ " vil teleportere til deg! Skriv /godta for å godta eller skriv /avvis for å avvise.");
				p.sendMessage(ChatColor.GREEN + "Du sendte en tpa forespørsel til " + ChatColor.BLUE + t.getName()
						+ ChatColor.GREEN + "!");
				return true;

			}
		}
		if (cmd.getName().equalsIgnoreCase("godta")) {
			if (plugin.getConfig().getBoolean("tpa") == false) {
				sender.sendMessage(ChatColor.RED
						+ "Error: Kommandoen /godta er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
				return true;
			} else if (!(sender.hasPermission("norskekommandoer.tpa"))) {
				sender.sendMessage(
						ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
				return true;
			} else

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre denne kommandoen!");
				return true;
			}
			Player p = (Player) sender;
			if (!(tpa.containsValue(p.getName()))) {
				p.sendMessage(ChatColor.RED + "Du har ingen forespørsler!");
				return true;
			} else {
				for (String key : tpa.keySet()) {
					if (tpa.get(key).equals(p.getName())) {
						Player t = Bukkit.getServer().getPlayer(key);
						t.teleport(p.getLocation());
						t.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.GREEN + " godtokk forespørselen!");
						p.sendMessage(
								ChatColor.GREEN + "Du godtokk tpa forespørselen fra " + ChatColor.BLUE + t.getName());
						tpa.remove(t.getName(), p.getName());
					}

				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("avvis")) {
			if (plugin.getConfig().getBoolean("tpa") == false) {
				sender.sendMessage(ChatColor.RED
						+ "Error: Kommandoen /avvis er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
				return true;
			} else if (!(sender.hasPermission("norskekommandoer.tpa"))) {
				sender.sendMessage(
						ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
				return true;
			} else

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre denne kommandoen!");
				return true;
			}
			Player p = (Player) sender;
			if (!(tpa.containsValue(p.getName()))) {
				p.sendMessage(ChatColor.RED + "Du har ingen forespørsler!");
				return true;
			} else {
				for (String key : tpa.keySet()) {
					if (tpa.get(key).equals(p.getName())) {
						Player t = Bukkit.getServer().getPlayer(key);
						tpa.remove(t.getName(), p.getName());
						p.sendMessage(
								ChatColor.RED + "Du avviste tpa forspørselen fra " + ChatColor.BLUE + t.getName());
						t.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.RED + " avviste tpa forespørselen!");
						return true;
					}

				}
			}
		}
		return true;

	}
}
