package com.menoria.auctions.gui;

import java.util.concurrent.TimeUnit;

import com.google.common.primitives.Doubles;
import com.menoria.auctions.AuctionItem;
import com.menoria.auctions.gui.elements.ElementBlock;
import com.menoria.auctions.gui.elements.ElementButton;
import com.menoria.auctions.gui.elements.ElementItem;
import com.menoria.auctions.gui.elements.actions.Action;
import com.menoria.auctions.gui.elements.ElementTextBox;
import com.menoria.auctions.gui.elements.ElementTextBox.TextUpdate;
import com.menoria.auctions.packets.AuctionServerPacket;
import com.menoria.auctions.utils.Colors;
import com.menoria.auctions.utils.DurationFormatter;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GuiSellItem extends GuiScreen {

private AuctionItem item;
private int time = 1;	
private ElementTextBox textBox;

	public GuiSellItem(AuctionItem item) {
		this.item=item;
	}
	
	public void initGui() {		
		this.addElement(new ElementButton(new Action() {

			@Override
			public void execute() {

			}
			
		}, EnumChatFormatting.color("&a&lValider"), 
				this.getSplitWidth() - 74, this.getSplitHeight() + 90, 148, 22, Colors.GREEN));
		
		this.addElement(new ElementButton(new Action() {

			@Override
			public void execute() {
				// sell
    			String text = textBox.getTextField().getText();
    			Double price = Doubles.tryParse(text);
    			if (text == null || price == null || price <= 0) {
    				textBox.getTextField().setText("! ! ! SOMME INVALIDE ! ! !");
    				return;
    			}
    			if (time == -1) {
    				time = 1;
    			}

    			item.setPrice(price);	
    			item.setExpirationMillis(item.getCreatedMillis() + TimeUnit.HOURS.toMillis(time));
    			mc.thePlayer.sendQueue.addToSendQueue(new AuctionServerPacket(AuctionServerPacket.SELL, item));
    			mc.displayGuiScreen((GuiScreen) null);
			}
			
		}, EnumChatFormatting.color("&c&lAnnuler"), this.getSplitWidth() - 74, 
				this.getSplitHeight() + 118, 148, 22, Colors.RED));
		
		this.addElement(textBox = new ElementTextBox("...", 32, false, 
				this.getSplitWidth() - 70, this.getSplitHeight() + 65, 140, 18, null));
		
		// time
		for (int i = 0; i < 5; i++) {
			int widthModifier =  this.getSplitWidth() - 60 + (i * 25);
			int hours = (i * 2) + 1;
			
			this.addElement(new ElementButton(new Action() {

				@Override
				public void execute() {
					// cancel
	    			mc.displayGuiScreen((GuiScreen) null);
				}
				
			}, EnumChatFormatting.color("&f&l" + hours +"h"), 
					widthModifier, this.getSplitHeight() + 25, 23, 22, Colors.BLUE));
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		//Item Container
		new ElementBlock(this.getSplitWidth() + 80, this.getSplitHeight() - 146, this.getSplitWidth() - 80, this.getSplitHeight() + 146, Colors.RED, 0.8F).display(mouseX, mouseY);
		
		//Item Slot
		new ElementBlock(this.getSplitWidth() - 20, this.getSplitHeight() + 2, this.getSplitWidth() + 20, this.getSplitHeight() - 35, Colors.WHITE, 0.9F).display(mouseX, mouseY);
		new ElementItem(this.item.getStack(), this.getSplitWidth() - 8, this.getSplitHeight() - 25).display(mouseX, mouseY);
		
		//Texts
		this.drawText("&7&oVeuillez entrer un prix", this.getSplitWidth() - 72, this.getSplitHeight() + 53);
		this.drawText("&cTemps de vente &4» &6"+ this.time + " h", this.getSplitWidth() - 50, this.getSplitHeight() + 10);
		
		this.renderLogo(this.getSplitWidth() - 50, this.getSplitHeight() - 140, 100, 100);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
