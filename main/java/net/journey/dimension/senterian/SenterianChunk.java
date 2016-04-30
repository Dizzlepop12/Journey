package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;

public class SenterianChunk {
	private Block[] data;
	private byte[] meta;
    public List<ChunkCoordIntPair> chunkTileEntityPositions;
	
	public SenterianChunk() {
		data = new Block[32768];
		meta = new byte[32768];
		this.chunkTileEntityPositions = new ArrayList<ChunkCoordIntPair>();
	}
	
	public void setBlockState(BlockPos pos, IBlockState iBlockState) {
		this.setBlockState(pos, iBlockState);
	}
	
	public void setBlockState(int x, int y, int z, Block b, int m) {
		data[x<<11 | z<<7 | y] = b;
		meta[x<<11 | z<<7 | y] = (byte)m;
		if (b.hasTileEntity()) {
			this.chunkTileEntityPositions.add(new ChunkCoordIntPair(x, y));
		}
	}
	
	public Block getBlock(int x, int y, int z) {
		return data[x<<11 | z<<7 | y];
	}
	
	public Block[] getChunkData() {
		return data;
	}
	
	public byte[] getChunkMetadata() {
		return meta;
	}

}