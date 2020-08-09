package fr.epicorp.epicomputer.init;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.blocks.BlockComputerCase;
import net.minecraft.block.Block;

public class BlocksMod {
	
	public static final Block computer_case = new BlockComputerCase();
	
	public static void setBlockName(Block block, String name)
	{
	    block.setRegistryName(Epicomputer.MODID, name).setUnlocalizedName(Epicomputer.MODID + "." + name);
	}

}
