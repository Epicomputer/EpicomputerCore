package fr.epicomputer.core;


import org.apache.logging.log4j.Logger;

import fr.epicomputer.core.blocks.ComputerCase;
import fr.epicomputer.core.handler.GuiHandler;
import fr.epicomputer.core.handler.RegisteringHandler;
import fr.epicomputer.core.init.ItemsCore;
import fr.epicomputer.core.proxy.CommonProxy;
import fr.epicomputer.core.tiles.TileEntityComputerCase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = EpicomputerCore.MODID, name = EpicomputerCore.NAME, version = EpicomputerCore.VERSION)
public class EpicomputerCore {
	
	public static final String MODID = "ecore";
	public static final String NAME = "Epicomputer Core";
	public static final String VERSION = "0.0.1";
	public static final String MINECRAFT_VERSION = "1.12.2";
	
	
    @Instance(EpicomputerCore.MODID)
   public static EpicomputerCore instance;

    @SidedProxy(clientSide = "fr.epicomputer.core.proxy.ClientProxy", serverSide = "fr.epicomputer.core.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabsCore = new CreativeTabs("epicomputercore_creative_tabs")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ItemsCore.BIOS);
        }
     
       
    };

    public static Logger logger;
    
    public EpicomputerCore() {
    	MinecraftForge.EVENT_BUS.register(new RegisteringHandler());
	}
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event.getSuggestedConfigurationFile());
        GameRegistry.registerTileEntity(TileEntityComputerCase.class, EpicomputerCore.MODID +":tile_computer_case");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }
 
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {
 
    }
    
}
