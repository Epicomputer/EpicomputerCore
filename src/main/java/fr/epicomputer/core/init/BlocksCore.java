package fr.epicomputer.core.init;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.blocks.ComputerCase;
import fr.epicomputer.core.blocks.ComputerCase.ComputerState;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksCore {
	
	public static final Block COMPUTER_CASE = new ComputerCase(Material.IRON, ComputerState.OFF);
	public static final Block COMPUTER_CASE_ON = new ComputerCase(Material.IRON, ComputerState.ON);
	public static final Block COMPUTER_CASE_BOOT = new ComputerCase(Material.IRON, ComputerState.BOOT);
	public static final Block COMPUTER_CASE_ERROR = new ComputerCase(Material.IRON, ComputerState.ERROR);
	 
	 public static void setBlockName(Block block, String name)
	 {
	     block.setRegistryName(EpicomputerCore.MODID, name).setUnlocalizedName(EpicomputerCore.MODID + "." + name);
	 }

}
