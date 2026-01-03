package fr.snipertvmc.javapluginextended.spigot.infrastructure.models;

import fr.snipertvmc.javapluginextended.universal.infrastructure.models.JPEBase;

import java.io.File;

public class JPESpigotBase extends JPEBase {


	// -------------------------------------------------- //


	public JPESpigotBase() {
		super(new JPESpigotLogger());
	}


	// -------------------------------------------------- //


	public File getPluginFile() {
		return getFile();
	}


	// -------------------------------------------------- //
}
