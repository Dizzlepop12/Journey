package net.journey.dimension;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;

public abstract class ChunkPrimerGenerator {

	public abstract boolean generate(ChunkPrimer primer, Random rand, BlockPos pos);
	
}