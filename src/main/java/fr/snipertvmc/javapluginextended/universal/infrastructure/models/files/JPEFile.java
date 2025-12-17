package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JPEFile {


	// -------------------------------------------------- //


	protected boolean isLoaded = false;
	protected final boolean isResourceFile;

	protected int fileVersion = 0;
	protected String fileVersionKeyPath = "file-version";

	protected final File file;
	protected int realFileVersion;


	// -------------------------------------------------- //


	public JPEFile(File file, boolean isResourceFile) {
		this.file = file;
		this.isResourceFile = isResourceFile;
	}


	public JPEFile(File file, int fileVersion, boolean isResourceFile) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.isResourceFile = isResourceFile;
	}


	public JPEFile(File file, int fileVersion, String fileVersionKeyPath, boolean isResourceFile) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.fileVersionKeyPath = fileVersionKeyPath;
		this.isResourceFile = isResourceFile;
	}


	// -------------------------------------------------- //


	protected void loadFile() {

		if (this.isResourceFile) {
			throw new IllegalStateException("This file is marked as a resource file. Use loadAsResource() instead.");
		}

		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
		}
	}


	protected void loadFileAsResource(JavaPlugin javaPlugin) {

		if (!this.isResourceFile) {
			throw new IllegalStateException("This file is not marked as a resource file. Use load() instead.");
		}

		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
			javaPlugin.saveResource(this.file.getName(), false);
		}
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

	public int getFileVersion() {
		return this.fileVersion;
	}


	// -------------------------------------------------- //
}
