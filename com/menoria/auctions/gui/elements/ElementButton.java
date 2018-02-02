package com.menoria.auctions.gui.elements;

import com.menoria.auctions.gui.elements.actions.Action;
import com.menoria.auctions.utils.Colors;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

@Getter
public class ElementButton extends Element {

	private Action action;
	private String title;
	private Colors color; 
	
	public ElementButton(Action action, String title, int posX, int posY, int width, int height, Colors color) {
		super(posX, posY, width, height);
		this.action = action;
		this.title = title;
		this.color = color;
	}

	@Override
	public void display(int posX, int posY) {
		Colors buttonColor = !this.isActive() ? this.color : (this.isHovered(posX, posY) ? this.color : Colors.GRAY);	
		new ElementBlock(this.getPosX(), this.getPosY(), this.getPosX() + this.getWidth(), this.getPosY() + this.getHeight(), buttonColor, 1.0F).display(posY, posY);		
		this.modifiers();
	}
	
	public void modifiers() {
		this.getMinecraft().fontRenderer.drawStringWithShadow(this.title, this.getPosX() + 1 + (this.getWidth() - this.getMinecraft().fontRenderer.getStringWidth(this.title)) / 2, this.getPosY() + (this.getHeight() - 8) / 2, this.color.getDisableColor());
	}
}