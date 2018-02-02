package com.menoria.auctions.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;
import com.menoria.auctions.AuctionEngine;
import com.menoria.auctions.AuctionItem;
import com.menoria.auctions.gui.elements.ElementAuctionItem;
import com.menoria.auctions.gui.elements.ElementBlock;
import com.menoria.auctions.gui.elements.ElementButton;
import com.menoria.auctions.gui.elements.actions.Action;
import com.menoria.auctions.utils.Colors;
import com.menoria.auctions.utils.DurationFormatter;
import com.menoria.auctions.utils.Section;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiAuctionHouse extends GuiScreen {

private int page, maxPage;
private Section currentSection;
private AuctionItem current;

// CONFIG
private final int MAX_ITEM_PER_PAGE = 7;
	
	public void initGui() {
		this.reset();
		
		for(int i = 0; i < Section.values().length; i++) {
			int widthModifier =  this.getSplitWidth() - 65 + (i * 70);
			Section section = Section.values()[i];

			this.addElement(new ElementButton(new Action() {

				@Override
				public void execute() {

				}
				
			}, EnumChatFormatting.color("&f&n" + section.getLabel()), 
					widthModifier, this.getSplitHeight() - 110, 66, 22, section.getColor()));
		}
		
		this.addElement(new ElementButton(new Action() {

			@Override
			public void execute() {
				
    			if (page <= 1) return;
    			
    			page -= 1;
			}
			
		}, EnumChatFormatting.color("&c&lPrécédent"), 
				this.getSplitWidth() - 95, this.getSplitHeight() - 145, 70, 21, Colors.RED));
		this.addElement(new ElementButton(new Action() {

			@Override
			public void execute() {
				
				if (page == maxPage) return;
    			
    			page += 1;
			}
			
		}, EnumChatFormatting.color("&a&lSuivant"), 
				this.getSplitWidth() + 24, this.getSplitHeight() - 145, 70, 21, Colors.GREEN));
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawDesign(mouseX, mouseY);

		String pageTitle = EnumChatFormatting.color("&f&l"+this.page + "/" + this.maxPage);
		this.drawText(pageTitle, this.getSplitWidth() - 20 + (this.mc.fontRenderer.getStringWidth(pageTitle)) / 2, this.getSplitHeight() - 137);	
		
		if (this.current != null) {
			new ElementBlock(this.getSplitWidth() - 196, this.getSplitHeight() + 2, this.getSplitWidth() - 158, this.getSplitHeight() - 35, Colors.WHITE, 0.9F).display(mouseX, mouseY);
			
			this.addElement(new ElementButton(new Action() {

				@Override
				public void execute() {
				}
				
			}, EnumChatFormatting.color("&e&lAcheter l'objet"), this.getSplitWidth() - 251,
					this.getSplitHeight() + 118, 146, 22, Colors.WHITE));

		} else {
			this.drawText("&e&nBienvenue dans l'HDV !", this.getSplitWidth() - 230, this.getSplitHeight() - 20);
			
			this.drawText("&c• &6&oVous avez &c&o" + AuctionEngine.getInstance().getPlayerItems().size() + " &6&oitems en vente", 
					this.getSplitWidth() - 250, this.getSplitHeight() );
			
			this.drawText("&c• &6&oIl y a &c&o" + AuctionEngine.getInstance().getAuctionItems().size() +" &6&oitems dans l'HDV", 
					this.getSplitWidth() - 250, this.getSplitHeight() + 15);
		}
		
		this.renderLogo(this.getSplitWidth() - 225, this.getSplitHeight() - 140, 100, 100);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	public void displayPage() {
		this.displayPage(null);
	}
	
	public void displayPage(String searchText) {
		List<AuctionItem> items = (searchText != null ? this.searchItem(searchText) : this.getSectionItems());
        int coords = 0, size = items.size(), from = (page == 1 ? 0 : MAX_ITEM_PER_PAGE * (page - 1)), to = (page == 1 ? MAX_ITEM_PER_PAGE : (MAX_ITEM_PER_PAGE * page));
       
        for (int i = from; i < (to >= size ? size : to); i++) {
        	AuctionItem item = items.get(i);
			int heightModifier =  this.getSplitHeight() - 80 + (coords * 30);

        	this.addElement(new ElementAuctionItem(new Action() {

				@Override
				public void execute() {
				}
        		
        	}, item, this.getSplitWidth() - 95, heightModifier, 350, 25, this.getSectionItem(item).getColor()));
        	coords++;
        }
        
        this.maxPage = size <= MAX_ITEM_PER_PAGE ? 1 : (size / MAX_ITEM_PER_PAGE) + 1;
	}
	
    public void reset() {
        this.current = null;
        this.currentSection = Section.ALL;
        this.page = 1;
        this.displayPage();
    }
 

    public List<AuctionItem> searchItem(String text) {
        List<AuctionItem> result = Lists.newArrayList();
        List<AuctionItem> items = AuctionEngine.getInstance().getAuctionItems();
        
    	return result;
    }
    
    public List<AuctionItem> getSectionItems() {
        List<AuctionItem> result = Lists.newArrayList();
        List<AuctionItem> items = AuctionEngine.getInstance().getAuctionItems();
 
        if (currentSection == null || currentSection == Section.ALL) {
            return Lists.newArrayList(items);
        }
        for (AuctionItem item : items) {
            Section itemSection = this.getSectionItem(item);
            if (itemSection != this.currentSection)
                continue;
            result.add(item);
        }
        return result;
    }
    
    public Section getSectionItem(AuctionItem item) {
    	return Section.findApplicable(item.getStack());
    }
	
	public void drawDesign(int mouseX, int mouseY) {
		//Background
		new ElementBlock(this.getSplitWidth() - 260, this.getSplitHeight() - 150, this.getSplitWidth() + 260, this.getSplitHeight() + 150, Colors.GRAY, 0.8F).display(mouseX, mouseY);
		
		//Item Container
		new ElementBlock(this.getSplitWidth() - 256, this.getSplitHeight() - 146, this.getSplitWidth() - 100, this.getSplitHeight() + 146, Colors.RED, 0.8F).display(mouseX, mouseY);
		
		// seperator
		new ElementBlock(this.getSplitWidth() + 255, this.getSplitHeight() - 120, this.getSplitWidth() - 95, this.getSplitHeight() - 115, Colors.GRAY, 0.4F,false).display(mouseX, mouseY);

		new ElementBlock(this.getSplitWidth() - 250, this.getSplitHeight() - 33, this.getSplitWidth() - 105, this.getSplitHeight() - 38, Colors.GRAY, 0.4F,false).display(mouseX, mouseY);

		//Bar 
		new ElementBlock(this.getSplitWidth() + 100, this.getSplitHeight() - 145, this.getSplitWidth() + 255, this.getSplitHeight() - 124, Colors.WHITE, 0.8F).display(mouseX, mouseY);	
	}
	
}
