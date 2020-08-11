package fr.epicorp.epicomputer.client.gui;

import java.io.IOException;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.blocks.BlockComputerCase;
import fr.epicorp.epicomputer.client.button.GuiButtonPowerComputer;
import fr.epicorp.epicomputer.container.ContainerComputerCase;
import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.init.ItemsMod;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiContenairComputerCase extends GuiContainer{


	private static final ResourceLocation background = new ResourceLocation(Epicomputer.MODID,"textures/gui/container/computer_gui.png");
	private TileEntityComputerCase tile;
	private ItemStack stack = new ItemStack(Item.getItemFromBlock(BlocksMod.computer_case));
	
	private GuiButtonPowerComputer powerButton;
	
	public boolean threadRunning = false;
	public boolean error = false;
	
	//public ArrayList<ComputerErrorType> errorList = new ArrayList();
	
	
	public GuiContenairComputerCase(TileEntityComputerCase tile, InventoryPlayer playerInv) {
        super(new ContainerComputerCase(tile, playerInv));
        this.tile = tile;
        
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
		//String s = this.tile.getAddress();
        //this.fontRenderer.drawString("Address : " + this.stack.getTagCompound().getString("address"), this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 0xFFFFFF);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.mc.getTextureManager().bindTexture(background);
	    this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		
	    this.buttonList.clear();
	    this.buttonList.add(new GuiButtonPowerComputer(0, this.width / 2 - 87, j + 10, "Turn on the computer"));

	    
	}
	public static boolean start = false;
	public static boolean antiSpamSound = false;
	@SubscribeEvent
	protected void actionPerformed(GuiButton button) throws IOException{
			
			switch(button.id)
			{
			case 0:
				
				if (this.threadRunning) {
					this.threadRunning = false;
				}
				
				if (this.error) {
					this.error = false;
				}
				
				tile.computerThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
					
						if (tile.getBlockType() instanceof BlockComputerCase) {
							
							BlockComputerCase computer = (BlockComputerCase) tile.getBlockType();
							
							tile.getWorld().setBlockState(tile.getPos(), computer.getStateFromMeta(BlockComputerCase.EnumType.COMPUTER_BOOT.getMetadata()));
							
							try {
								tile.computerThread.sleep(1500);
							} catch (InterruptedException e) {
								error(computer, true, tile.getAddress());
							}
							
							tile.getWorld().setBlockState(tile.getPos(), computer.getStateFromMeta(BlockComputerCase.EnumType.COMPUTER_ON.getMetadata()));
							
							while (threadRunning) {
								
								error(computer, true, tile.getAddress());
								
							}
							
							tile.computerThread.stop();
						}
						
					}
					
				}, "ComputerThread-" + tile.getAddress());
				
				tile.computerThread.run();
				
				break;
				
			default:
				break;
			}
			
			
			
		super.actionPerformed(button);
	}
	
	public void error(BlockComputerCase computer, boolean error, String uuid) {
		
		this.threadRunning = false;
		this.error = error;
		
		while(this.error) {
			
			Thread thread = null;
			
			thread = new Thread(new Runnable() {
				
				@Override
				public void run() {

					tile.getWorld().setBlockState(tile.getPos(), computer.getStateFromMeta(BlockComputerCase.EnumType.COMPUTER_OFF.getMetadata()));
					
					try {
						thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					tile.getWorld().setBlockState(tile.getPos(), computer.getStateFromMeta(BlockComputerCase.EnumType.COMPUTER_ERROR.getMetadata()));

					try {
						thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, "ErrorThread-" + uuid);
		
			thread.interrupt();
			
		}
		
		
	}
	
	public boolean doesGuiPauseGame(){
        return false;
    }

}
