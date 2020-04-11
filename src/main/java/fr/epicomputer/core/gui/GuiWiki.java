package fr.epicomputer.core.gui;

import org.lwjgl.opengl.GL11;

import fr.epicomputer.core.EpicomputerCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiWiki extends GuiScreen{
	
	private static final ResourceLocation background = new ResourceLocation(EpicomputerCore.MODID, "textures/gui/tablet_of_help.png");
    
	int guiWidth = 400; //vers la droite
	int guiHeight = 300;// vers le haut/bas
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    
	    this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(background);
	    this.drawTexturedModalRect(0,0, 0, 0, this.width, this.height);
	 
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
