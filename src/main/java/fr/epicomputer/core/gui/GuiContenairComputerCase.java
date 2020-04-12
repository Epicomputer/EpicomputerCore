package fr.epicomputer.core.gui;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.blocks.ComputerCase;
import fr.epicomputer.core.blocks.ComputerCase.ComputerErrorType;
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
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.tools.nsc.doc.base.comment.Text;

public class GuiContenairComputerCase extends GuiContainer{
	
	private static final ResourceLocation background = new ResourceLocation(EpicomputerCore.MODID,"textures/gui/container/computer_gui.png");
	private TileEntityComputerCase tile;
	
	private GuiButtonPowerComputer powerButton;
	
	public ArrayList<ComputerErrorType> errorList = new ArrayList();
	
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

	    this.buttonList.add(powerButton = new GuiButtonPowerComputer(0, this.width / 2 - 50, j + 30 ));

	    this.fontRenderer.drawString(this.tile.getName(), i + 80, j + 45, 0xFFFFFF);
	}
	
	@SubscribeEvent
	protected void actionPerformed(GuiButton button) throws IOException{
			
			switch(button.id)
			{
			case 0:
				if(this.tile.getStackInSlot(1).getItem().equals(ItemsCore.BIOS) && 
				   this.tile.getStackInSlot(2).getItem().equals(ItemsCore.PROCESSOR) &&
				   this.tile.getStackInSlot(3).getItem().equals(ItemsCore.RAM) &&
				   this.tile.getStackInSlot(4).getItem().equals(ItemsCore.CARDGRAPHICS) &&
				   this.tile.getStackInSlot(5).getItem().equals(ItemsCore.HARDDISK) ){
					
					
				}else { 
					
					if (this.tile.getStackInSlot(1).getItem() == Items.AIR) {	
						errorList.add(ComputerErrorType.NO_BIOS);
					}
					if (this.tile.getStackInSlot(2).getItem() == Items.AIR) {	
						errorList.add(ComputerErrorType.NO_CPU);
					}
					if (this.tile.getStackInSlot(3).getItem() == Items.AIR) {	
						errorList.add(ComputerErrorType.NO_RAM);
					}
					if (this.tile.getStackInSlot(4).getItem() == Items.AIR) {	
						errorList.add(ComputerErrorType.NO_GRAPHICCARD);
					}
					if (this.tile.getStackInSlot(5).getItem() == Items.AIR) {	
						errorList.add(ComputerErrorType.NO_RAM);
					}
				}
				
				if (!errorList.isEmpty()){
					
					for (EntityPlayer player : this.tile.getWorld().playerEntities) {
						
						ArrayList<String> errors = new ArrayList();
						
						for (int i = 0; i < errorList.size(); i++) {
							
							errors.add(errorList.get(i).getError());
							
						}
						player.sendMessage(new TextComponentString("Error: " + String.join(",", errors)));
						
					}
					
				}
				
				break;
				
			default:
				//tu met rien ici
				break;
			}
		super.actionPerformed(button);
	}

}
