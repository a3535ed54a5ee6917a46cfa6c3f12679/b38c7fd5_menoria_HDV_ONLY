package com.menoria.auctions;

import lombok.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

@Data
public class AuctionItem {
	
private String uniqueId, owner;
private ItemStack stack;
private double price;
private long createdMillis, expirationMillis;

	public AuctionItem() {}

	public boolean hasExpired() {
		return this.expirationMillis - System.currentTimeMillis() <= 0L;
	}
	
	public boolean isOwner() {
		return Minecraft.getMinecraft().thePlayer.getCommandSenderName().equalsIgnoreCase(this.owner);
	}
	
}
