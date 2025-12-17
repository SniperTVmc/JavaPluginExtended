package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JPEYamlFile{


	// -------------------------------------------------- //


	public boolean isLoaded = false;
	public final boolean isResourceFile;

	private short fileVersion = 0;
	private String fileVersionKey = "file-version";

	private final File file;
	private int realFileVersion;

	private YamlConfiguration yamlConfiguration;


	// -------------------------------------------------- //


	public JPEYamlFile(File file, boolean isResourceFile) {
		this.file = file;
		this.isResourceFile = isResourceFile;
	}


	public JPEYamlFile(File file, short fileVersion, boolean isResourceFile) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.isResourceFile = isResourceFile;
	}


	public JPEYamlFile(File file, short fileVersion, String fileVersionKey, boolean isResourceFile) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.fileVersionKey = fileVersionKey;
		this.isResourceFile = isResourceFile;
	}


	// -------------------------------------------------- //


	public void load() {

		if (this.isResourceFile) {
			throw new IllegalStateException("This file is marked as a resource file. Use loadAsResource() instead.");
		}

		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
		}

		this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
		this.realFileVersion = this.yamlConfiguration.getInt(this.fileVersionKey, 0);
		this.isLoaded = true;
	}


	public void loadAsResource(JavaPlugin javaPlugin) {

		if (!this.isResourceFile) {
			throw new IllegalStateException("This file is not marked as a resource file. Use load() instead.");
		}

		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
			javaPlugin.saveResource(this.file.getName(), false);
		}

		this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
		this.realFileVersion = this.yamlConfiguration.getInt(this.fileVersionKey, 0);
		this.isLoaded = true;
	}


	public boolean backupFile() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String formattedDate = dateFormat.format(currentDate);

		if (!this.file.exists()) {
			throw new IllegalStateException("The file to backup does not exist: " + this.file.getAbsolutePath());
		}

		File backupFile = new File(this.file.getParent(), formattedDate + "_" + this.file.getName());
		return this.file.renameTo(backupFile);
	}


	public boolean isUpToDate() {

		if (!this.isLoaded) {
			throw new IllegalStateException("The YAML file must be loaded before checking its version.");
		}
		return this.fileVersion == this.realFileVersion;
	}


	// -------------------------------------------------- //


	public boolean isLoaded() {
		return this.isLoaded;
	}

	public File getFile() {
		return this.file;
	}

	public YamlConfiguration getYamlConfiguration() {
		return this.yamlConfiguration;
	}

	public short getFileVersion() {
		return this.fileVersion;
	}


	// -------------------------------------------------- //
}
