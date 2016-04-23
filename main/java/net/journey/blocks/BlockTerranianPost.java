package net.journey.blocks;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.block.BlockMod;

public class BlockTerranianPost extends BlockMod {

	public BlockTerranianPost(String name, String f) {
		super(name, f);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		setBlockBounds(0.3F, 0.0F, 0.3F, 0.6F, 1.0F, 0.6F);
	}
}