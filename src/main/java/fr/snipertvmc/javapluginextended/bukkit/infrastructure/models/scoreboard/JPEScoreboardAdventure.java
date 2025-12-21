package fr.snipertvmc.javapluginextended.bukkit.infrastructure.models.scoreboard;

import fr.snipertvmc.javapluginextended.bukkit.libraries.fastinv.FastBoardAdventure;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JPEScoreboardAdventure {


	// -------------------------------------------------- //


	private final Player owner;
	private FastBoardAdventure fastBoardAdventure;

	private Component title = Component.text("Default Title");
	private final List<Component> lines = new ArrayList<>();


	// -------------------------------------------------- //


	public JPEScoreboardAdventure(Player player) {
		this.owner = player;
		this.fastBoardAdventure = new FastBoardAdventure(this.owner);
	}


	// -------------------------------------------------- //


	public JPEScoreboardAdventure setTitle(Component title) {
		this.title = title;
		return this;
	}


	public JPEScoreboardAdventure addLine(Component line) {
		this.lines.add(line);
		return this;
	}


	public JPEScoreboardAdventure setLines(List<Component> lines) {
		this.lines.clear();
		this.lines.addAll(lines);
		return this;
	}


	public JPEScoreboardAdventure show() {
		if (this.fastBoardAdventure == null) {
			this.fastBoardAdventure = new FastBoardAdventure(this.owner);
		}
		return this;
	}


	public JPEScoreboardAdventure hide() {
		if (this.fastBoardAdventure != null) {
			this.fastBoardAdventure.delete();
			this.fastBoardAdventure = null;
		}
		return this;
	}


	public void update() {
		if (this.fastBoardAdventure != null) {
			this.fastBoardAdventure.updateTitle(this.title);
			this.fastBoardAdventure.updateLines(this.lines);
		}
	}


	// -------------------------------------------------- //


	public Player getOwner() {
		return owner;
	}
	public FastBoardAdventure getFastBoardAdventure() {
		return fastBoardAdventure;
	}

	public Component getTitle() {
		return title;
	}
	public List<Component> getLines() {
		return lines;
	}


	// -------------------------------------------------- //
}
