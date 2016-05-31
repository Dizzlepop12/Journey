package net.journey.dimension.nether.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherFlower extends WorldGenerator {

	@Override
	public boolean generate(World w, Random rand, BlockPos pos) {
		Block plant = JourneyBlocks.deathGrass;
		switch(rand.nextInt(2)){
		case 0: plant = JourneyBlocks.deathGrass; break;
		case 1: plant = JourneyBlocks.hellBell; break;
		}
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = pos.add(
            		rand.nextInt(8) 
            		- rand.nextInt(8), 
            		rand.nextInt(4) 
            		- rand.nextInt(4), 
            		rand.nextInt(8) 
            		- rand.nextInt(8));
			if(w.getBlockState(blockpos.down()).getBlock() == JourneyBlocks.heatSoil && w.getBlockState(pos) == Blocks.air.getDefaultState() && pos.getY() < 250) {
				w.setBlockState(blockpos, plant.getDefaultState(), 2);
			}
		}
		return true;
	}
}