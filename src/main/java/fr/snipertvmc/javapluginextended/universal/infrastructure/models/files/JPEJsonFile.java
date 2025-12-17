package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JPEJsonFile {


	// -------------------------------------------------- //


	public boolean isLoaded = false;
	public final boolean isResourceFile;

	private String fileVersion = null;
	private String fileVersionKey = "file-version";

	private final File file;
	private String realFileVersion = "1.0";

	private YamlConfiguration yamlConfiguration;


	// -------------------------------------------------- //


	public JPEJsonFile(File file, boolean isResourceFile) {
		this.file = file;
		this.isResourceFile = isResourceFile;
	}


	public JPEJsonFile(File file, String fileVersion, boolean isResourceFile) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.isResourceFile = isResourceFile;
	}


	public JPEJsonFile(File file, String fileVersion, String fileVersionKey, boolean isResourceFile) {
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
		this.realFileVersion = this.yamlConfiguration.getString(this.fileVersionKey, "0.0");
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
		this.realFileVersion = this.yamlConfiguration.getString(this.fileVersionKey, "0.0");
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
		return this.fileVersion.equals(this.realFileVersion);
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

	public String getFileVersion() {
		return this.fileVersion;
	}


	// -------------------------------------------------- //
}
