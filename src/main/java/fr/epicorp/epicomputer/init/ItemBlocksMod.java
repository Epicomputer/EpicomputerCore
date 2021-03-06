package fr.epicorp.epicomputer.init;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.blocks.BlockComputerCase;
import fr.epicorp.epicomputer.blocks.BlockSiliciumOre;
import fr.epicorp.epicomputer.itemblocks.ItemBlockMetadata;
import fr.epicorp.epicomputer.itemblocks.ItemBlockSiliciumFactory;
import fr.epicorp.epicomputer.itemblocks.ItemBlockSiliciumOre;
import fr.epicorp.epicomputer.items.ItemEpicomputer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(value = Side.CLIENT, modid = Epicomputer.MODID)
public class ItemBlocksMod {
	
	public static final Item computer_case = new ItemBlockMetadata(BlocksMod.computer_case, new String[]{"computer_case", "computer_case_m1", "computer_case_m2", "computer_case_m3"} ).setRegistryName(BlocksMod.computer_case.getRegistryName());
	public static final Item SILICIUM_ORE = new ItemBlockSiliciumOre();
	public static final Item SILICIUM_FACTORY = new ItemBlockSiliciumFactory();
	
	public static void setItemName(Item item, String name) {
		item.setRegistryName(Epicomputer.MODID, name).setUnlocalizedName(Epicomputer.MODID + "." + name);
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		for (int i = 0; i < BlockComputerCase.EnumType.values().length; i++) registerModel(computer_case, i);
		registerModel(SILICIUM_ORE);
		registerModel(SILICIUM_FACTORY);
	}

	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item, int metadata) {
		if (metadata < 0) metadata = 0;
	    String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
	    if (metadata > 0) resourceName += "_m" + String.valueOf(metadata);
	 
	    ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceName, "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item) {
	    String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
	    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(resourceName, "inventory"));
	}
}