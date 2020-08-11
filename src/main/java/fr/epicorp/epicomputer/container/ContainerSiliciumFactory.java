package fr.epicorp.epicomputer.container;


import fr.epicorp.epicomputer.init.ItemsMod;
import fr.epicorp.epicomputer.tileentity.TileEntitySiliciumFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerSiliciumFactory extends Container{
	
	private TileEntitySiliciumFactory tile;
	private int	timePassed = 0;
	private int	burnTimeLeft = 0;


	public ContainerSiliciumFactory(TileEntitySiliciumFactory tile, InventoryPlayer playerInventory) {
		this.tile = tile;
		 
	    int i;
	        this.addSlotToContainer(new SlotSingleItem(tile, 0, 53, 28 , ItemsMod.SILICIUM));
	    
	    this.addSlotToContainer(new SlotOutput(tile, 1, 111, 28));
	 
	    for(i = 0; i < 3; ++i) {
	        for(int j = 0; j < 9; ++j) {
	            this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	        }
	    }
	 
	    for(i = 0; i < 9; ++i) {
	        this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
	    }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
	    return tile.isUsableByPlayer(player);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
	    super.addListener(listener);
	    listener.sendAllWindowProperties(this, this.tile);
	}
	 
	@Override
	public void detectAndSendChanges() {
	    super.detectAndSendChanges();
	 
	    for(int i = 0; i < this.listeners.size(); ++i) {
	        IContainerListener icontainerlistener = (IContainerListener) this.listeners
	                .get(i);
	 
	        if (this.burnTimeLeft != this.tile.getField(0)) {
	            icontainerlistener.sendWindowProperty(this, 0,
	                    this.tile.getField(0));
	        }
	 
	        if (this.timePassed != this.tile.getField(1)) {
	            icontainerlistener.sendWindowProperty(this, 1,
	                    this.tile.getField(1));
	        }
	    }
	 
	    this.burnTimeLeft = this.tile.getField(0);
	    this.timePassed = this.tile.getField(1);
	}
	 
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
	    this.tile.setField(id, data);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
	    return ItemStack.EMPTY;
	}
	
	public class SlotOutput extends Slot {
		 
	    public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
	        super(inventoryIn, index, xPosition, yPosition);
	    }
	 
	    @Override
	    public boolean isItemValid(ItemStack stack) {
	        return false;
	    }
	}
	
	public class SlotSingleItem extends Slot {
		 
	    private Item item;
	 
	    public SlotSingleItem(IInventory inventoryIn, int index, int xPosition, int yPosition, Item item) {
	        super(inventoryIn, index, xPosition, yPosition);
	        this.item = item;
	    }
	 
	    @Override
	    public boolean isItemValid(ItemStack stack) {
	        return stack.isEmpty() || stack.getItem() == item;
	    }
	}

}
