package fr.epicomputer.core.init;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.blocks.BlockTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksCore {
	
	public static final Block TESTBLOCK = new BlockTest(Material.ROCK);
	 
	 public static void setBlockName(Block block, String name)
	 {
	     block.setRegistryName(EpicomputerCore.MODID, name).setUnlocalizedName(EpicomputerCore.MODID + "." + name);
	 }

}
