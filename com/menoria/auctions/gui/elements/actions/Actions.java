package com.menoria.auctions.gui.elements.actions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

public class Actions {

	//Connect
	public static class ActionConnect extends Action {

		private GuiScreen current;
		private String adress;
		
		public ActionConnect(GuiScreen current, String adress) {		
			this.current = current;
			this.adress = adress;
		}
		
		@Override
		public void execute() {
			this.current.getMc().displayGuiScreen(new GuiConnecting(this.current, this.current.getMc(), new ServerData("Nexion", this.adress)));
		}
	}

	//Interface
	public static class ActionOpenInterface extends Action {

		private GuiScreen target;
		
		public ActionOpenInterface(GuiScreen target) {		
			this.target = target;
		}
		
		@Override
		public void execute() {
			this.target.getMc().displayGuiScreen(this.target);
		}
	}
	
	//Link
	public static class ActionOpenLink extends Action {

		private String adress;
		
		public ActionOpenLink(String adress) {		
			this.adress = adress;
		}
		
		public void createURI(String adress) {

			URI connection = URI.create(adress);
		
			try {
				Desktop.getDesktop().browse(connection);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		
		@Override
		public void execute() {			
			this.createURI(this.adress);
		}
	}
	
	
}