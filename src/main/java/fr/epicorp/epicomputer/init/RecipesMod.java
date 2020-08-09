package fr.epicorp.epicomputer.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesMod {

	public static void init() {
		
		GameRegistry.addSmelting(ItemBlocksMod.SILICIUM_ORE, new ItemStack(ItemsMod.SILICIUM_PLATE), 5);
		
	}
	
}
