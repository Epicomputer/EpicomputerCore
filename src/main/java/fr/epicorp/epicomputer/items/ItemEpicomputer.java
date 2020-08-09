package fr.epicorp.epicomputer.items;

import fr.epicorp.epicomputer.Epicomputer;
import fr.epicorp.epicomputer.init.ItemsMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEpicomputer extends Item {
	
	public static String NAME;
	
	public ItemEpicomputer(String name) {
		super();
		this.NAME = name;
		ItemsMod.setItemName(this, name);
		setCreativeTab(Epicomputer.tabEpicomputer);
	}

}
