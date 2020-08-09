package fr.epicorp.epicomputer.init;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.itemblocks.ItemBlockComputerCase;
import fr.epicorp.epicomputer.items.ItemEpicomputer;
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
	
	public static final Item computer_case = new ItemBlockComputerCase();

	public static void setItemName(Item item, String name) {
		item.setRegistryName(Epicomputer.MODID, name).setUnlocalizedName(Epicomputer.MODID + "." + name);
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		registerModel(computer_case, 0);
	}

	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item, int metadata) {
		if (metadata < 0) metadata = 0;
	    String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
	    if (metadata > 0) resourceName += "_m" + String.valueOf(metadata);
	 
	    ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceName, "inventory"));
	}
}