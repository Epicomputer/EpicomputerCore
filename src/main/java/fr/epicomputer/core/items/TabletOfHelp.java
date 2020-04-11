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

public class TabletOfHelp extends Item {
	
	public static final String NAME = "tablet_of_help";
	
    public TabletOfHelp()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
    }

    @Override

    public ActionResult <ItemStack>onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
    	
    	Minecraft.getMinecraft().displayGuiScreen(new GuiWiki());
    	
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }
    
}
