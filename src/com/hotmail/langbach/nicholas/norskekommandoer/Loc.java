package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Loc implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("location") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /location er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre kommandoen!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.location"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else {
			Player p = (Player) sender;
			if (args.length == 0) {
				Location loc = p.getLocation();
				int x = loc.getBlockX();
				int y = loc.getBlockZ();
				int z = loc.getBlockY();
				p.sendMessage(ChatColor.GREEN + "Dine kordinater er: " + ChatColor.BLUE + "X: " + ChatColor.GREEN + x
						+ ChatColor.BLUE + " Y: " + ChatColor.GREEN + y + ChatColor.BLUE + " Z: " + ChatColor.GREEN
						+ z);
				return true;
			} else if (args.length >= 1) {

				if (Bukkit.getServer().getPlayer(args[0]) == null) {
					p.sendMessage(ChatColor.RED + "Spilleren " + args[0] + " er ikke pålogget!");
					return true;
				} else {
					Player t = Bukkit.getServer().getPlayer(args[0]);
					Location loc = t.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockZ();
					int z = loc.getBlockY();
					p.sendMessage(ChatColor.GREEN + "Spilleren " + ChatColor.BLUE + t.getName() + ChatColor.GREEN
							+ " sine kordinater er: " + ChatColor.BLUE + "X: " + ChatColor.GREEN + x + ChatColor.BLUE
							+ " Y: " + ChatColor.GREEN + y + ChatColor.BLUE + " Z: " + ChatColor.GREEN + z);
				}
			}
		}
		return true;
	}

}
