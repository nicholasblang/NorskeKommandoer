package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Godta implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("godta")) {
			if (settings.config.getBoolean("tpa") == false) {
				sender.sendMessage(ChatColor.RED
						+ "Error: Kommandoen /godta er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
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
			if (!(Main.tpa.containsValue(p.getName()))) {
				p.sendMessage(ChatColor.RED + "Du har ingen forespørsler!");
				return true;
			} else {
				for (String key : Main.tpa.keySet()) {
					if (Main.tpa.get(key).equals(p.getName())) {
						@SuppressWarnings("deprecation")
						Player t = Bukkit.getServer().getPlayer(key);
						t.teleport(p.getLocation());
						t.sendMessage(ChatColor.BLUE + p.getName() + ChatColor.GREEN + " godtokk forespørselen!");
						p.sendMessage(
								ChatColor.GREEN + "Du godtokk tpa forespørselen fra " + ChatColor.BLUE + t.getName());
						Main.tpa.remove(t.getName(), p.getName());
					}

				}
			}
		}
		return true;
	}

}
