package fr.epicomputer.core.handler;

import fr.epicomputer.core.init.BlocksCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringHandler {
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
	    event.getRegistry().registerAll(
	    		BlocksCore.COMPUTER_CASE,
	    		BlocksCore.COMPUTER_CASE_ON,
	    		BlocksCore.COMPUTER_CASE_BOOT,
	    		BlocksCore.COMPUTER_CASE_ERROR
	    		);
	}
	
	@SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(
			ItemsCore.BIOS, 
			ItemsCore.COMPUTER_CASE, 
			ItemsCore.PROCESSOR, 
			ItemsCore.RAM, 
			ItemsCore.CARDGRAPHICS, 
			ItemsCore.HARDDISK,
			ItemsCore.TABLETOFHELP
		);
        
    }
}
