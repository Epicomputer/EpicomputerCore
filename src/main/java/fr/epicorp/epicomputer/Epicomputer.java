package fr.epicorp.epicomputer;

import org.apache.logging.log4j.Logger;

import fr.epicorp.epicomputer.gen.WorldGen;
import fr.epicorp.epicomputer.handler.GuiHandler;
import fr.epicorp.epicomputer.handler.RegisteringHandler;
import fr.epicorp.epicomputer.init.ItemsMod;
import fr.epicorp.epicomputer.init.RecipesMod;
import fr.epicorp.epicomputer.proxy.ClientProxy;
import fr.epicorp.epicomputer.proxy.CommonProxy;
import fr.epicorp.epicomputer.tileentity.TileEntityComputerCase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Epicomputer.MODID, name = Epicomputer.NAME, version = Epicomputer.VERSION)
public class Epicomputer {

	public static final String MODID = "epicomputer";
	public static final String NAME = "Epicomputer";
	public static final String VERSION = "0.0.1";
	
	WorldGen worldgeneration = new WorldGen(); 
	
	@Instance(Epicomputer.MODID)
	public static Epicomputer instance;
	
	@SidedProxy(clientSide = ClientProxy.PACKAGE, serverSide = CommonProxy.PACKAGE)
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	public Epicomputer() {
		MinecraftForge.EVENT_BUS.register(new RegisteringHandler());
	}
	
	public static final CreativeTabs tabEpicomputer = new CreativeTabs("tabEpicomputer")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ItemsMod.BIOS);
        }
    };
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event.getSuggestedConfigurationFile());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileEntityComputerCase.class, Epicomputer.MODID +":tile_computer_case");
        GameRegistry.registerWorldGenerator(worldgeneration, 0); 
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		RecipesMod.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {

	}
	
}
