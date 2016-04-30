package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.journey.dimension.senterian.room.RoomBase;
import net.journey.dimension.senterian.room.RoomChest;
import net.journey.dimension.senterian.room.RoomHall;
import net.journey.dimension.senterian.room.RoomNPC;
import net.journey.dimension.senterian.room.RoomSpawner1;
import net.journey.dimension.senterian.room.SenterianCeiling;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderSenterian implements IChunkProvider {

    public class ChunkCoords {
        public final int chunkCoordX;
        public final int chunkCoordZ;

        public ChunkCoords(int X, int Z) {
            this.chunkCoordX = X;
            this.chunkCoordZ = Z;
        }

        public boolean equals(Object o) {
            if (!(o instanceof ChunkCoords)) {
                return false;
            } else {
                ChunkCoords chunkCoords = (ChunkCoords) o;
                return chunkCoords.chunkCoordX == this.chunkCoordX
                        && chunkCoords.chunkCoordZ == this.chunkCoordZ;
            }
        }

        public int hashCode() {
            return this.chunkCoordX + this.chunkCoordZ * 31;
        }
    }

    private ArrayList Rooms;
    private ArrayList BossRooms;
    private SenterianCeiling Ceiling;
    private World worldObj;
    private Random random;
    private Map chunkTileEntityMap;

    public ChunkProviderSenterian(World world, long seed) {

        worldObj = world;
        random = new Random(seed);

        Rooms = new ArrayList(21);

        Rooms.add(new RoomHall());
        Rooms.add(new RoomChest());
        Rooms.add(new RoomNPC());
        Rooms.add(new RoomSpawner1());
        /*Rooms.add(new RoomSpawner2());
        Rooms.add(new RoomSpawner3());
        Rooms.add(new RoomSpawner4());*/
        Ceiling = new SenterianCeiling();
        this.chunkTileEntityMap = new HashMap();
    }

    @Override
    public boolean chunkExists(int i, int j) {
        return false;
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        ChunkPrimer senterianChunk = new ChunkPrimer();

        for (int i = 4; i > 0; i--) {
            RoomBase room = (RoomBase) (Rooms.get(random.nextInt(21)));
            if (room instanceof RoomHall && i >= 3)
                room = (RoomBase) (Rooms.get(this.random.nextInt(10) + 10));

            room.generate(senterianChunk, random, (new BlockPos(0, i * 8, 0)));
        }

        Ceiling.generate(senterianChunk, random, (new BlockPos(0, 40, 0)));

        chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), senterianChunk);

        Chunk chunk = new Chunk(this.worldObj, chunkX, chunkZ);
        chunk.generateSkylightMap();
        BiomeGenBase[] biome = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, chunkX * 16,
                        chunkZ * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) biome[i].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public Chunk provideChunk(BlockPos pos) {
        return this.provideChunk(pos);
    }

    @Override
	public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(new BlockPos(x + 16, z + 16, 0));
		boolean flag = false;
		this.random.setSeed(this.worldObj.getSeed());
		long var8 = this.random.nextLong() / 2L * 2L + 1L;
		long var10 = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed(chunkX * var8 + chunkZ * var10 ^ this.worldObj.getSeed());
		int roomToGenerate;

		Random rand = random;
		Chunk chunk = this.worldObj.getChunkFromChunkCoords(chunkX, chunkZ);

		ChunkCoords chunkCoords = new ChunkCoords(chunkX, chunkZ);
		List<ChunkCoordIntPair> chunkTileEntityPositions = (List<ChunkCoordIntPair>)chunkTileEntityMap.get(chunkCoords);
		if (chunkTileEntityPositions != null) {
			for (int i = 0; i < chunkTileEntityPositions.size(); i++) {
				ChunkCoordIntPair ChunkCoordIntPair = chunkTileEntityPositions.get(i);
				Block b = chunk.getBlock(ChunkCoordIntPair.chunkXPos, ChunkCoordIntPair.chunkZPos, i);
				TileEntity te = b.createTileEntity(this.worldObj, null);
				this.worldObj.setTileEntity(new BlockPos(x + ChunkCoordIntPair.chunkXPos, z + ChunkCoordIntPair.chunkZPos, i), te);
			}
			chunkTileEntityMap.remove(chunkCoords);
		}
		if ((chunkX & 1) == 1 && (chunkZ & 1) == 1) {
		    for(int i = 1; i < 4; i++) {
    			if(this.random.nextInt(30) == 0 || this.random.nextInt(30) == 0 || this.random.nextInt(30) == 0) {
    	            roomToGenerate = rand.nextInt(2);
    				this.random.setSeed(chunkX * var8 + chunkZ * var10 ^ this.worldObj.getSeed() * i << 2 | var10);
    				break;
    			}
		    }
		}
	}

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
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
        return "Senterian Labyrinth";
    }

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(Chunk chunk, int i, int j) {}

    @Override
    public void saveExtraData() {}

    public ChunkCoordIntPair findClosestStructure(World var1, String var2, int var3, int var4, int var5) {
        return null;
    }

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return false;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}
}
