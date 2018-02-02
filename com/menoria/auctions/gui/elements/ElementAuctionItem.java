package com.menoria.auctions.gui.elements;

import com.menoria.auctions.AuctionItem;
import com.menoria.auctions.gui.elements.actions.Action;
import com.menoria.auctions.utils.Colors;
import com.menoria.auctions.utils.DurationFormatter;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

@Getter @Setter
public class ElementAuctionItem extends ElementButton {

	private AuctionItem item;

	public ElementAuctionItem(Action action, AuctionItem item, int posX, int posY, int width, int height, Colors color) {
		super(action, "", posX, posY, width, height, color);
		this.item = item;
	}

	@Override
	public void display(int posX, int posY) {	
		super.display(posX, posY);
		
		//Item Slot
	    new ElementBlock(this.getPosX() + 2, this.getPosY() + 2, this.getPosX() + 22, this.getPosY() + (this.getHeight() - 2), Colors.WHITE, 0.9F).display(posX, posY);
	    
	    //Item
		new ElementItem(this.item.getStack(), this.getPosX() + 5, this.getPosY() + 4).display(posX, posY);
	}

	public void modifiers() {
		//Title
		this.getMinecraft().fontRenderer.drawStringWithShadow(EnumChatFormatting.color("&c• &f"+this.item.getStack().getDisplayName() + " &7- &e" + this.item.getPrice() +"$"), 
				this.getPosX() + 25, this.getPosY() + 4, this.getColor().getDisableColor());
		
		//Price
		this.getMinecraft().fontRenderer.drawStringWithShadow(EnumChatFormatting.color("&7&o" + this.item.getOwner()), this.getPosX() + 25, this.getPosY() + 14, this.getColor().getDisableColor());
		//Created
		String expiration = this.item.hasExpired() ? EnumChatFormatting.color("&c✖ Indisponible") : EnumChatFormatting.color("&6Expiration de l'item dans &c"
				+ DurationFormatter.getRemaining(this.item.getExpirationMillis() - System.currentTimeMillis(), true));
		
		this.getMinecraft().fontRenderer.drawStringWithShadow(expiration, this.getPosX()  + this.getWidth() - this.getMinecraft().fontRenderer.getStringWidth(expiration) - 4, this.getPosY() + 4, this.getColor().getDisableColor());
	
		String creation = EnumChatFormatting.color("&7&o" + DurationFormatter.getDurationDate(this.item.getCreatedMillis()));
		this.getMinecraft().fontRenderer.drawStringWithShadow(creation, this.getPosX()  + this.getWidth() - this.getMinecraft().fontRenderer.getStringWidth(creation) - 4, this.getPosY() + 14, this.getColor().getDisableColor());
		
	}
}
