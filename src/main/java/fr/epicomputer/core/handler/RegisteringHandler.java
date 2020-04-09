package fr.epicomputer.core.handler;


import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringHandler {
	
	@SubscribeEvent
    public void registerItems(Event.Register<Item> event) {
        event.getRegistry().registerAll(ItemsCore.TUTORIAL); 
        //event.getRegistry().registerAll(block1, block2, block3);
        
    }

}
