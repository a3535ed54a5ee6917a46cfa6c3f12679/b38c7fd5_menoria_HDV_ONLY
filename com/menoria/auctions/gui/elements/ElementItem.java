package com.menoria.auctions.gui.elements;

import org.lwjgl.opengl.GL11;

import com.menoria.auctions.AuctionItem;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

@Getter
public class ElementItem extends Element {

	private ItemStack item;
	
	public ElementItem(ItemStack item, int posX, int posY) {
		super(posX, posY, 16, 16);
		this.item = item;
	}
	
	public void renderItem(ItemStack item, int x, int y, boolean overlay) {
		RenderItem itemRenderer = new RenderItem();
		RenderHelper.enableGUIStandardItemLighting();	
		itemRenderer.renderItemIntoGUI(this.getMinecraft().fontRenderer, this.getMinecraft().getTextureManager(), item, x, y);
		
       if (overlay) {
       	itemRenderer.renderItemOverlayIntoGUI(this.getMinecraft().fontRenderer, this.getMinecraft().getTextureManager(), item, x, y);
       }    
       RenderHelper.disableStandardItemLighting();   
   }
	
	@Override
	public void display(int posX, int posY) {
		this.renderItem(item, this.getPosX(), this.getPosY(), true);		
		if(item != null && this.isHovered(posX, posY)) {			
			//Debug
			this.getMinecraft().currentScreen.func_146285_a(item, posX - 130, posY);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        RenderHelper.disableStandardItemLighting();
		}
	}

}
