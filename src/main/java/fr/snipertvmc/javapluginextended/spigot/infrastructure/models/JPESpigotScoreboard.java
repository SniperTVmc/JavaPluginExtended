package fr.snipertvmc.javapluginextended.spigot.infrastructure.models;

import fr.snipertvmc.javapluginextended.universal.libraries.fastinv.FastBoard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JPESpigotScoreboard {


	// -------------------------------------------------- //


	private final Player owner;
	private FastBoard fastBoard;

	private String title = "Default Title";
	private final List<String> lines = new ArrayList<>();


	// -------------------------------------------------- //


	public JPESpigotScoreboard(Player player) {
		this.owner = player;
		this.fastBoard = new FastBoard(this.owner);
	}


	// -------------------------------------------------- //


	public JPESpigotScoreboard setTitle(String title) {
		this.title = title;
		return this;
	}


	public JPESpigotScoreboard addLine(String line) {
		this.lines.add(line);
		return this;
	}


	public JPESpigotScoreboard setLines(List<String> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
		return this;
	}


	public JPESpigotScoreboard show() {
		if (this.fastBoard == null) {
			this.fastBoard = new FastBoard(this.owner);
		}
		return this;
	}


	public JPESpigotScoreboard hide() {
		if (this.fastBoard != null) {
			this.fastBoard.delete();
			this.fastBoard = null;
		}
		return this;
	}


	public void update() {
		if (this.fastBoard != null) {
			this.fastBoard.updateTitle(this.title);
			this.fastBoard.updateLines(this.lines);
		}
	}


	// -------------------------------------------------- //


	public Player getOwner() {
		return owner;
	}
	public FastBoard getFastBoard() {
		return fastBoard;
	}

	public String getTitle() {
		return title;
	}
	public List<String> getLines() {
		return lines;
	}


	// -------------------------------------------------- //
}
