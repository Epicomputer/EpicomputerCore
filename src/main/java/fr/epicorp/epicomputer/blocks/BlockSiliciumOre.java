package fr.epicorp.epicomputer.blocks;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.BlocksMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSiliciumOre extends Block {

	public static final String NAME = "silicium_ore";
	
	public BlockSiliciumOre(Material materialIn) {
		super(materialIn);
		
		BlocksMod.setBlockName(this, NAME);
		setHardness(1.6f);
		setResistance(1.6f);
		setCreativeTab(Epicomputer.tabEpicomputer);
		
	}

	
	
}
