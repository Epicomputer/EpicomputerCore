package fr.epicorp.epicomputer.client.gui;

import java.io.IOException;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.client.button.GuiButtonPowerComputer;
import fr.epicorp.epicomputer.container.ContainerComputerCase;
import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiContenairComputerCase extends GuiContainer{


	private static final ResourceLocation background = new ResourceLocation(Epicomputer.MODID,"textures/gui/container/computer_gui.png");
	private TileEntityComputerCase tile;
	private ItemStack stack = new ItemStack(Item.getItemFromBlock(BlocksMod.computer_case));
	
	private GuiButtonPowerComputer powerButton;
	
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
		String s = this.tile.getAddress();
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
				
				EntityPlayer joueur = Minecraft.getMinecraft().player;
				if (this.tile.getComputerState() != ComputerState.ON) {
					if(this.tile.getStackInSlot(1).getItem().equals(ItemsCore.BIOS) && 
							   this.tile.getStackInSlot(2).getItem().equals(ItemsCore.PROCESSOR) &&
							   this.tile.getStackInSlot(3).getItem().equals(ItemsCore.RAM) &&
							   this.tile.getStackInSlot(5).getItem().equals(ItemsCore.CARDGRAPHICS) &&
							   this.tile.getStackInSlot(7).getItem().equals(ItemsCore.HARDDISK) )
					{
						
						if(antiSpamSound == false) {
								ComputerCase.setState(ComputerState.BOOT, this.tile.getWorld(), this.tile.getPos());
								
								
								new Thread("computer-" + this.tile.getAddress()) {
									
									@Override
									public void run() {
										
										try {
											
											this.sleep(1500L);
											
											joueur.playSound(new SoundEvent(new ResourceLocation("ecore:computer_startup")), 1.0F, 1.0F);
											antiSpamSound = true;
											ComputerCase.setState(ComputerState.ON, tile.getWorld(), tile.getPos());
											//TileEntityComputerCase.setNBT(tile.getWorld(), tile.getPos());
											onEveryday();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									
								}.start();
								}
								
									
								
									
								
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
								if (this.tile.getStackInSlot(5).getItem() == Items.AIR) {	
									errorList.add(ComputerErrorType.NO_GRAPHICCARD);
								}
								if (this.tile.getStackInSlot(7).getItem() == Items.AIR) {	
									errorList.add(ComputerErrorType.NO_HARDDISK);
								}
								start = false;
							}
							
							if (!errorList.isEmpty()){
								
								for (EntityPlayer player : this.tile.getWorld().playerEntities) {
									
									ArrayList<String> errors = new ArrayList();
									
									for (int i = 0; i < errorList.size(); i++) {
										
										errors.add(errorList.get(i).getError());
										
									}
									player.sendMessage(new TextComponentString("Error: " + String.join(",", errors)));
									
									ComputerCase.setState(ComputerState.ERROR, this.tile.getWorld(), this.tile.getPos());
									
									System.out.println("ERROR");
									
									errors.clear();
									errorList.clear();
									
								}
								
							}
				}else{
					
					if (this.tile.getComputerthread() != null) {
						this.tile.getComputerthread().stop();
					}
				}
				
				break;
				
			default:
				//tu met rien ici
				break;
			}
			
			
			
		super.actionPerformed(button);
	}
	
	public static void onEveryday() {
		//ComputerCase.setState(ComputerState.ON, tile.getWorld(), tile.getPos());
		new Thread("start") {
			
			@Override
			public void run() {
				
				try {
					if (start == true) {
					this.sleep(1L);
					
					//BlockComputerCase.setState(ComputerState.ON, tile.getWorld(), tile.getPos());
					//TileEntityComputerCase.setNBT(tile.getWorld(), tile.getPos());
					this.start();
					}else if (start = false){
						antiSpamSound = false;
						start = false;
						this.stop();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}.start();
	}
	
	public boolean doesGuiPauseGame(){
        return false;
    }

}
