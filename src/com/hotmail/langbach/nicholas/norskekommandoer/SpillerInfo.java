package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SpillerInfo implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("spillerinfo") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /spillerinfo er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.spillerinfo"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else

		if (args.length != 0) {
			if (Bukkit.getPlayer(args[0]) == null && Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {
				OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
				sender.sendMessage(
						ChatColor.GREEN + "---==[ " + ChatColor.BLUE + t.getName() + ChatColor.GREEN + " ]==---");
				sender.sendMessage(ChatColor.GREEN + "Bannet: " + ChatColor.BLUE + t.isBanned());
				sender.sendMessage(ChatColor.GREEN + "Op: " + ChatColor.BLUE + t.isOp());
				sender.sendMessage(ChatColor.GREEN + "Whitelisted: " + ChatColor.BLUE + t.isWhitelisted());
				return true;
				
			} else if (Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage(ChatColor.RED + "Error: Spilleren " + args[0] + " har ikke spilt på serveren før!");
				return true;
			} else  {
				Player t = Bukkit.getPlayer(args[0]);
				sender.sendMessage(
						ChatColor.GREEN + "---==[ " + ChatColor.BLUE + t.getName() + ChatColor.GREEN + " ]==---");
				sender.sendMessage(ChatColor.GREEN + "Gamemode: " + ChatColor.BLUE + t.getGameMode());
				sender.sendMessage(ChatColor.GREEN + "Matliv: " + ChatColor.BLUE + t.getFoodLevel());
				sender.sendMessage(ChatColor.GREEN + "Liv: " + ChatColor.BLUE + t.getHealth());
				sender.sendMessage(ChatColor.GREEN + "UUID: " + ChatColor.BLUE + t.getUniqueId().toString());
				sender.sendMessage(ChatColor.GREEN + "Ping: " + ChatColor.BLUE + ((CraftPlayer) t).getHandle().ping
						+ ChatColor.GREEN + "ms");
				sender.sendMessage(
						ChatColor.GREEN + "IP: " + ChatColor.BLUE + t.getAddress().getAddress().getHostAddress());
				return true;
			}
		}
		sender.sendMessage(ChatColor.RED + "Error: Husk å skriv navn! /spillerinfo <spiller>");
		return true;
	}

}
