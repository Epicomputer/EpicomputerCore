package fr.epicomputer.core.init;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.blocks.ComputerCase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksCore {
	
	public static final Block COMPUTER_CASE = new ComputerCase(Material.IRON);
	 
	 public static void setBlockName(Block block, String name)
	 {
	     block.setRegistryName(EpicomputerCore.MODID, name).setUnlocalizedName(EpicomputerCore.MODID + "." + name);
	 }

}
