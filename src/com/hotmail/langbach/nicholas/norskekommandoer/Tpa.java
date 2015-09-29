package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpa implements CommandExecutor{

	SettingsManager settings = SettingsManager.getInstance();
	private static Main plugin = (Main)Bukkit.getPluginManager().getPlugin("Main");
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if (settings.config.getBoolean("tpa") == false) {
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
				if (Bukkit.getServer().getPlayer(args[0]) == null) {
					p.sendMessage(ChatColor.RED + "Kunne ikke finne spilleren " + ChatColor.BLUE + args[0]);
					return true;
				}

				Player t = Bukkit.getServer().getPlayer(args[0]);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						if (Main.tpa.containsKey(t.getName())) {
							Main.tpa.remove(t.getName(), p.getName());
							p.sendMessage(ChatColor.RED + "TPA forspørselen fra " + ChatColor.BLUE + t.getName()
									+ ChatColor.RED + " utløpte!");
							t.sendMessage(ChatColor.RED + "Din TPA forspørsel utløpte!");
						}

					}

				}, settings.config.getInt("Sekunder") * 20);
				Main.tpa.put(p.getName(), t.getName());
				t.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.GREEN
						+ " vil teleportere til deg! Skriv /godta for å godta eller skriv /avvis for å avvise.");
				p.sendMessage(ChatColor.GREEN + "Du sendte en tpa forespørsel til " + ChatColor.BLUE + t.getName()
						+ ChatColor.GREEN + "!");
				return true;

			}
		}
		return true;
	}
	
	

}
