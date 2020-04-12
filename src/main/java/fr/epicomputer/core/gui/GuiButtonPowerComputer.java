package fr.epicomputer.core.gui;

import fr.epicomputer.core.EpicomputerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonPowerComputer extends GuiButton {
	
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(EpicomputerCore.MODID, "textures/gui/power_button.png");
    

	public GuiButtonPowerComputer(int buttonID, int xPos, int yPos)
    {
        super(buttonID, xPos, yPos, 30, 30, "");
    }
	
	
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = 0;

            if (flag)
            {
                i += this.height;
            }

            this.drawTexturedModalRect(this.x, this.y, 0, 0, this.width, this.height);
        }
    }

	public void playPressSound(SoundHandler soundHandlerIn){
		soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_ANVIL_BREAK, 1.0F));
	}
	
}
