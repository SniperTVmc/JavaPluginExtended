package fr.snipertvmc.javapluginextended.spigot.builders;

import org.bukkit.entity.Player;

public class SpigotTitleBuilder {


	// -------------------------------------------------- //


	private String title = "";
	private String subtitle = "";

	private int fadeIn = 10;
	private int stay = 70;
	private int fadeOut = 20;


	// -------------------------------------------------- //


	public SpigotTitleBuilder() {
	}


	public SpigotTitleBuilder(String title) {
		this.title = title;
	}


	public SpigotTitleBuilder(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}


	public SpigotTitleBuilder(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		this.title = title;
		this.subtitle = subtitle;
		this.fadeIn = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}


	// -------------------------------------------------- //


	public String getTitle() {
		return title;
	}
	public String getSubtitle() {
		return subtitle;
	}

	public int getFadeIn() {
		return fadeIn;
	}
	public int getStay() {
		return stay;
	}
	public int getFadeOut() {
		return fadeOut;
	}


	// -------------------------------------------------- //


	public SpigotTitleBuilder title(String title) {
		this.title = title;
		return this;
	}
	public SpigotTitleBuilder subtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}


	public SpigotTitleBuilder fadeIn(int fadeIn) {
		this.fadeIn = fadeIn;
		return this;
	}
	public SpigotTitleBuilder stay(int stay) {
		this.stay = stay;
		return this;
	}
	public SpigotTitleBuilder fadeOut(int fadeOut) {
		this.fadeOut = fadeOut;
		return this;
	}


	// -------------------------------------------------- //


	public void show(Player... players) {
		for (Player player : players) {
			player.sendTitle(this.title, this.subtitle, this.fadeIn, this.stay, this.fadeOut);
		}
	}


	// -------------------------------------------------- //
}
