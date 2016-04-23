package net.journey.dimension.cloudia;

import java.util.List;
import java.util.Random;

import net.journey.dimension.cloudia.gen.WorldGenCloudiaLamp;
import net.journey.dimension.cloudia.gen.WorldGenCloudiaLand;
import net.journey.dimension.cloudia.gen.WorldGenHut;
import net.journey.dimension.cloudia.gen.WorldGenIsland;
import net.journey.dimension.cloudia.gen.WorldGenStarlightCastle;
import net.journey.dimension.cloudia.gen.WorldGenStarlightTree;
import net.journey.dimension.cloudia.gen.WorldGenStarlightVillage;
import net.journey.dimension.cloudia.gen.WorldGenTower;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderCloudia implements IChunkProvider {

	private Random rand;
	private World worldObj;
	private BiomeGenBase[] biomesForGeneration;
	public ChunkProviderCloudia(World worldIn, long seed) {
		this.worldObj = worldIn;
		this.rand = new Random(seed);
		new NoiseGeneratorOctaves(this.rand, 4);
		new NoiseGeneratorOctaves(this.rand, 4);
	}

	@Override
	public Chunk provideChunk(int cx, int cz) {
		this.rand.setSeed((long)cx * 391279512714L + (long)cz * 132894987741L);
		ChunkPrimer primer = new ChunkPrimer();
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, cx* 16, cz* 16, 16, 16);
		Chunk chunk = new Chunk(this.worldObj, primer, cx, cz);
		byte[] abyte = chunk.getBiomeArray();
		for(int k = 0; k < abyte.length; ++k) abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
		chunk.generateSkylightMap();
		return chunk;
	}

	private static WorldGenerator castle = new WorldGenStarlightCastle();
	private static WorldGenerator tower = new WorldGenTower();
	private static WorldGenerator hut = new WorldGenHut();
	private static WorldGenerator lamp = new WorldGenCloudiaLamp();
	private static WorldGenerator tree = new WorldGenStarlightTree();
	private static WorldGenerator island = new WorldGenIsland();
	private static WorldGenerator village = new WorldGenStarlightVillage();
	
	@Override
	public void populate(IChunkProvider c, int cx, int cz) {
		this.rand.setSeed(this.worldObj.getSeed() * (cx + cz) * this.rand.nextInt());
		int x1 = cx * 16;
		int z1 = cz * 16;
		int x, z;
		x = x1 + this.rand.nextInt(16);
		z = z1 + this.rand.nextInt(16);
		
		if (this.rand.nextInt(60) == 0) {
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))castle.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		if (this.rand.nextInt(60) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))tower.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		if (this.rand.nextInt(30) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))hut.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))lamp.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))tree.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))island.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(40) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))village.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		/**if (this.rand.nextInt(15) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int y = rand.nextInt(250);
			if(y > 30 && y < 130) new WorldGenCloudiaLand().generate(worldObj, rand, new BlockPos(x, y, z));
		}*/
	}

	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public boolean func_177460_a(IChunkProvider ic, Chunk c, int x, int z) {
		return false;
	}

	@Override
	public boolean saveChunks(boolean b, IProgressUpdate i) {
		return true;
	}

	@Override
	public void saveExtraData() {
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String s, BlockPos p) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk c, int x, int z) {
	}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
	}
}