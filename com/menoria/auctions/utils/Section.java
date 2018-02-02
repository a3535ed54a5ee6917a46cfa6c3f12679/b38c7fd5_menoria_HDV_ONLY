package com.menoria.auctions.utils;

import lombok.Getter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

@Getter
public enum Section {

	COMBAT("Combat", Colors.RED) {
		public boolean isApplicable(ItemStack stack) {
			return stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemBow;
		}
	},
	TOOLS("Outils", Colors.BLUE) {
		public boolean isApplicable(ItemStack stack) {
			return stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemHoe;
		}
	},
	BLOCKS("Blocs", Colors.ORANGE) {
		public boolean isApplicable(ItemStack stack) {
			return stack.getItem() instanceof ItemBlock || stack.getItem() instanceof ItemBlockWithMetadata;
		}
	},
	ALL("Tout", Colors.GREEN) {	
		public boolean isApplicable(ItemStack stack) {
			return true;
		}
	};
	
	private String label;
	private Colors color;
	
	Section(String label, Colors color) {
		this.label = label;
		this.color = color;
	}
	
	public boolean isApplicable(ItemStack item) {return false;};
	
	public static Section findApplicable(ItemStack item) {
		for (Section tab : Section.values()) {
			if (tab.isApplicable(item)) {
				return tab;
			}
		}
		return Section.ALL;
	}
}
