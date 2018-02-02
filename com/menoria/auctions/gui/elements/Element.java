package com.menoria.auctions.gui.elements;

import org.lwjgl.opengl.GL11;

import com.menoria.auctions.utils.Colors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

@Getter @Setter @EqualsAndHashCode(callSuper=false, exclude={"minecraft"})
public abstract class Element extends GuiScreen {

	private Minecraft minecraft = Minecraft.getMinecraft();
	private int posX, posY, width, height;
	private boolean visible, active;
	
	public Element(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.visible = true;
		this.active = true;
	}
	
	public boolean isHovered(int posX, int posY) {
		return posX >= this.posX && posY >= this.posY && posX < this.posX + this.width && posY < this.posY + this.height;
	}

	public boolean isPressed(int posX, int posY) {
		return this.active && posX >= this.posX && posY >= this.posY && posX < this.posY + this.width && posY < this.posY + this.height;
	}

	public abstract void display(int posX, int posY);
	
	public void setGlColor(int color, float transparency) {
		float red = (float) (color >> 16 & 255) / 255.0F;
		float blue = (float) (color >> 8 & 255) / 255.0F;
		float green = (float) (color & 255) / 255.0F;
		GL11.glColor4f(red, blue, green, transparency);
	}
}
