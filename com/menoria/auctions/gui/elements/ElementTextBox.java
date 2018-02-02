package com.menoria.auctions.gui.elements;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

@Getter
public class ElementTextBox extends Element{

private int width, height;
private GuiTextField textField;
private TextUpdate onTextUpdate;
private String defaultText;
private boolean hideContent;

		public ElementTextBox(String defaultText, int posX, int posY, int width, int height, TextUpdate onTextUpdate) {
			this(defaultText, 120, false, posX, posY, width, height, onTextUpdate);
		}
		
		public ElementTextBox(String defaultText, int maxChar, boolean hideContent, int posX, int posY, int width, int height, TextUpdate onTextUpdate) {
			super(posX, posY, width, height);
			
			this.defaultText = defaultText;
			this.width = width; 
			this.height = height;
	        this.textField = new GuiTextField(Minecraft.getMinecraft().fontRenderer, posX, posY, width, height, hideContent, defaultText);
	        this.textField.setFocused(false);
	        this.textField.func_146203_f(maxChar);
	        this.onTextUpdate = onTextUpdate;
		}
		
		public static abstract class TextUpdate {
			public abstract void onTextUpdate(String text);
		}

		@Override
		public void display(int posX, int posY) {
			this.textField.drawTextBox();
		}

}
