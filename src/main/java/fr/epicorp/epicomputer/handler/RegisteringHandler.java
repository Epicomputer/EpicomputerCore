package fr.epicorp.epicomputer.handler;

import fr.epicorp.epicomputer.init.BlocksMod;
import fr.epicorp.epicomputer.init.ItemBlocksMod;
import fr.epicorp.epicomputer.init.ItemsMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringHandler {
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				ItemsMod.BIOS,
				ItemsMod.PROCESSOR, 
				ItemsMod.RAM, 
				ItemsMod.GRAPHIC_CARD,
				ItemsMod.HARD_DISK
				);
		/******************** ITEMBLOCKS ********************/
		event.getRegistry().registerAll(
				ItemBlocksMod.computer_case,
				ItemBlocksMod.SILICIUM_ORE
				);
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				BlocksMod.computer_case,
				BlocksMod.SILICIUM_ORE
				);
	}

}
