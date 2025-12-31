package fr.snipertvmc.javapluginextended.spigot.builders;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class SpigotActionBarBuilder {


	// -------------------------------------------------- //


	private String message = "";

	private int fadeIn = 10;
	private int stay = 70;
	private int fadeOut = 20;


	// -------------------------------------------------- //


	public SpigotActionBarBuilder() {
	}


	public SpigotActionBarBuilder(String message) {
		this.message = message;
	}


	public SpigotActionBarBuilder(String message, int fadeIn, int stay, int fadeOut) {
		this.message = message;
		this.fadeIn = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}


	// -------------------------------------------------- //


	public String getMessage() {
		return message;
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


	public SpigotActionBarBuilder message(String message) {
		this.message = message;
		return this;
	}


	public SpigotActionBarBuilder fadeIn(int fadeIn) {
		this.fadeIn = fadeIn;
		return this;
	}
	public SpigotActionBarBuilder stay(int stay) {
		this.stay = stay;
		return this;
	}
	public SpigotActionBarBuilder fadeOut(int fadeOut) {
		this.fadeOut = fadeOut;
		return this;
	}


	// -------------------------------------------------- //


	public void show(Player... players) {
		for (Player player : players) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(this.message));
		}
	}


	// -------------------------------------------------- //
}
