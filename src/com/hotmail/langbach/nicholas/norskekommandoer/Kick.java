package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("kick") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /kick er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender.hasPermission("norskekommandoer.kick"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else

		if (args.length != 0) {
			if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]) == null) {
					sender.sendMessage(ChatColor.RED + "Error: Kunne ikke finne spilleren " + ChatColor.BLUE + args[0]);
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				t.kickPlayer(ChatColor.RED + "Du ble kicket av " + ChatColor.BLUE + sender.getName() + ChatColor.RED
						+ "." + ChatColor.BLUE + " Grunn: " + ChatColor.RED + "Grunn ikke oppgitt.");
				if (settings.config.getBoolean("KickBroadcast") == true) {
					Bukkit.broadcastMessage(ChatColor.GREEN + "Spilleren " + ChatColor.BLUE + sender.getName()
							+ ChatColor.GREEN + " kicket " + ChatColor.BLUE + t.getName() + ChatColor.GREEN + "."
							+ ChatColor.BLUE + " Grunn: " + ChatColor.GREEN + "Grunn ikke oppgitt");
							return true;
				}
				return true;
			} else {
				if (Bukkit.getPlayer(args[0]) == null) {
					sender.sendMessage(ChatColor.RED + "Error: Kunne ikke finne spilleren " + ChatColor.BLUE + args[0]);
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				StringBuilder str = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					str.append(args[i] + " ");
				}
				String mel = str.toString().trim();
				t.kickPlayer(ChatColor.RED + "Du ble kicket av " + ChatColor.BLUE + sender.getName() + ChatColor.RED
						+ "." + ChatColor.BLUE + " Grunn: " + ChatColor.RED + mel);
				
				if (settings.config.getBoolean("KickBroadcast") == true) {
					Bukkit.broadcastMessage(ChatColor.GREEN + "Spilleren " + ChatColor.BLUE + sender.getName()
							+ ChatColor.GREEN + " kicket " + ChatColor.BLUE + t.getName() + ChatColor.GREEN + "."
							+ ChatColor.BLUE + " Grunn: " + ChatColor.GREEN + mel);
							return true;
				}
			}

			return true;
		}
		sender.sendMessage(ChatColor.RED + "Error: Husk å skriv spiller! /kick <spiller> [grunn]");
		return true;
	}

}
