package fr.epicorp.epicomputer.client.button;

import fr.epicorp.epicomputer.Epicomputer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonPowerComputer extends GuiButton {
	
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Epicomputer.MODID, "textures/gui/power_button.png");
    

	public GuiButtonPowerComputer(int buttonID, int xPos, int yPos, String text)
    {
        super(buttonID, xPos, yPos, 20, 20, text);
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
		EntityPlayer player = Minecraft.getMinecraft().player;
		player.playSound(new SoundEvent(new ResourceLocation("ecore:computer_button")), 3.0F, 1.0F);
		
	}
	
}
