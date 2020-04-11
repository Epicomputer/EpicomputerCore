package fr.epicomputer.core.items;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.gui.GuiWiki;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Bios extends Item {
	
	public static final String NAME = "bios";
	
    public Bios()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
    }

}
