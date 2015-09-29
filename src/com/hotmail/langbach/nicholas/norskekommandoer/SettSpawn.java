package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettSpawn implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("settspawn") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /settspawn er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.settspawn"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Du kan ikke bruek kommandoen /settspawn!");
			return true;
		} else {
			Player p = (Player) sender;
			Location loc = p.getLocation();;
			settings.data.set("spawn.world", loc.getWorld().getName());
			settings.data.set("spawn.x", loc.getX());
			settings.data.set("spawn.y", loc.getY());
			settings.data.set("spawn.z", loc.getZ());
			settings.data.set("spawn.pitch", p.getLocation().getPitch());
			settings.data.set("spawn.yaw", p.getLocation().getYaw());
			settings.saveData();
			p.sendMessage(ChatColor.GREEN + "Du satt spawn punktet i verdenen " + ChatColor.BLUE
					+ loc.getWorld().getName() + ChatColor.GREEN + "!");
			return true;
		}

	}

}
