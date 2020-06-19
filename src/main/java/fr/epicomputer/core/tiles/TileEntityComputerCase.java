package fr.epicomputer.core.tiles;

import java.util.Random;

import fr.epicomputer.core.blocks.ComputerCase;
import fr.epicomputer.core.recipes.RecipesComputerCase;
import fr.epicomputer.core.tiles.TileEntityComputerCase.ComputerState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityComputerCase extends TileEntityLockable implements ITickable {
	
	public static String NAME = null;
	private NonNullList<ItemStack> stacks = NonNullList.withSize(10, ItemStack.EMPTY);
	private String customName;
	private int	timePassed = 0;
	private int	burningTimeLeft	= 0;
	private String address = "";
	
	public ComputerState state;
	public Thread computerthread;
	public TileEntityComputerCase tile;
	public String tileType = "";
	public static String lastAddress = "";
	
	public enum ComputerErrorType{
		
		NO_BIOS(0, " BIOS element not found"),
		NO_CPU(1, " CPU element not found"),
		NO_RAM(2, " RAM element not found"),
		NO_RAM_2(3, " RAM element not found"),
		NO_GRAPHICCARD(4, " Graphic Card element not found"),
		NO_GRAPHICCARD_2(5, " Graphic Card element not found"),
		NO_HARDDISK(6, " Hard Disk element not found"),
		NO_HARDDISK_2(7, " Hard Disk element not found"),
		NO_HARDDISK_3(8, " Hard Disk element not found"),
		;
		
		private int id;
		private String error;
		
		private ComputerErrorType(int id, String error) {
			this.id = id;
			this.error = error;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
		
		
		
	}
	
	public enum ComputerState{
		
		ERROR(0, "error"),
		BOOT(1, "boot"),
		ON(2, "on"),
		OFF(3, "off");
		
		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		private String state;
		private int id;
		
		private ComputerState(int id, String state) {
			
			this.state = state;
			
		}
		
		public static ComputerState getFromString(String name){
			
			switch (name) {
			case "off":
				return ComputerState.OFF;
			case "on":
				return ComputerState.ON;
			case "boot":
				return ComputerState.BOOT;
			case "error":
				return ComputerState.ERROR;
			default:
				break;
			}
			
			return null;
			
		}
		
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
	
	@Override
	public int getField(int id) {
	    switch (id) {
	        case 0:
	            return this.burningTimeLeft;
	        case 1:
	            return this.timePassed;
	    }
	    return 0;
	}
	 
	@Override
	public void setField(int id, int value) {
	    switch (id) {
	        case 0:
	            this.burningTimeLeft = value;
	            break;
	        case 1:
	            this.timePassed = value;
	    }
	}
	 
	@Override
	public int getFieldCount() {
	    return 2;
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
	    return 64;
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
	    return null;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
	    // Le slot 3 je n'autorise que les planches de bois
	    if (index == 2)
	        return OreDictionary.getOres("plankWood").contains(
	                new ItemStack(stack.getItem(), 1,
	                        OreDictionary.WILDCARD_VALUE));
	    // Le slot 4 je n'autorise que le blï¿½
	    if (index == 3)
	        return stack.getItem() == Items.WHEAT;
	    // Le slot 5 (celui du rï¿½sultat) je n'autorise rien
	    if (index == 4)
	        return false;
	    // Sinon pour les slots 1 et 2 on met ce qu'on veut
	    return true;
	}
	
	/** Vï¿½rifie la distance entre le joueur et le bloc et que le bloc soit toujours prï¿½sent */
	public boolean isUsableByPlayer(EntityPlayer player) {
	    return this.world.getTileEntity(this.pos) != this ? false : player
	            .getDistanceSq((double) this.pos.getX() + 0.5D,
	                    (double) this.pos.getY() + 0.5D,
	                    (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public boolean hasFuelEmpty() {
	    return this.getStackInSlot(2).isEmpty()
	            || this.getStackInSlot(3).isEmpty();
	}
	
	public ItemStack getRecipeResult() {
	    return RecipesComputerCase.getRecipeResult(new ItemStack[] {
	            this.getStackInSlot(0), this.getStackInSlot(1) });
	}
	
	public boolean canSmelt() {
	    // On rï¿½cupï¿½re le rï¿½sultat de la recette
	    ItemStack result = this.getRecipeResult();
	 
	    // Le rï¿½sultat est null si il n'y a pas de recette associï¿½e, donc on retourne faux
	    if (result != null) {
	 
	        // On rï¿½cupï¿½re le contenu du slot de rï¿½sultat
	        ItemStack slot4 = this.getStackInSlot(4);
	 
	        // Si il est vide on renvoie vrai
	        if (slot4.isEmpty())
	            return true;
	 
	        // Sinon on vï¿½rifie que ce soit le mï¿½me objet, les mï¿½me mï¿½tadata et que la taille finale ne sera pas trop grande
	        if (slot4.getItem() == result.getItem() && slot4.getItemDamage() == result.getItemDamage()) {
	            int newStackSize = slot4.getCount() + result.getCount();
	            if (newStackSize <= this.getInventoryStackLimit() && newStackSize <= slot4.getMaxStackSize()) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public void smelt() {
	    // Cette fonction n'est appelï¿½e que si result != null, c'est pourquoi on ne fait pas de null check
	    ItemStack result = this.getRecipeResult();
	    // On enlï¿½ve un item de chaque ingrï¿½dient
	    this.decrStackSize(0, 1);
	    this.decrStackSize(1, 1);
	    // On rï¿½cupï¿½re le slot de rï¿½sultat
	    ItemStack stack4 = this.getStackInSlot(4);
	    // Si il est vide
	    if (stack4.isEmpty()) {
	        // On y insï¿½re une copie du rï¿½sultat
	        this.setInventorySlotContents(4, result.copy());
	    } else {
	        // Sinon on augmente le nombre d'objets de l'ItemStack
	        stack4.setCount(stack4.getCount() + result.getCount());
	    }
	}
	/** Temps de cuisson de la recette */
	public int getFullRecipeTime() {
	    return 200;
	}
	 
	/** Temps que dure 1 unitï¿½ de carburant (ici : 1 planche + 1 blï¿½) */
	public int getFullBurnTime() {
	    return 300;
	}
	 
	/** Renvoie vrai si le feu est allumï¿½ */
	public boolean isBurning() {
	    return burningTimeLeft > 0;
	}
	
	@Override
	public void tick() {
	    if (!this.world.isRemote) {
	 
	        /* Si le carburant brï¿½le, on rï¿½duit rï¿½duit le temps restant */
	        if (this.isBurning()) {
	            this.burningTimeLeft--;
	        }
	 
	        /*
	            * Si la on peut faire cuire la recette et que le four ne cuit pas
	            * alors qu'il peut, alors on le met en route
	            */
	        if (!this.isBurning() && this.canSmelt() && !this.hasFuelEmpty()) {
	            this.burningTimeLeft = this.getFullBurnTime();
	            this.decrStackSize(2, 1);
	            this.decrStackSize(3, 1);
	        }
	 
	        /* Si on peut faire cuire la recette et que le feu cuit */
	        if (this.isBurning() && this.canSmelt()) {
	            this.timePassed++;
	            if (timePassed >= this.getFullRecipeTime()) {
	                timePassed = 0;
	                this.smelt();
	            }
	        } else {
	            timePassed = 0;
	        }
	        this.markDirty();
	    }
	}

    public TileEntityComputerCase() {
    	tileType = "normal";
;    	readFromNBT(null);
    }
    
    public TileEntityComputerCase(ComputerState state) {
    	tileType = "stated";
    	this.state = state;
		 
		 if (state == ComputerState.ERROR) {
			NAME = "computer_case_error";
		 }else if(state == ComputerState.ON) {
			 NAME = "computer_case_on";
		 }else if(state == ComputerState.BOOT) {
			 NAME = "computer_case_boot";
		 }else {
			 NAME = "computer_case";
		 }
		 
		 readFromNBT(null);
    }
    
    public ComputerState getComputerState() {
		
		return this.state;
		
	}
    
    public Thread getComputerthread() {
		return this.computerthread;
	}

	public void setComputerthread(Thread computerthread) {
		this.computerthread = computerthread;
	}
	
	public void setAddress() { 
		if (this.address == null || this.address == "") {
			
			Random rand = new Random();
			
			int integer = rand.nextInt(999999999);
			
			this.address = String.valueOf(integer);
			
			this.markDirty(); 
		}
	}
	
	public String getAddress() { 
		return this.address; 
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if (nbt == null) nbt = new NBTTagCompound();
		
		if (address != null && address != "") {
			nbt.setString("address", this.address);
			System.out.println("Save de l'addres: " + this.address);
		}
	    ItemStackHelper.saveAllItems(nbt, this.stacks);
	    if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.customName);
	    }
		return super.writeToNBT(nbt);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt == null) nbt = new NBTTagCompound();
		
	    this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	    ItemStackHelper.loadAllItems(nbt, this.stacks);
	    if (nbt.getString("address") != null && nbt.getString("address") != "") {
	    	this.address = nbt.getString("address");
			System.out.println("Chargement de l'address: " + nbt.getString("address"));
			System.out.println(tileType);
			lastAddress = this.address;
		}else{
			setAddress();
		}
	    if (nbt.hasKey("CustomName", 8)) {
	       this.customName = nbt.getString("CustomName");
	    }
		super.readFromNBT(nbt);
	}
	
}
