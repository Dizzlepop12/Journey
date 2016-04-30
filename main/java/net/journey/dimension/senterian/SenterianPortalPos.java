package net.journey.dimension.senterian;

import net.minecraft.world.ChunkCoordIntPair;

public class SenterianPortalPos extends ChunkCoordIntPair {
	
    public long seed;
    private final TeleporterSenterian teleporter;

    public SenterianPortalPos(TeleporterSenterian teleporter, int par2, int par3, int par4, long par5) {
        super(par2, par3);
        this.teleporter = teleporter;
        this.seed = par5;
    }
}