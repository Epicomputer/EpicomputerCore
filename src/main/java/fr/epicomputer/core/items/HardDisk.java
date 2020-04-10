package fr.epicomputer.core.items;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.item.Item;

public class HardDisk extends Item {
	
	public static final String NAME = "hard_disk";
	
    public HardDisk()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
    }

}
