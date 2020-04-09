package fr.epicomputer.core.items;

import net.minecraft.item.Item;
import net.minecraft.creativetab.*;

public class ItemTest extends Item {
	
	public static final String NAME = "item_test";
	
    public ItemTest()
    {
        super();
        this.setUnlocalizedName(NAME);
        setCreativeTab(CreativeTabs.MISC);
    }

}
