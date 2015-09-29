package com.hotmail.langbach.nicholas.norskekommandoer;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StabSamtale implements CommandExecutor, Listener {


	SettingsManager settings = SettingsManager.getInstance();

	
	
	static HashSet<String> stab = new HashSet<String>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if (settings.config.getBoolean("stabsamtale") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /stabsamtale er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		} else if (!(sender instanceof Player)) {
			if (args.length > 0) {

				StringBuilder str = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					str.append(args[i] + " ");
				}
			
				String mel = str.toString().trim();

					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("norskekommandoer.stab")) {
							pl.sendMessage(ChatColor.GOLD + "[StabSamtale] " + ChatColor.RED + sender.getName()
									+ ChatColor.GRAY + ": " + mel);
							return true;
						}
						sender.sendMessage(ChatColor.RED + "Ingen stab medlemmer pålogget!");
						return true;
					}
				
			}
			sender.sendMessage(ChatColor.RED + "Error: For lite argumenter.");
			return true;
		} else {
			Player p = (Player) sender;

			if (!(stab.contains(p.getName()))) {
				stab.add(p.getName());
				p.sendMessage(ChatColor.GREEN + "Du skrudde på StabSamtale.");
				return true;
			} else if(stab.contains(p.getName())){
				stab.remove(p.getName());
				p.sendMessage(ChatColor.RED + "Du skrudde av StabSamtale.");
				return true;
			}
		}
		return true;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if(stab.contains(p.getName())) {
			e.setCancelled(true);
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(!(pl.hasPermission("norskekommandoer.stabsamtale"))) return;
				pl.sendMessage(ChatColor.GOLD + "[StabSamtale] " + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + e.getMessage());
			}
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if(stab.contains(e.getPlayer().getName())) {
			stab.remove(e.getPlayer().getName());
			
		}
	}
}
