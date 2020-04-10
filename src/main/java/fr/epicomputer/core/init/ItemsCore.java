package fr.epicomputer.core.init;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.items.Bios;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
 
@EventBusSubscriber(value = Side.CLIENT, modid = EpicomputerCore.MODID) // En 1.12+
public class ItemsCore
{
	//creer un item ici
    public static final Item BIOS = new Bios();
    //block item
    public static final Item COMPUTER_CASE = new ItemBlock(BlocksCore.COMPUTER_CASE).setRegistryName(BlocksCore.COMPUTER_CASE.getRegistryName());
 
    public static void setItemName(Item item, String name)
    {
        item.setRegistryName(EpicomputerCore.MODID, name).setUnlocalizedName(EpicomputerCore.MODID + "." + name);
    }
 
 
    // En 1.12+
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemsModels(ModelRegistryEvent event)
    {
    	//registre ton model
    	registerModel(BIOS, 0);
    	//block item
    	registerModel(COMPUTER_CASE, 0);
    }
 
    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item, int metadata)
    {
        if (metadata < 0) metadata = 0;
        String resourceName = item.getUnlocalizedName().substring(5).replace('.', ':');
        if (metadata > 0) resourceName += "_m" + String.valueOf(metadata);
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceName, "inventory"));
    }
 
}
