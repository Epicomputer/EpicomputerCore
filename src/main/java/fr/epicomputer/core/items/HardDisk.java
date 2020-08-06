package fr.epicomputer.core.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.API;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HardDisk extends Item {
	
	public static final String NAME = "hard_disk";
	
    public HardDisk()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
//        setMaxStackSize(1);
//        setHasSubtypes(true);
//        setMaxDamage(0);
    }

}
