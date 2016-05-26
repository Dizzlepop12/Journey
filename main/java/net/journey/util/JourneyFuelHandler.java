package net.journey.util;

import net.journey.JourneyItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class JourneyFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() == JourneyItems.blazium) {
			return 3200;
		}
		return 0;
	}
}
