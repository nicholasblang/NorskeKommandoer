package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stuck implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("stuck") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /stuck er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utf�re kommandoen!");
			return true;
		} else if (!(sender.hasPermission("norskekommandoer.stuck"))) {
			sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til � utf�re kommandoen /" + cmd.getName() + "!");
			return true;
		} else {
			Player p = (Player) sender;
			Block b = p.getWorld().getHighestBlockAt(p.getLocation());
			Location loc = b.getLocation();
			p.teleport(loc);
			p.sendMessage(ChatColor.GREEN + "Du ble teleportert til den h�yeste blokken over deg!");
		}
		return true;
	}

}
