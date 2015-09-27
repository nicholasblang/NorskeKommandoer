package com.hotmail.langbach.nicholas.norskekommandoer.Kommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hotmail.langbach.nicholas.norskekommandoer.Main;

public class Spawn implements CommandExecutor {

	private static Main plugin = (Main) Bukkit.getPluginManager().getPlugin("NorskeKommandoer");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("spawn") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /spawn er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare en spiller kan utøfre kommandoen!");
			return true;
		} else {
			Player p = (Player) sender;
			p.teleport(p.getWorld().getSpawnLocation());
			p.sendMessage(ChatColor.GREEN + "Du teleporterte til spawn!");
		}
		return true;
	}

}
