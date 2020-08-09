package fr.epicorp.epicomputer.tileentity;

import java.util.UUID;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.ItemsMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityComputerCase extends TileEntityLockable implements ITickable {

	private NonNullList<ItemStack> stacks = NonNullList.withSize(9, ItemStack.EMPTY);
	private String customName;
	private String address;
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
        this.loadFromNbt(compound);
	    
	}
	 
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	    super.writeToNBT(compound);
	    return saveToNbt(compound);
	}
	
	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.isEmpty();
	}
	 
	@Override
	public String getName() {
	    return hasCustomName() ? this.customName : "tile.computer_case";
	}
	 
	public void setCustomName(String name) {
	    this.customName = name;
	}
	
	public String getAddress() {
	    return this.address;
	}
	
	public void setRandomAddress() {
		if(!hasAddress()) {
		String address = UUID.randomUUID().toString();
	    this.address = address;
		}
	}
	 
	public boolean hasAddress() {
	    return this.address != null && !this.address.isEmpty();
	}
	
	@Override
	public int getSizeInventory() {
	    return this.stacks.size();
	}
	 
	@Override
	public ItemStack getStackInSlot(int index) {
	    return this.stacks.get(index);
	}
	 
	@Override
	public ItemStack decrStackSize(int index, int count) {
	    return ItemStackHelper.getAndSplit(this.stacks, index, count);
	}
	 
	@Override
	public ItemStack removeStackFromSlot(int index) {
	    return ItemStackHelper.getAndRemove(stacks, index);
	}
	 
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    this.stacks.set(index, stack);
	 
	    if (stack.getCount() > this.getInventoryStackLimit()) {
	        stack.setCount(this.getInventoryStackLimit());
	    }
	}
	 
	@Override
	public int getInventoryStackLimit() {
	    return 1;
	}
	 
	@Override
	public boolean isEmpty() {
	    for(ItemStack stack : this.stacks) {
	        if (!stack.isEmpty()) {
	            return false;
	        }
	    }
	    return true;
	}
	 
	@Override
	public void clear() {
	    for(int i = 0; i < this.stacks.size(); i++) {
	        this.stacks.set(i, ItemStack.EMPTY);
	    }
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}
	 
	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
	    return null;
	}
	 
	@Override
	public String getGuiID() {
	    return Epicomputer.MODID + ":computer_case";
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
	    
		switch(index) {
			case 0:
				return stack.getItem() == ItemsMod.BIOS;
			case 1:
				return stack.getItem() == ItemsMod.PROCESSOR;
			case 2:
				return stack.getItem() == ItemsMod.RAM;
			case 3:
				return stack.getItem() == ItemsMod.RAM;
			case 4:
				return stack.getItem() == ItemsMod.GRAPHIC_CARD;
			case 5:
				return stack.getItem() == ItemsMod.GRAPHIC_CARD;
			case 6:
				return stack.getItem() == ItemsMod.HARD_DISK;
			case 7:
				return stack.getItem() == ItemsMod.HARD_DISK;
			case 8:
				return stack.getItem() == ItemsMod.HARD_DISK;
		}
		return false;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
	    return this.world.getTileEntity(this.pos) != this ? false : player
	            .getDistanceSq((double) this.pos.getX() + 0.5D,
	                    (double) this.pos.getY() + 0.5D,
	                    (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void update() {
	    if (!this.world.isRemote) {
	    	
	    }
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}
	
	
	public NBTTagCompound saveToNbt(NBTTagCompound compound)
    {
		ItemStackHelper.saveAllItems(compound, this.stacks);
		 
	    if (this.hasCustomName()) {
	        compound.setString("CustomName", this.customName);
	    }
	 
	    
	    if(this.hasAddress()) {
	    	compound.setString("address", this.address);
	    }
        return compound;
    }
	
	public void loadFromNbt(NBTTagCompound compound)
    {
		this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	    ItemStackHelper.loadAllItems(compound, this.stacks);
	 
	    if (compound.hasKey("CustomName")) {
	        this.customName = compound.getString("CustomName");
	    }
	    
	    if(compound.hasKey("address")) {
	    	this.address = compound.getString("address");
	    	System.out.println(this.address);
	    }
    }

	public void setAddress(String string) {
		this.address = string;
	}

}
