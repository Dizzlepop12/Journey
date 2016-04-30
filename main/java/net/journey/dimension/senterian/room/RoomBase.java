package net.journey.dimension.senterian.room;

import java.util.Random;

import net.journey.dimension.senterian.SenterianChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class RoomBase {

    public abstract boolean generate(SenterianChunk chunk, Random rand, BlockPos pos);

}