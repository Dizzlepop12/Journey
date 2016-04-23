package net.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockModLog;

public class BlockIceLog extends BlockModLog {

	public BlockIceLog() {
		super("iceLog", "Ice Log");
		isOpaque = false;
		isNormalCube = false;
		setLightOpacity(3);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iba, BlockPos pos, EnumFacing side) {
		Block block = iba.getBlockState(pos).getBlock();
		return block == this && (side == EnumFacing.DOWN || side == EnumFacing.UP) ? false : super.shouldSideBeRendered(iba, pos, side);
	}
}