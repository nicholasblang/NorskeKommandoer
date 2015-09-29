package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rens implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("rens") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /rens er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.rens"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Du kan ikke utføre kommandoen /rens!");
			return true;
		} else if (args.length > 0) {
			Player p = (Player) sender;
			if (Bukkit.getPlayer(args[0]) == null) {
				p.sendMessage(ChatColor.RED + "Error: Spilleren " + ChatColor.BLUE + args[0] + ChatColor.RED
						+ " er ikke pålogget!");
				return true;
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				t.sendMessage(ChatColor.GREEN + "Spilleren " + ChatColor.BLUE + p.getName() + ChatColor.GREEN
						+ " renset ditt inventar.");
				p.sendMessage(ChatColor.GREEN + "Du renset " + ChatColor.BLUE + t.getName() + ChatColor.GREEN
						+ " sitt inventar.");
				return true;
			}
		} else if (args.length == 0) {
			Player p = (Player) sender;
			p.sendMessage(ChatColor.GREEN + "Renset ditt inventar!");
			p.getInventory().clear();
			return true;
		}
		return true;

	}
}
