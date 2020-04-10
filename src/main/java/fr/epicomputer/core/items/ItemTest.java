package fr.epicomputer.core.items;

import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTest extends Item {
	
	public static final String NAME = "item_test";
	
    public ItemTest()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(CreativeTabs.MISC);
    }

}