package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JPEJsonFile extends JPEFile {


	// -------------------------------------------------- //


	private JsonElement jsonContent;


	// -------------------------------------------------- //


	public JPEJsonFile(File file, boolean isResourceFile) {
		super(file, isResourceFile);
	}

	public JPEJsonFile(File file, int fileVersion, boolean isResourceFile) {
		super(file, fileVersion, isResourceFile);
	}

	public JPEJsonFile(File file, int fileVersion, String fileVersionKeyPath, boolean isResourceFile) {
		super(file, fileVersion, fileVersionKeyPath, isResourceFile);
	}


	// -------------------------------------------------- //


	public void load() {
		this.loadFile();
		this.loadJsonContent();
	}


	public void loadAsResource(JavaPlugin javaPlugin) {
		this.loadFileAsResource(javaPlugin);
		this.loadJsonContent();
	}


	private void loadJsonContent() {
		try (FileReader fileReader = new FileReader(this.file)) {

			this.jsonContent = new Gson().fromJson(fileReader, JsonElement.class);
			JsonElement jsonElement = this.jsonContent.getAsJsonObject().get(this.fileVersionKeyPath);

			if (jsonElement != null && jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
				this.realFileVersion = jsonElement.getAsInt();
			} else {
				this.realFileVersion = 0;
			}

			this.isLoaded = true;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException("Invalid JSON syntax in file: " + this.file.getAbsolutePath(), e);
		}
	}


	// -------------------------------------------------- //


	public JsonElement get(String path) { return this.jsonContent.getAsJsonObject().get(path); }

	public JsonElement getJsonContent() { return this.jsonContent; }


	// -------------------------------------------------- //
}
