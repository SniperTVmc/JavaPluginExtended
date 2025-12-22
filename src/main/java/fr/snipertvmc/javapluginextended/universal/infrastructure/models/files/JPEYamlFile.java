package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;

public class JPEYamlFile extends JPEFile {


	// -------------------------------------------------- //


	private YamlDocument yamlDocument;


	// -------------------------------------------------- //


	public JPEYamlFile(File file) {
		super(file);
	}

	public JPEYamlFile(File file, int fileVersion) {
		super(file, fileVersion);
	}

	public JPEYamlFile(File file, int fileVersion, String fileVersionKeyPath) {
		super(file, fileVersion, fileVersionKeyPath);
	}


	// -------------------------------------------------- //


	public void load() {
		super.loadFile();
		this.loadYamContent();
	}


	public void loadAsResource(String resourcePath, boolean replace) {
		super.loadResourceFile(resourcePath, replace);
		this.loadYamContent();
	}


	public void loadYamContent() {
		try {
			this.yamlDocument = YamlDocument.create(this.file);
			this.realFileVersion = this.yamlDocument.getInt(this.fileVersionKeyPath, 0);
			this.isLoaded = true;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	// -------------------------------------------------- //


	public Object get(String path) {
		return this.yamlDocument.get(path);
	}
	public Object get(String path, Object def) {
		return this.yamlDocument.get(path, def);
	}

	public YamlDocument getYamlDocument() {
		return this.yamlDocument;
	}


	// -------------------------------------------------- //
}
