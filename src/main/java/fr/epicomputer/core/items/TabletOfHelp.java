package fr.epicomputer.core.items;

import java.util.Iterator;
import java.util.List;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;

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

        if(!world.isRemote)

        {

        	Minecraft.getMinecraft().displayGuiScreen(new GuiWiki());

        }

        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));

    }
    
}
