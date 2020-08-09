package fr.epicorp.epicomputer.init;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.blocks.BlockComputerCase;
import fr.epicorp.epicomputer.blocks.BlockSiliciumOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksMod {
	
	public static final Block computer_case = new BlockComputerCase();
	public static final Block SILICIUM_ORE = new BlockSiliciumOre(Material.ROCK);
	
	public static void setBlockName(Block block, String name)
	{
	    block.setRegistryName(Epicomputer.MODID, name).setUnlocalizedName(Epicomputer.MODID + "." + name);
	}

}
