package net.journey.dimension.nether.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModBush;

public class WorldGenNetherBush extends WorldGenerator {
	
	@Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(
            		rand.nextInt(8) 
            		- rand.nextInt(8), 
            		rand.nextInt(4) 
            		- rand.nextInt(4), 
            		rand.nextInt(8) 
            		- rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.netherrack && JourneyBlocks.sizzleberryBush.canPlaceBlockAt(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, JourneyBlocks.sizzleberryBush.getDefaultState().withProperty(BlockModBush.AGE, 2));
            }
        }
        return true;
    }
}