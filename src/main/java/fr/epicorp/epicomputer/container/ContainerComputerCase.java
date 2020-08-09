package fr.epicorp.epicomputer.container;

import fr.epicorp.epicomputer.init.ItemsMod;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerComputerCase extends Container {

	private TileEntityComputerCase tile;
	
	public ContainerComputerCase(TileEntityComputerCase tile, InventoryPlayer playerInventory) {
	    this.tile = tile;
	 this.addSlotToContainer(new SlotSingleItem(tile, 0, 113, 50,ItemsMod.BIOS ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 1, 61, 8,ItemsMod.PROCESSOR ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 2, 88, 8,ItemsMod.RAM ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 3, 106, 8,ItemsMod.RAM ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 4, 61, 39,ItemsMod.GRAPHIC_CARD ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 5, 79, 39,ItemsMod.GRAPHIC_CARD ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 6, 143, 7,ItemsMod.HARD_DISK ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 7, 143, 29,ItemsMod.HARD_DISK ));
	 this.addSlotToContainer(new SlotSingleItem(tile, 8, 143, 51,ItemsMod.HARD_DISK ));
	 
	 //this.addSlotToContainer(new SlotOutput(tile, 4, 116, 17));
	 //inventory
	 int i;
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
	 
	        
	    }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        return itemstack;
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
