package fr.snipertvmc.javapluginextended.bukkit.infrastructure.models.scoreboard;

import fr.snipertvmc.javapluginextended.bukkit.libraries.fastinv.FastBoard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JPEScoreboard {


	// -------------------------------------------------- //


	private final Player owner;
	private FastBoard fastBoard;

	private String title = "Default Title";
	private final List<String> lines = new ArrayList<>();


	// -------------------------------------------------- //


	public JPEScoreboard(Player player) {
		this.owner = player;
		this.fastBoard = new FastBoard(this.owner);
	}


	// -------------------------------------------------- //


	public JPEScoreboard setTitle(String title) {
		this.title = title;
		return this;
	}


	public JPEScoreboard addLine(String line) {
		this.lines.add(line);
		return this;
	}


	public JPEScoreboard setLines(List<String> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
		return this;
	}


	public JPEScoreboard show() {
		if (this.fastBoard == null) {
			this.fastBoard = new FastBoard(this.owner);
		}
		return this;
	}


	public JPEScoreboard hide() {
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
