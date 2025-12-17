package fr.snipertvmc.javapluginextended.bukkit.infrastructure.models;

import fr.snipertvmc.javapluginextended.universal.infrastructure.models.JPELogger;
import org.bukkit.Bukkit;

public class JPEBukkitLogger implements JPELogger {


	// -------------------------------------------------- //


	@Override
	public void info(String message) { Bukkit.getLogger().info(message); }
	@Override
	public void log(String message) { Bukkit.getLogger().info(message); }

	@Override
	public void warning(String message) { Bukkit.getLogger().warning(message); }
	@Override
	public void warn(String message) { Bukkit.getLogger().warning(message); }

	@Override
	public void error(String message) { Bukkit.getLogger().severe(message); }
	@Override
	public void severe(String message) { Bukkit.getLogger().severe(message); }

	@Override
	public void debug(String message) { Bukkit.getLogger().info("[DEBUG] " + message); }


	// -------------------------------------------------- //
}
