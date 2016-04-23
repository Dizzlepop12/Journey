package net.journey.blocks.crop;

import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockModCrop;

public class BlockGlowshroomCrop extends BlockModCrop {

	public BlockGlowshroomCrop(String name) {
		super(name);
	}

	@Override
	public PropertyInteger getAge() {
		return PropertyInteger.create("age", 0, 6);
	}
	
	@Override
	public Item getSeed() {
		return JourneyCrops.glowshroomPowder;
	}

	@Override
	public Item getCrop() {
		return JourneyItems.glowshroom;
	}

	@Override
	public int getStages() {
		return 6;
	}
}