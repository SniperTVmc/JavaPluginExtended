package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import fr.snipertvmc.javapluginextended.universal.utilities.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JPEFile {


	// -------------------------------------------------- //


	protected boolean isLoaded = false;

	protected int fileVersion = 0;
	protected String fileVersionKeyPath = "file-version";

	protected final File file;
	protected int realFileVersion;


	// -------------------------------------------------- //


	public JPEFile(File file) {
		this.file = file;
	}


	public JPEFile(File file, int fileVersion) {
		this.file = file;
		this.fileVersion = fileVersion;
	}


	public JPEFile(File file, int fileVersion, String fileVersionKeyPath) {
		this.file = file;
		this.fileVersion = fileVersion;
		this.fileVersionKeyPath = fileVersionKeyPath;
	}


	// -------------------------------------------------- //


	protected void loadFile() {
		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();
		}
	}


	protected void loadResourceFile(String resourcePath, boolean replace) {
		FileUtils.loadResourceFile(this.file, resourcePath, replace);
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
