package fr.epicomputer.core.blocks;

import java.util.Random;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.BlocksCore;
import fr.epicomputer.core.tiles.TileEntityComputerCase;
import fr.epicomputer.core.tiles.TileEntityComputerCase.ComputerState;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.functions.SetMetadata;


public class ComputerCase extends BlockContainer {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public String NAME;
	public ComputerState state;
	public TileEntityComputerCase tile;
	public BlockPos pos;
	
	public ComputerCase(Material materialIn, ComputerState state) {
		 super(materialIn);
		 
		 if (state == ComputerState.ERROR) {
				NAME = "computer_case_error";
			 }else if(state == ComputerState.ON) {
				 NAME = "computer_case_on";
			 }else if(state == ComputerState.BOOT) {
				 NAME = "computer_case_boot";
			 }else {
				 NAME = "computer_case";
			 }
		 
		 this.state = state;
		 
		 BlocksCore.setBlockName(this, NAME);
		 setResistance(5.0F);
		 //this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	        
		 setHardness(3.0F);
		 setCreativeTab(EpicomputerCore.tabsCore);
		 
	}
    
 
//////////////////////////////////////////
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }
  
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
  
            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }
  
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    
    @Override
    public boolean hasTileEntity() {
        return true;
    }
     
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
     
        if (tileentity instanceof TileEntityComputerCase) {
            InventoryHelper.dropInventoryItems(worldIn, pos,
                    (TileEntityComputerCase) tileentity);
        }
     
        super.breakBlock(worldIn, pos, state);
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        
    	this.pos = pos;
    	
    	if (world.isRemote) {
            return true;
        } else {
        	
            TileEntity tileentity = world.getTileEntity(pos);
     
            if (tileentity instanceof TileEntityComputerCase) {
                player.openGui(EpicomputerCore.instance, 0, world, pos.getX(),
                        pos.getY(), pos.getZ());
                
            }
     
            return true;
        }
    }
  
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)  {
        
    	tile = new TileEntityComputerCase(this.state);
    	
    	return tile;
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    	if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
     
            if (tileentity instanceof TileEntityComputerCase) {
                ((TileEntityComputerCase) tileentity).setCustomName(stack
                        .getDisplayName());
            }
        }
    }
    
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
  
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);
  
        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
  
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
  
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
  
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
  
    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
  
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
  
    public static void setState(ComputerState state, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (state == ComputerState.ON)
        {
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_ON.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_ON.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else if (state == ComputerState.BOOT)
        {
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_BOOT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_BOOT.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else if (state == ComputerState.ERROR)
        {
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_ERROR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE_ERROR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlocksCore.COMPUTER_CASE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
	
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
}
