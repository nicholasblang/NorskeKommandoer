package com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import com.hotmail.langbach.nicholas.norskekommandoer.Main;

public class NorskeKommandoer implements CommandExecutor {

	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin("NorskeKommandoer");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		PluginDescriptionFile pdf = plugin.getDescription();
		if (args.length == 0) {

			sender.sendMessage(ChatColor.GREEN + "Tilgjenglige sub-kommandoer:");
			sender.sendMessage(ChatColor.GREEN + "/norskekommandoer reload" + ChatColor.RED + " | " + ChatColor.GREEN
					+ "reloader config.yml filen.");
			sender.sendMessage(ChatColor.GREEN + "/norskekommandoer info" + ChatColor.RED + " | " + ChatColor.GREEN
					+ "gir deg info om pluginen.");
		} else
			switch (args[0].toLowerCase()) {

			case "reload":
				if (!(sender.hasPermission("norskekommandoer.reload"))) {
					sender.sendMessage(ChatColor.RED
							+ "Du har ikke rettigheter til å utføre kommandoen /norskekommandoer reload!");
					return true;
				} else

					plugin.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Config.yml ble velykket reloaded!");
				break;
			case "info":
				sender.sendMessage(ChatColor.GREEN + "---==[ NorskeKommandoer ]==---");
				sender.sendMessage(ChatColor.GREEN + "Versjon: " + ChatColor.BLUE + pdf.getVersion());
				sender.sendMessage(ChatColor.GREEN + "Utvikler: " + ChatColor.BLUE + pdf.getAuthors());
				break;
			default:
				sender.sendMessage(ChatColor.RED + "Kunne ikke finne en sub-kommando som het " + args[0] + "!");
				break;
			}

		return true;
	}

}
