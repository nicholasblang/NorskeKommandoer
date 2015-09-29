package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StabHjelp implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 0) {

			if (settings.config.getBoolean("stabhjelp") == false) {
				sender.sendMessage(ChatColor.RED
						+ "Error: Kommandoen /stabhjelp er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
				return true;
			} else {

				StringBuilder str = new StringBuilder();
				for (int i = 0; i < args.length; i++) {
					str.append(args[i] + " ");
				}
				String mel = str.toString().trim();
				sender.sendMessage(ChatColor.GREEN + "Meldingen: " + ChatColor.BLUE + mel + ChatColor.GREEN
						+ " ble sendt til stab medlemmene!");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (pl.hasPermission("norskekommandoer.stab")) {
						pl.sendMessage(ChatColor.GOLD + "[StabHjelp] " + ChatColor.BLUE + sender.getName()
								+ ChatColor.GRAY + ": " + mel);
					}
				}

				return true;
			}
		}
		sender.sendMessage(ChatColor.RED + "Error: Husk å skriv en melding! /stabhjelp <melding>");
		return true;
	}

}
