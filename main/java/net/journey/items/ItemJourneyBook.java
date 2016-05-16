package net.journey.items;

import amerifrance.guideapi.item.ItemGuideBook;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemJourneyBook extends ItemGuideBook {
	public ItemJourneyBook(String name, String finalName){
		this(name, finalName, JourneyTabs.items);
	}

	public ItemJourneyBook(String name, String finalName, CreativeTabs tab){
		LangRegistry.addItem(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);
	}
}
