package com.hotmail.langbach.nicholas.norskekommandoer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	SettingsManager settings = SettingsManager.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (settings.config.getBoolean("spawn") == false) {
			sender.sendMessage(ChatColor.RED
					+ "Error: Kommandoen /spawn er deaktivert. Hvis du tror dette er en feil, kontakt en server administrator.");
			return true;
		}else if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Du kan ikke bruke kommandoen /spawn!");
			return true;
		}else {
			Player p = (Player) sender;
			if(settings.data.getConfigurationSection("spawn") == null) {
				p.sendMessage(ChatColor.RED + "Spawnen har ikke blitt settet enda!");
				return true;
			}else {
				World w = Bukkit.getServer().getWorld(settings.data.getString("spawn.world"));
				double x = settings.data.getDouble("spawn.x");
				double y = settings.data.getDouble("spawn.y");
				double z = settings.data.getDouble("spawn.z");
				float pitch = (float) settings.data.getDouble("spawn.pitch");
				float yaw = (float) settings.data.getDouble("spawn.yaw");
				
				p.teleport(new Location(w, x, y, z, yaw, pitch));
				p.sendMessage(ChatColor.GREEN + "Du teleportere til Spawn!");
				return true;
			}
		}
	}

}
