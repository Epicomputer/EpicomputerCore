package fr.epicorp.epicomputer.client;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.container.ContainerSiliciumFactory;
import fr.epicorp.epicomputer.tileentity.TileEntitySiliciumFactory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContenairSiliciumFactory extends GuiContainer{
	
	private static final ResourceLocation background = new ResourceLocation(Epicomputer.MODID,"textures/gui/container/silicium_gui.png");
	private TileEntitySiliciumFactory tile;

	public GuiContenairSiliciumFactory(TileEntitySiliciumFactory tile, InventoryPlayer playerInv) {
		super(new ContainerSiliciumFactory(tile, playerInv));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(background);
	    this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	 
	    //fleche
	    int timePassed = this.tile.getField(1);
	    int textureWidth = (int) (31f / 200f * timePassed);
	    this.drawTexturedModalRect(i + 77, j + 28, 176, 0, textureWidth, 17);
	 
	    //presseurs
	    if (this.tile.isBurning()) {
	        int burningTime = this.tile.getField(0);
	        int textureHeight = (int) (16f / this.tile.getFullBurnTime() * burningTime);
	        int textureHeight2 = (int) (16f / this.tile.getFullBurnTime() * burningTime);
	        this.drawTexturedModalRect(i + 53, j + 5 /*- textureHeight*/,
	        							176, 18 /*+ textureHeight*/,
	        							16, 15 -textureHeight);
	        
	        this.drawTexturedModalRect(i + 53, j + 53,
					176, 35 /*+ textureHeight*/,
					16,  textureHeight2);
	    }
	 
	    this.fontRenderer.drawString(this.tile.getName(), i + 84, j + 7, 0x565656);
	}

}
