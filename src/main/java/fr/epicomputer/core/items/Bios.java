package fr.epicomputer.core.items;

import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Bios extends Item {
	
	public static final String NAME = "bios";
	
    public Bios()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(CreativeTabs.MISC);
    }

}
