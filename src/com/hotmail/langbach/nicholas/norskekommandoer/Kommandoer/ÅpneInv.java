package com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hotmail.langbach.nicholas.norskekommandoer.Main;

public class ÅpneInv implements CommandExecutor {

	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin("NorskeKommandoer");

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("openinv") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /openinv er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utføre kommandoen!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.opneinv"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
			return true;
		} else {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "Feil: Husk å skriv navn!");
				return true;
			} else if (Bukkit.getServer().getPlayer(args[0]) == null) {
				p.sendMessage(ChatColor.RED + "Spilleren " + args[0] + " er ikke pålogget!");
				return true;
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				p.openInventory(t.getInventory());
				p.sendMessage(ChatColor.GREEN + "Åpnet " + ChatColor.BLUE + t.getName() + ChatColor.GREEN
						+ " sitt inventory.");
				return true;
			}
		}
	}

}
