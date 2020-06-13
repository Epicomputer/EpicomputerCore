package fr.epicomputer.core.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.BlocksCore;
import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SmallSpeaker extends Block {

	public static final String NAME = "small_speaker";
	public static final AxisAlignedBB Z_SMALL_SPEAKER = new AxisAlignedBB(0.25, 0 ,0.25, 0.75D, 0.81D, 0.75D);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	protected static final Logger LOGGER = LogManager.getLogger();

	public SmallSpeaker(Material materialIn) {
		super(materialIn);
		BlocksCore.setBlockName(this, NAME);
	    setResistance(5.0F);
	    setHardness(3.0F);
	    setCreativeTab(EpicomputerCore.tabsCore);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
		
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        return enumfacing.getAxis() == EnumFacing.Axis.X ? Z_SMALL_SPEAKER : Z_SMALL_SPEAKER;
    }
	
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
    public void onBlockPlacedBy(World world, BlockPos pos,IBlockState state, EntityLivingBase placer, ItemStack stack) {
		
	    world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
    	
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);
  
        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
  
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getBlock() != this ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
	
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	
	
//	@Override
//	@Nullable
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
//    {
//        return NULL_AABB;
//    }

}
