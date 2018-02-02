package com.menoria.auctions.packets;

import java.io.IOException;

import com.menoria.auctions.AuctionItem;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class AuctionServerPacket extends Packet{
	
public final static int SELL = 0, BUY = 1, GET = 2;
private int action;
private AuctionItem item;
	
	public AuctionServerPacket() {};
	
	public AuctionServerPacket(int action, AuctionItem item) {
		this.action=action;
		this.item=item;
	}

	@Override
	public void readPacketData(PacketBuffer p_148837_1_) throws IOException {}

	@Override
	public void writePacketData(PacketBuffer buffer) throws IOException {
		buffer.writeStringToBuffer(Minecraft.getMinecraft().thePlayer.getCommandSenderName()); // we need see, custom packet
		buffer.writeInt(action);
		buffer.writeStringToBuffer(item.getUniqueId());
		buffer.writeStringToBuffer(item.getOwner());
		buffer.writeItemStackToBuffer(item.getStack());
		buffer.writeDouble(item.getPrice());
		buffer.writeLong(item.getCreatedMillis());
		buffer.writeLong(item.getExpirationMillis());
	}

	@Override
	public void processPacket(INetHandler p_148833_1_) {}

}
