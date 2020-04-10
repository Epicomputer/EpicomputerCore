package fr.epicomputer.core.items;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Processor extends Item {
	
public static final String NAME = "processor";
	
    public Processor()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
    }

}
