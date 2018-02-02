package com.menoria.auctions.packets;

import java.io.IOException;

import com.menoria.auctions.AuctionEngine;
import com.menoria.auctions.AuctionItem;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

@Getter
public class AuctionClientPacket extends Packet{
	
private int action;
private boolean clear;
private AuctionItem item;
	
	public AuctionClientPacket() {};

	@Override
	public void readPacketData(PacketBuffer reader) throws IOException {		
		this.action = reader.readInt();
		
		if (this.action == 1) {
			this.clear = reader.readBoolean();
		}
		if (this.action == 1 || this.action == 2) {
			String id = reader.readStringFromBuffer(32767), owner = reader.readStringFromBuffer(32767);
			ItemStack stack = reader.readItemStackFromBuffer();
			double price = reader.readDouble();
			long created = reader.readLong(), expiration = reader.readLong();
			
			this.item = new AuctionItem();
			this.item.setUniqueId(id);
			this.item.setOwner(owner);
			this.item.setStack(stack);
			this.item.setPrice(price);
			this.item.setCreatedMillis(created);
			this.item.setExpirationMillis(expiration);	
		}
	}

	@Override
	public void writePacketData(PacketBuffer p_148840_1_) throws IOException {}

	@Override
	public void processPacket(INetHandler p_148833_1_) {
		AuctionEngine.getInstance().receive(this);
	}

}
