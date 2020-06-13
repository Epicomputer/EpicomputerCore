package fr.epicomputer.core.gui;

import org.lwjgl.opengl.GL11;

import fr.epicomputer.core.EpicomputerCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiWiki extends GuiScreen{
	
	private static final ResourceLocation background = new ResourceLocation(EpicomputerCore.MODID, "textures/gui/tablet_of_help.png");
    
	int guiWidth = 464; //vers la droite
	int guiHeight = 256;// vers le haut/bas
	
	
	public void drawScreen(int x, int y, float ticks)
    { 

        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        //this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(background);
<<<<<<< HEAD
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
=======
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
>>>>>>> 8828bc07da5042f23e405c965f90f11d0cfb092f
        this.drawTexturedModalRect(guiX,guiY, 0,0, guiWidth, guiHeight);
        
		
        super.drawScreen(x, y, ticks);
    }

	@Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
