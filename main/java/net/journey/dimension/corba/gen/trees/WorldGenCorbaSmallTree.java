package net.journey.dimension.corba.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenCorbaSmallTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX() - 2, j = pos.getY() - 1, k = pos.getZ() - 2;
		Block log = JourneyBlocks.corbaLog;
		Block leaves = JourneyBlocks.corbaLeaves;
		int height = r.nextInt(3);
		WorldGenAPI.addRectangle(1, 1, 4 + height, world, i + 2, j + 1, k + 2, log);
		j = j + height;
		world.setBlockState(new BlockPos(i, j + 2, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 4), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 2), leaves.getDefaultState());
		return true;
	}
}