package com.menoria.auctions.utils;

import java.awt.Color;

import lombok.Getter;

@Getter
public enum Colors {

	GRAY(new Color(118, 120, 119).getRGB(), new Color(91, 94, 94).getRGB(), new Color(177, 182, 181).getRGB()), 
	
	GREEN(new Color(116, 233, 108).getRGB(), new Color(86, 186, 79).getRGB(), new Color(177, 182, 181).getRGB()),
	ORANGE(new Color(233, 179, 30).getRGB(), new Color(196, 144, 0).getRGB(), new Color(177, 182, 181).getRGB()),
	YELLOW(new Color(250, 235, 26).getRGB(), new Color(216, 202, 0).getRGB(), new Color(177, 182, 181).getRGB()),
	BLUE(new Color(31, 174, 236).getRGB(), new Color(14, 133, 183).getRGB(), new Color(177, 182, 181).getRGB()),
	
	WHITE(new Color(254, 254, 254).getRGB(), new Color(230, 230, 230).getRGB(), new Color(177, 182, 181).getRGB()), 
	RED(new Color(212, 58, 68).getRGB(), new Color(199, 66, 75).getRGB(), new Color(177, 182, 181).getRGB());
	
	private int lightColor, darkColor, disableColor;

	Colors(int lightColor, int darkColor, int disableColor) {
		this.lightColor = lightColor;
		this.darkColor = darkColor;
		this.disableColor = disableColor;
	}
}
