package fr.snipertvmc.javapluginextended.universal.infrastructure.models.files;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JPEJsonFile extends JPEFile {


	// -------------------------------------------------- //


	private JsonElement jsonContent;


	// -------------------------------------------------- //


	public JPEJsonFile(File file) {
		super(file);
	}

	public JPEJsonFile(File file, int fileVersion) {
		super(file, fileVersion);
	}

	public JPEJsonFile(File file, int fileVersion, String fileVersionKeyPath) {
		super(file, fileVersion, fileVersionKeyPath);
	}


	// -------------------------------------------------- //


	public void load() {
		super.loadFile();
		this.loadJsonContent();
	}


	public void loadAsResource(String resourcePath, boolean replace) {
		super.loadResourceFile(resourcePath, replace);
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
