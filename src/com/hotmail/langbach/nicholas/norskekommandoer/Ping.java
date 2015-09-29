package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (settings.config.getBoolean("ping") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /ping er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre kommandoen!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.ping"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else {
			Player p = (Player) sender;
			int ping = ((CraftPlayer) p).getHandle().ping;

			if (args.length != 0) {
				if (Bukkit.getPlayer(args[0]) == null) {
					p.sendMessage(ChatColor.RED + "Spilleren " + args[0] + " er ikke pålogget");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				p.sendMessage(ChatColor.BLUE + t.getName() + ChatColor.GREEN + " sin ping er: " + ChatColor.BLUE + ping
						+ ChatColor.GREEN + "ms");
			} else {
				p.sendMessage(ChatColor.GREEN + "Din ping er: " + ChatColor.BLUE + ping + ChatColor.GREEN + "ms");
				return true;
			}

		}

		return true;
	}

}
