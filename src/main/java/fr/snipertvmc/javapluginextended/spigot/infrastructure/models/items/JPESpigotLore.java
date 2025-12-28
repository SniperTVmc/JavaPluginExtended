package fr.snipertvmc.javapluginextended.spigot.infrastructure.models.items;

import fr.snipertvmc.javapluginextended.universal.utilities.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class JPESpigotLore {


	// -------------------------------------------------- //


	private final List<String> loreLines;


	// -------------------------------------------------- //


	public JPESpigotLore() {
		this.loreLines = new ArrayList<>();
	}


	public JPESpigotLore(String... loreLines) {
		this.loreLines = new ArrayList<>(List.of(loreLines));
	}


	public JPESpigotLore(List<String> loreLines) {
		this.loreLines = new ArrayList<>(loreLines);
	}


	// -------------------------------------------------- //


	public JPESpigotLore addLine(String loreLine) {
		this.loreLines.add(loreLine);
		return this;
	}


	public JPESpigotLore insertLine(int index, String loreLine) {
		this.loreLines.add(index, loreLine);
		return this;
	}


	public JPESpigotLore removeLine(String loreLine) {
		this.loreLines.remove(loreLine);
		return this;
	}


	public JPESpigotLore removeLine(int index) {
		this.loreLines.remove(index);
		return this;
	}


	public JPESpigotLore setLine(int index, String loreLine) {
		this.loreLines.set(index, loreLine);
		return this;
	}


	public JPESpigotLore setLines(List<String> loreLines) {
		this.loreLines.clear();
		this.loreLines.addAll(loreLines);
		return this;
	}


	// -------------------------------------------------- //


	public List<String> build() {
		return TextUtils.colorizeLegacy(this.loreLines);
	}


	public List<String> build(boolean colorize) {
		if (!colorize) {return this.loreLines;}
		return TextUtils.colorizeLegacy(this.loreLines);
	}


	// -------------------------------------------------- //
}
