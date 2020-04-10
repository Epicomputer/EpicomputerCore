package fr.epicomputer.core.items;

import fr.epicomputer.core.EpicomputerCore;
import fr.epicomputer.core.init.ItemsCore;
import net.minecraft.item.Item;

public class CardGraphics extends Item {
	
	public static final String NAME = "graphic_card";
	
    public CardGraphics()
    {
        super();
        ItemsCore.setItemName(this, NAME);
        setCreativeTab(EpicomputerCore.tabsCore);
    }

}
