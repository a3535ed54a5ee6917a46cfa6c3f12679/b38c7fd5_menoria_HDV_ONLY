package com.menoria.auctions;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.menoria.auctions.gui.GuiAuctionHouse;
import com.menoria.auctions.gui.GuiSellItem;
import com.menoria.auctions.packets.AuctionClientPacket;

import lombok.Getter;
import net.minecraft.client.Minecraft;

@Getter
public class AuctionEngine {
	
private static @Getter AuctionEngine instance;
private List<AuctionItem> auctionItems = Lists.newArrayList();
public static final Integer PACKET_ID = 100;

	public AuctionEngine() {
		instance=this;
		
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new AuctionCleannerRunnable(), 1L, 1L, TimeUnit.SECONDS);
	}
	
	public List<AuctionItem> getPlayerItems() {
		List<AuctionItem> result = Lists.newArrayList();
		for (int i = 0; i < this.auctionItems.size(); i++) {
			AuctionItem item = this.auctionItems.get(i);
			if (item.isOwner()) {
				result.add(item);
			}
		}
		return result;
	}
	
	private Comparator<AuctionItem> COMPARATOR = new Comparator<AuctionItem>() {
		@Override
		public int compare(AuctionItem a1, AuctionItem a2) {
			return -Long.compare(a1.getCreatedMillis(), a2.getCreatedMillis());
		}
	};
	
	private final int OPEN = 0, COLLECT = 1, SELL = 2;
	
	public void receive(AuctionClientPacket packet) {
		int action = packet.getAction();
		
		switch (action) {
		
		case OPEN:
			Minecraft.getMinecraft().displayGuiScreen(new GuiAuctionHouse());
			break;
		case COLLECT:
			boolean clear = packet.isClear();
			if (clear) {
				this.auctionItems.clear();
			}
			
			this.auctionItems.add(packet.getItem());
			Collections.sort(this.auctionItems, COMPARATOR);
			break;
		case SELL:
			Minecraft.getMinecraft().displayGuiScreen(new GuiSellItem(packet.getItem()));
			break;		
		}
	}
	
	public static void debug(String message, Object... args) {
		System.out.println("[Auction] "+String.format(message, args));
	}
}
