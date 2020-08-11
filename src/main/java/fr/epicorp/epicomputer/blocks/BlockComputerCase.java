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
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockComputerCase extends BlockContainer {
	
	public static final String NAME = "computer_case";
	public static final PropertyEnum<BlockComputerCase.EnumType> VARIANT = PropertyEnum.<BlockComputerCase.EnumType>create("variant", BlockComputerCase.EnumType.class); 
	TileEntity tileentity = null;
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	
	public BlockComputerCase() {
		super(Material.IRON);
		
		BlocksMod.setBlockName(this, NAME);
	    setResistance(5.0F);
	    setHardness(3.0F);
	    setCreativeTab(Epicomputer.tabEpicomputer);
	    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockComputerCase.EnumType.COMPUTER_OFF)); 
	    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)); 
	    
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
		
		EnumFacing enumFacing = EnumFacing.getHorizontal(MathHelper.floor((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
		state = state.withProperty(FACING, enumFacing);
		
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
	
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis){
	    return super.rotateBlock(world, pos, axis);
	}

	 

	    @Override
	    public int damageDropped(IBlockState state){

	        // Ici nous allons dire à Forge quelle valeur de métadonnée doit avoir l'objet obtenu lorsque l'on casse le bloc.

	    	return state.getValue(VARIANT).getMetadata(); 
	    	
	    }

	 

	    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList <ItemStack>list){

	        // Nous allons définir ici les variantes du bloc.
	    	
	    	for (BlockComputerCase.EnumType type : BlockComputerCase.EnumType.values())

	    	{

	    	    list.add(new ItemStack(itemIn, 1, type.getMetadata()));

	    	}
	    	
	    }

	 

	    @Override
	    public IBlockState getStateFromMeta(int meta){
	    	
	    	return this.getBlockState().getBaseState().withProperty(VARIANT, BlockComputerCase.EnumType.byMetadata(meta));

	    }

	 

	    @Override
	    public int getMetaFromState(IBlockState state){

	        // Nous allons renvoyer la métadonnée selon l'état du bloc.
	    	
	    	return ((BlockComputerCase.EnumType)state.getValue(VARIANT)).getMetadata();
	    	
	    }

	 

	    @Override
	    protected BlockStateContainer createBlockState(){

	        // Nous allons créer l'état de l'objet.

	    	return new BlockStateContainer(this, new IProperty[] {VARIANT, FACING});
	    	
	    }

	 

	    public static enum EnumType implements IStringSerializable{

	        // Nous allons définir ici les différents sous-blocs.

	        COMPUTER_OFF(0, "computer_case", "computer_case"),
	    	COMPUTER_BOOT(1, "computer_case_boot", "computer_case_m1"),
	    	COMPUTER_ERROR(2, "computer_case_error", "computer_case_m2"),
	    	COMPUTER_ON(3, "computer_case_on", "computer_case_m3");

	 

	        // Nous allons créer ici quelques variables que nous utiliserons dans les fonctions.

	    	private static final BlockComputerCase.EnumType[] META_LOOKUP = new BlockComputerCase.EnumType[values().length];

	    	private final int meta;

	    	private final String name;

	    	private final String unlocalizedName;

	        private EnumType(int metaIn, String nameIn, String unlocalizedIn){

	            // Ici nous créons le constructeur de l'énumération

	        	this.meta = metaIn;
	        	this.name = nameIn;
	        	this.unlocalizedName = unlocalizedIn;
	        	
	        }

	 

	        public static String[] getUnlocalizedNames(){

	            // Nous utiliserons cette fonction pour récupérer une liste des noms non-localisés.
	        	
	            String[] names = new String[values().length];

	            

	            for (int i = 0; i < META_LOOKUP.length; i++)

	                names[i] = META_LOOKUP[i].unlocalizedName;

	         

	            return names;
	        	
	        }

	 

	        public int getMetadata(){

	            // Nous utiliserons cette fonction pour obtenir la métadonnée de l'objet.
	        	
	        	return this.meta;
	        	
	        }

	 

	        public static BlockComputerCase.EnumType byMetadata(int meta){

	            // Nous allons ici obtenir la valeur dans l'énumération correspondant à la métadonnée passée en paramètre. Remplacez TutorialBlock par le nom de votre classe.
	        	
	            if (meta < 0 || meta >= META_LOOKUP.length)

	            {

	                meta = 0;

	            }

	         

	            return META_LOOKUP[meta];
	        	
	        }

	 

	        public String toString(){

	            // Nous allons renvoyer le nom de l'objet.

	        	return this.name;
	        	
	        }

	 

	        @Override

	        public String getName(){

	            // Nous allons ici aussi renvoyer le nom de l'objet.
	        	
	        	return this.name;
	        	
	        }
	        
	        static

	        {

	            for (BlockComputerCase.EnumType type : values())

	            {

	                META_LOOKUP[type.getMetadata()] = type;

	            }

	        }
	        
	    }
	 
}

