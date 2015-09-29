package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("fly") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /fly er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utøfre kommandoen!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.fly"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else {
			if (args.length != 0) {
				if (Bukkit.getPlayer(args[0]) == null) {
					sender.sendMessage(ChatColor.RED + "Spilleren " + ChatColor.BLUE + args[0] + ChatColor.RED
							+ " er ikke pålogget!");
					return true;
				}
				Player p = (Player) sender;
				Player t = Bukkit.getPlayer(args[0]);
				t.setAllowFlight(!(t.getAllowFlight()));
				t.sendMessage(t.getAllowFlight()
						? ChatColor.GREEN + "Spilleren " + ChatColor.BLUE + p.getName() + ChatColor.GREEN
								+ " skrudde på fly modus til deg!"
						: ChatColor.RED + "Spilleren " + ChatColor.BLUE + args[0] + ChatColor.RED
								+ " skrudde av fly modus til deg!");
				p.sendMessage(t.getAllowFlight()
						? ChatColor.GREEN + "Du skrudde på fly modus til " + ChatColor.BLUE + t.getName()
						: ChatColor.RED + "Du skrudde av fly modus til " + ChatColor.BLUE + t.getName());
				return true;

			} else {
				Player p = (Player) sender;
				p.setAllowFlight(!(p.getAllowFlight()));
				p.sendMessage(p.getAllowFlight() ? ChatColor.GREEN + "Du har slått på fly modus!"
						: ChatColor.RED + "Du har slått av fly modus!");
				return true;
			}

		}
	}

}
