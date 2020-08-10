package fr.epicorp.epicomputer.blocks;

import javax.annotation.Nullable;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.tileentity.TileEntitySiliciumFactory;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSiliciumFactory extends BlockContainer{

	public static final String NAME = "silicium_factory";
	public static final AxisAlignedBB SILICIUM_FACTORY = new AxisAlignedBB(1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
	
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SILICIUM_FACTORY;
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return SILICIUM_FACTORY;
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySiliciumFactory();
	}

}
