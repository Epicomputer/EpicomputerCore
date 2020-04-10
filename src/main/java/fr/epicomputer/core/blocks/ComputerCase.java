package fr.epicomputer.core.blocks;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.BlocksCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ComputerCase extends Block {
	
	public static final String NAME = "computer_case";

	public ComputerCase(Material materialIn) {
		 super(materialIn);
		 
		 BlocksCore.setBlockName(this, NAME);
		 setResistance(5.0F);
		 setHardness(3.0F);
		 setCreativeTab(EpicomputerCore.tabsCore);
	}

	

}
