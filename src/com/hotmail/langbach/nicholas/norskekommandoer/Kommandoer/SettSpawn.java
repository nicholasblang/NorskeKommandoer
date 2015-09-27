package com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hotmail.langbach.nicholas.norskekommandoer.Main;

public class SettSpawn implements CommandExecutor {

	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin("NorskeKommandoer");

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("settspawn") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /settspawn er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender instanceof Player)) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.settspawn"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen " + cmd.getName() + "!");
			return true;
		} else {
			Player p = (Player) sender;
			Location loc = p.getLocation();
			p.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			p.sendMessage(ChatColor.GREEN + "Du sett spawn punktet i verdenen " + ChatColor.BLUE
					+ p.getWorld().getName() + ChatColor.GREEN + "!");

			return true;
		}

	}

}
