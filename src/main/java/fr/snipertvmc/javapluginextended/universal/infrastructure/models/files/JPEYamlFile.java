package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class JPEYamlFile extends JPEFile {


	// -------------------------------------------------- //


	private YamlConfiguration yamlConfiguration;


	// -------------------------------------------------- //


	public JPEYamlFile(File file, boolean isResourceFile) {
		super(file, isResourceFile);
	}

	public JPEYamlFile(File file, int fileVersion, boolean isResourceFile) {
		super(file, fileVersion, isResourceFile);
	}

	public JPEYamlFile(File file, int fileVersion, String fileVersionKeyPath, boolean isResourceFile) {
		super(file, fileVersion, fileVersionKeyPath, isResourceFile);
	}


	// -------------------------------------------------- //


	public void load() {
		this.loadFile();

		this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
		this.realFileVersion = this.yamlConfiguration.getInt(this.fileVersionKeyPath, 0);
		this.isLoaded = true;
	}


	public void loadAsResource(JavaPlugin javaPlugin) {
		this.loadFileAsResource(javaPlugin);

		this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
		this.realFileVersion = this.yamlConfiguration.getInt(this.fileVersionKeyPath, 0);
		this.isLoaded = true;
	}


	// -------------------------------------------------- //


	public Object get(String path) {
		return this.yamlConfiguration.get(path);
	}
	public Object get(String path, Object def) {
		return this.yamlConfiguration.get(path, def);
	}

	public YamlConfiguration getYamlConfiguration() {
		return this.yamlConfiguration;
	}


	// -------------------------------------------------- //
}
