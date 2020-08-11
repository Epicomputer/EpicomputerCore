package fr.epicorp.epicomputer.blocks;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.tileentity.TileEntitySiliciumFactory;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSiliciumFactory extends BlockContainer{

	public static final String NAME = "silicium_factory";
	//public static final AxisAlignedBB SILICIUM_FACTORY = new AxisAlignedBB(1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
	
	public BlockSiliciumFactory() {
		super(Material.IRON);
		
		BlocksMod.setBlockName(this, NAME);
		
	    setResistance(5.0F);
	    setHardness(3.0F);
	    setCreativeTab(Epicomputer.tabEpicomputer);
		
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		
		return false;
		
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean hasTileEntity()
    {
        return true;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySiliciumFactory();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	 
	    if (tileentity instanceof TileEntitySiliciumFactory) {
	        InventoryHelper.dropInventoryItems(worldIn, pos,
	                (TileEntitySiliciumFactory) tileentity);
	    }
	 
	    super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    if (world.isRemote) {
	        return true;
	    } else {
	        TileEntity tileentity = world.getTileEntity(pos);
	 
	        if (tileentity instanceof TileEntitySiliciumFactory) {
	            player.openGui(Epicomputer.instance, 0, world, pos.getX(),
	                    pos.getY(), pos.getZ());
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
	    if (stack.hasDisplayName()) {
	        TileEntity tileentity = worldIn.getTileEntity(pos);
	 
	        if (tileentity instanceof TileEntitySiliciumFactory) {
	            ((TileEntitySiliciumFactory) tileentity).setCustomName(stack
	                    .getDisplayName());
	        }
	    }
	}
}
