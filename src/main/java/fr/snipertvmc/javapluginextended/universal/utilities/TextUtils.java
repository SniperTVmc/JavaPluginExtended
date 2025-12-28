package fr.snipertvmc.javapluginextended.universal.utilities;

import java.util.List;

public class TextUtils {


	// -------------------------------------------------- //


	public static String colorizeLegacy(String text) {
		return text.replace("&", "§");
	}


	public static List<String> colorizeLegacy(List<String> texts) {
		return texts.stream().map(TextUtils::colorizeLegacy).toList();
	}


	// -------------------------------------------------- //
}
