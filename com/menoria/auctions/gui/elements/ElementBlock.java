package com.menoria.auctions.gui.elements;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.menoria.auctions.utils.Colors;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

@Getter
public class ElementBlock extends Element {

	private Colors color;
	private float transparency;
	private boolean borders;

	public ElementBlock(int posX, int posY, int width, int height, Colors color, float transparency) {
		super(posX, posY, width, height);
		this.color = color;
		this.transparency = transparency;
		this.borders = true;
	}
	
	public ElementBlock(int posX, int posY, int width, int height, Colors color, float transparency, boolean borders) {
		super(posX, posY, width, height);
		this.color = color;
		this.transparency = transparency;
		this.borders = borders;
	}

	public void display(int posX, int posY) {
		this.displayBlockRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), this.color, this.transparency);
	}
	
	public void displayBlockRect(int posX, int posY, int width, int height, Colors color, float transparency) {
		
		int modifier;

		if (posX < width) {
			modifier = posX;
			posX = width;
			width = modifier;
		}

		if (posY < height) {
			modifier = posY;
			posY = height;
			height = modifier;
		}

		Tessellator tess = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		this.setGlColor(color.getDarkColor(), transparency);

		// Background
		tess.startDrawingQuads();
		tess.addVertex((double) posX, (double) height, 0.0D);
		tess.addVertex((double) width, (double) height, 0.0D);
		tess.addVertex((double) width, (double) posY, 0.0D);
		tess.addVertex((double) posX, (double) posY, 0.0D);
		tess.draw();

		GL11.glColor4f(0.0F, 0.0F, 0.0F, transparency);
		GL11.glDisable(GL11.GL_BLEND);

		if(this.borders) {
			
			// Bordures
			tess.startDrawingQuads();
			tess.addVertex((double) posX + 1, (double) height, 0.1D);
			tess.addVertex((double) posX, (double) height, 0.1D);
			tess.addVertex((double) posX, (double) posY, 0.1D);
			tess.addVertex((double) posX + 1, (double) posY, 0.1D);
			tess.draw();

			tess.startDrawingQuads();
			tess.addVertex((double) posX + 1, (double) posY, 0.1D);
			tess.addVertex((double) width, (double) posY, 0.1D);
			tess.addVertex((double) width, (double) posY + 1, 0.1D);
			tess.addVertex((double) posX + 1, (double) posY + 1, 0.1D);
			tess.draw();

			tess.startDrawingQuads();
			tess.addVertex((double) posX, (double) height, 0.1D);
			tess.addVertex((double) width, (double) height, 0.1D);
			tess.addVertex((double) width, (double) height + 1, 0.1D);
			tess.addVertex((double) posX, (double) height + 1, 0.1D);
			tess.draw();

			tess.startDrawingQuads();
			tess.addVertex((double) width + 1, (double) height, 0.1D);
			tess.addVertex((double) width, (double) height, 0.1D);
			tess.addVertex((double) width, (double) posY, 0.1D);
			tess.addVertex((double) width + 1, (double) posY, 0.1D);
			tess.draw();
		}

		this.drawGradientRect(posX, posY, width, height, Integer.MIN_VALUE, 0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
