package fr.epicorp.epicomputer.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockComputerCase extends BlockContainer {
	
	public static final String NAME = "computer_case";
	TileEntity tileentity = null;
	public BlockComputerCase() {
		super(Material.IRON);
		
		BlocksMod.setBlockName(this, NAME);
	    setResistance(5.0F);
	    setHardness(3.0F);
	    setCreativeTab(Epicomputer.tabEpicomputer);
		
	}

	@Override
	public boolean hasTileEntity() {
	    return true;
	}
	 
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)  {
	    return new TileEntityComputerCase();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	 
	    if (tileentity instanceof TileEntityComputerCase) {
	        InventoryHelper.dropInventoryItems(worldIn, pos,(TileEntityComputerCase) tileentity);
	    
	        TileEntityComputerCase tileentitycomputercase = (TileEntityComputerCase)tileentity;

                ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound.setTag("BlockEntityTag", ((TileEntityComputerCase)tileentity).saveToNbt(nbttagcompound1));
                itemstack.setTagCompound(nbttagcompound);

                if (tileentitycomputercase.hasCustomName())
                {
                    itemstack.setStackDisplayName(tileentitycomputercase.getName());
                    tileentitycomputercase.setCustomName("");
                }
                
                if (tileentitycomputercase.hasAddress())
                {
                	nbttagcompound.setString("address", tileentitycomputercase.getAddress());
                }

                spawnAsEntity(worldIn, pos, itemstack);
            

            worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        }
	 
	    super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    if (world.isRemote) {
	        return true;
	    } else {
	        TileEntity tileentity = world.getTileEntity(pos);
	 
	        if (tileentity instanceof TileEntityComputerCase) {
	            player.openGui(Epicomputer.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
	        }
	 
	        return true;
	    }
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
	    return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		TileEntityComputerCase te = ((TileEntityComputerCase)tileentity);
		
		if (stack.hasDisplayName()) {
	 
	        if (tileentity instanceof TileEntityComputerCase) {
	            te.setCustomName(stack.getDisplayName());
	        }
	    }
		if(te.getTileData().getString("address") != null) {
			 if (te.getTileData().getString("address") != null || te.getTileData().getString("address") != ""){
				if (tileentity instanceof TileEntityComputerCase) {
					te.setRandomAddress();
				}
			} else  if (te.getTileData().getString("address") == null || te.getTileData().getString("address") == ""){
				if (tileentity instanceof TileEntityComputerCase) {
					te.setAddress(stack.getTagCompound().getString("address"));
				}
			}
		}
		
		
	}
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        ItemStack itemstack = super.getItem(worldIn, pos, state);
        TileEntityComputerCase tileentitycomputer = (TileEntityComputerCase)worldIn.getTileEntity(pos);
        NBTTagCompound nbttagcompound = tileentitycomputer.saveToNbt(new NBTTagCompound());

        if (!nbttagcompound.hasNoTags())
        {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }

        return itemstack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null && nbttagcompound.hasKey("address"))
        {
				tooltip.add(String.format("Address : " + stack.getTagCompound().getString("address")));

			

        }
    }
}

