package fr.snipertvmc.javapluginextended.spigot.builders;

import fr.snipertvmc.javapluginextended.spigot.infrastructure.models.items.JPESpigotEnchantment;
import fr.snipertvmc.javapluginextended.spigot.infrastructure.models.items.JPESpigotLore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpigotItemBuilder {


	// -------------------------------------------------- //


	private Material material;
	private String displayName;

	private int amount = 1;
	private int durability = 0;

	private JPESpigotLore lore = new JPESpigotLore();
	private final List<JPESpigotEnchantment> enchantments = new ArrayList<>();
	private final List<ItemFlag> itemFlags = new ArrayList<>();

	private boolean unbreakable;
	private boolean glow;


	// -------------------------------------------------- //


	public SpigotItemBuilder(Material material) {
		this.material = material;
		this.displayName = material.name();
	}


	public SpigotItemBuilder(ItemStack itemStack) {
		this.copyOf(itemStack);
	}


	// -------------------------------------------------- //


	public SpigotItemBuilder material(Material material) {
		this.material = material;
		return this;
	}
	public SpigotItemBuilder displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}


	public SpigotItemBuilder amount(int amount) {
		this.amount = amount;
		return this;
	}
	public SpigotItemBuilder durability(int durability) {
		this.durability = durability;
		return this;
	}


	public SpigotItemBuilder lore(JPESpigotLore lore) {
		this.lore = lore;
		return this;
	}
	public SpigotItemBuilder addLoreLine(String line) {
		this.lore.addLine(line);
		return this;
	}
	public SpigotItemBuilder enchant(JPESpigotEnchantment enchantment) {
		this.enchantments.add(enchantment);
		return this;
	}
	public SpigotItemBuilder flag(ItemFlag itemFlag) {
		this.itemFlags.add(itemFlag);
		return this;
	}


	public SpigotItemBuilder unbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
		return this;
	}
	public SpigotItemBuilder glow(boolean glow) {
		this.glow = glow;
		return this;
	}


	// -------------------------------------------------- //


	public ItemStack build() {

		ItemStack itemStack = new ItemStack(this.material, this.amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta == null) {
			throw new IllegalStateException("ItemMeta is null for material: " + this.material);
		}

		itemMeta.setDisplayName(this.displayName);
		itemMeta.setLore(this.lore.build());

		if (itemMeta instanceof Damageable damageable) {
			damageable.setDamage(this.durability);
		}

		for (JPESpigotEnchantment enchantment : this.enchantments) {
			itemMeta.addEnchant(enchantment.getEnchantment(), enchantment.getLevel(), enchantment.ignoreLevelRestriction());
		}

		for (ItemFlag itemFlag : this.itemFlags) {
			itemMeta.addItemFlags(itemFlag);
		}

		itemMeta.setUnbreakable(this.unbreakable);
		if (this.glow) {
			itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}


	public ItemStack copyOf(ItemStack itemStack) {

		ItemMeta itemMeta = itemStack.getItemMeta();
		assert itemMeta != null;

		this.material = itemStack.getType();
		this.displayName = itemMeta.getDisplayName();

		this.amount = itemStack.getAmount();
		if (itemMeta instanceof Damageable damageable) {
			this.durability = damageable.getDamage();
		}

		this.lore = new JPESpigotLore(itemMeta.getLore());
		itemMeta.getEnchants().forEach((enchantment, level) -> {
			this.enchantments.add(new JPESpigotEnchantment(enchantment, level, true));
		});
		this.itemFlags.addAll(itemMeta.getItemFlags());

		this.unbreakable = itemMeta.isUnbreakable();
		this.glow = itemMeta.hasEnchant(Enchantment.UNBREAKING) && itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS);

		return itemStack;
	}


	// -------------------------------------------------- //
}
