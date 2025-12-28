package fr.snipertvmc.javapluginextended.spigot.infrastructure.models.items;

import org.bukkit.enchantments.Enchantment;

public class JPESpigotEnchantment {


	// -------------------------------------------------- //


	private final Enchantment enchantment;

	private int level = 1;
	private boolean ignoreLevelRestriction = false;


	// -------------------------------------------------- //


	public JPESpigotEnchantment(Enchantment enchantment) {
		this.enchantment = enchantment;
	}
	public JPESpigotEnchantment(Enchantment enchantment, int level) {
		this.enchantment = enchantment;
		this.level = level;
	}
	public JPESpigotEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
		this.enchantment = enchantment;
		this.level = level;
		this.ignoreLevelRestriction = ignoreLevelRestriction;
	}


	// -------------------------------------------------- //


	public Enchantment getEnchantment() {
		return enchantment;
	}
	public int getLevel() {
		return level;
	}
	public boolean ignoreLevelRestriction() {
		return ignoreLevelRestriction;
	}


	// -------------------------------------------------- //
}
