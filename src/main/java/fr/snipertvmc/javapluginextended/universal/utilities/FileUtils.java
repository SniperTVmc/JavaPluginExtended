package fr.snipertvmc.javapluginextended.universal.utilities;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtils {


	// -------------------------------------------------- //


	public static boolean loadResourceFile(File file, String resourcePath, boolean replace) {

		try (InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (inputStream == null) {
				System.err.println("Resource not found: " + resourcePath);
				return false;
			}

			if (replace || !file.exists()) {

				if (file.getParentFile() != null && !file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return true;
			}

			return false;

		} catch (Exception e) {
			System.err.println("Error while loading resource file: " + resourcePath);
			return false;
		}
	}
}
