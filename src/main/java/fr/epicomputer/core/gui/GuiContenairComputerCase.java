package fr.epicomputer.core.gui;

import java.io.IOException;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.blocks.ComputerCase;
import fr.epicomputer.core.blocks.ComputerCase.ComputerState;
import fr.epicomputer.core.container.ComputerCaseContainer;
import fr.epicomputer.core.init.ItemsCore;
import fr.epicomputer.core.tiles.TileEntityComputerCase;
import ibxm.Player;
import net.minecraft.block.SoundType;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiContenairComputerCase extends GuiContainer{
	
	private static final ResourceLocation background = new ResourceLocation(EpicomputerCore.MODID,"textures/gui/container/computer_gui.png");
	private TileEntityComputerCase tile;
	
	private int indexOfClick = 0;
	
	public GuiContenairComputerCase(TileEntityComputerCase tile, InventoryPlayer playerInv) {
        super(new ComputerCaseContainer(tile, playerInv));
        this.tile = tile;
	}
	
	@Override
	   public void updateScreen()
	   {
	       super.updateScreen();
	   }
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(background);
	    this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	 
	    int timePassed = this.tile.getField(1);
	    int textureWidth = (int) (23f / 200f * timePassed);
	    this.drawTexturedModalRect(i + 81, j + 24, 177, 18, textureWidth, 7);
	 
	    if (this.tile.isBurning()) {
	        int burningTime = this.tile.getField(0);
	        int textureHeight = (int) (12f / this.tile.getFullBurnTime() * burningTime);
	        this.drawTexturedModalRect(i + 37, j + 26 + 12 - textureHeight,
	                177, 12 - textureHeight, 27, textureHeight);
	    }

	    this.buttonList.add(new GuiButtonPowerComputer(0, this.width / 2 - 50, j + 30 ));

	    this.fontRenderer.drawString(this.tile.getName(), i + 80, j + 45, 0xFFFFFF);
	}
	

	@SubscribeEvent
	protected void actionPerformed(GuiButton button) throws IOException{
			
			switch(button.id)
			{
			case 0:
					
				ComputerCase.setState(ComputerState.ON, this.tile.getWorld(), this.tile.getPos());
				
				break;
				
			default:
				//tu met rien ici
				break;
			}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch(button.id)
		{
		case 0:
			//le bouton power
			
			break;
			
		default:
			//tu met rien ici
			break;
		}
		

		super.actionPerformed(button);
	}

}
