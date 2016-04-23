package net.journey.blocks;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.block.BlockModVine;

public class BlockCaveVine extends BlockModVine {

	public BlockCaveVine(String name, String f) {
		super(name, f, 2);
		setLightLevel(0.6F);
	}
	
	@Override
	public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos) {
		return 1000;
	}
}