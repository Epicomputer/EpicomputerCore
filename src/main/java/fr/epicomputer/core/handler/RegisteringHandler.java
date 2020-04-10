package fr.epicomputer.core.handler;

import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringHandler {
	
	@SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemsCore.TUTORIAL);
        
    }

}
