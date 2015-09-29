package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NorskeKommandoer implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {

			sender.sendMessage(ChatColor.GREEN + "Tilgjenglige sub-kommandoer:");
			sender.sendMessage(ChatColor.GREEN + "/nk reload" + ChatColor.RED + " | " + ChatColor.GREEN
					+ "reloader config.yml filen.");
			
		} else
			switch (args[0].toLowerCase()) {

			case "reload":
				if (!(sender.hasPermission("norskekommandoer.reload"))) {
					sender.sendMessage(ChatColor.RED
							+ "Du har ikke rettigheter til å utføre kommandoen /norskekommandoer reload!");
					return true;
				} else

					settings.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Config.yml ble velykket reloaded!");
				break;
			
			default:
				sender.sendMessage(ChatColor.RED + "Kunne ikke finne en sub-kommando som het " + args[0] + "!");
				break;
			}

		return true;
	}

}
