package net.journey.dimension.senterian.room;

import java.util.Random;

import net.journey.dimension.senterian.SenterianChunk;

public abstract class RoomBase {

    public abstract boolean generate(SenterianChunk chunk, Random rand, int x, int y, int z);

}