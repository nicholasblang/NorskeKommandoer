package com.hotmail.langbach.nicholas.norskekommandoer;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Pvp implements CommandExecutor, Listener {

	SettingsManager settings = SettingsManager.getInstance();

	static HashSet<String> pv = new HashSet<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("pvp") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /pvp er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else {
			if (!(sender.hasPermission("norskekommandoer.pvp"))) {
				sender.sendMessage(
					ChatColor.RED + "Du har ikke rettigheter til å utføre kommandoen /" + cmd.getName() + "!");
				return true;
			} else if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Du må være en spiller for å utføre kommandoen /pvp!");
				return true;
			} else {
				Player p = (Player) sender;
				if (!(pv.contains(p.getName()))) {
					p.sendMessage(ChatColor.GREEN + "Du skrudde av pvp!");
					pv.add(p.getName());
					return true;
				} else if (pv.contains(p.getName())) {
					pv.remove(p.getName());
					p.sendMessage(ChatColor.RED + "Du skrudde på pvp!");
					return true;
				}
			}

		}
		return true;

	}
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		Entity victim = e.getEntity();
		if(victim instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) victim;
			Player d = (Player) e.getDamager();
			if(pv.contains(p.getName())) {
				d.sendMessage(ChatColor.RED + "Spilleren " + ChatColor.BLUE + p.getName() + ChatColor.RED + " har pvp skrudd av!");
				e.setCancelled(true);
			}else if(pv.contains(d.getName())) {
				d.sendMessage(ChatColor.RED + "Du har pvp skrudd av!");
				e.setCancelled(true);
			}
		}
	}
}
