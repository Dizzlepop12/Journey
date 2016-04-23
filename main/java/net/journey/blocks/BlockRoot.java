package net.journey.blocks;

import net.journey.JourneyTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockRoot extends BlockMod {

	public BlockRoot(String name, String f) {
		super(EnumMaterialTypes.PLANT, name, f, 0.4F);
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World w, BlockPos pos, IBlockState state) {
        return null;
    }

}
