package fr.epicomputer.core.gui;

import org.lwjgl.opengl.GL11;

import fr.epicomputer.core.EpicomputerCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiWiki extends GuiScreen{
	
	private static final ResourceLocation background = new ResourceLocation(EpicomputerCore.MODID, "textures/gui/tablet_of_help.png");
    
	int guiWidth = 480; //vers la droite
	int guiHeight = 128;// vers le haut/bas
	
	
	public void drawScreen(int x, int y, float ticks)
    { 
<<<<<<< HEAD
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        //this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(background);
        GL11.glColor4f(1, 1, 1, 1);
        this.drawTexturedModalRect(guiX,guiY, 0, 0, guiWidth, guiHeight);
        
        
        super.drawScreen(x, y, ticks);
    }
	
=======
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
	    //this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(background);
	    GL11.glColor4f(1, 1, 1, 1);
	    this.drawTexturedModalRect(guiX,guiY, 0, 0, guiWidth, guiHeight);
	    
	    
	    super.drawScreen(x, y, ticks);
	}
>>>>>>> 07ad03ffd545179cc7001220245dab32ff11ea2c
	@Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
