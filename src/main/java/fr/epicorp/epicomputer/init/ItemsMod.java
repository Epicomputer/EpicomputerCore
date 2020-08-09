package fr.epicorp.epicomputer.init;

import fr.epicorp.epicomputer.Epicomputer;
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
public class ItemsMod {
	
	public static final Item BIOS = new ItemEpicomputer("bios");
	public static final Item PROCESSOR = new ItemEpicomputer("processor");
	public static final Item RAM = new ItemEpicomputer("ram");
	public static final Item GRAPHIC_CARD = new ItemEpicomputer("graphic_card");
	public static final Item HARD_DISK = new ItemEpicomputer("hard_disk");
	public static final Item SILICIUM_PLATE = new ItemEpicomputer("silicium_plate");

	public static void setItemName(Item item, String name) {
		item.setRegistryName(Epicomputer.MODID, name).setUnlocalizedName(Epicomputer.MODID + "." + name);
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		registerModel(BIOS, 0);
		registerModel(PROCESSOR, 0);
		registerModel(RAM, 0);
		registerModel(GRAPHIC_CARD, 0);
		registerModel(HARD_DISK, 0);
		registerModel(SILICIUM_PLATE, 0);
	}

	@SideOnly(Side.CLIENT)
	public static void registerModel(Item item, int metadata) {
		if (metadata < 0) metadata = 0;
	    String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
	    if (metadata > 0) resourceName += "_m" + String.valueOf(metadata);
	 
	    ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceName, "inventory"));
	}
}