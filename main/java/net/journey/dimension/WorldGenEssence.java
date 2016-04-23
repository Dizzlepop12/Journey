package net.journey.dimension;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.boil.gen.WorldGenBoilingFire;
import net.journey.dimension.boil.gen.WorldGenBoilingLava;
import net.journey.dimension.depths.gen.WorldGenDepthsTree;
import net.journey.dimension.euca.gen.WorldGenSmeltery;
import net.journey.dimension.nether.gen.WorldGenBoilPortal;
import net.journey.dimension.nether.gen.WorldGenHellThorn;
import net.journey.dimension.nether.gen.WorldGenHellThornMedium;
import net.journey.dimension.nether.gen.WorldGenHellThornTall;
import net.journey.dimension.nether.gen.WorldGenNetherDungeons;
import net.journey.dimension.nether.gen.WorldGenNetherTower;
import net.journey.dimension.overworld.gen.*;
import net.journey.util.Config;
import net.journey.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenEssence implements IWorldGenerator {

	private World worldObj;
	private static Random r = new Random();

	public WorldGenEssence() {
		r = new Random();
		LogHelper.info("Loading world generator");
	}
	
	@Override
	public void generate(Random r, int chunkX, int chunkZ, World w, IChunkProvider c, IChunkProvider c2) {
		int dim = w.provider.getDimensionId();
		switch(dim) {
		case -1: generateNether(w, r, chunkX * 16, chunkZ * 16);
		case 0: generateOverworld(w, r, chunkX * 16, chunkZ * 16);
		}
		if(dim == Config.boil) generateBoilingPoint(w, r, chunkX * 16, chunkZ * 16);
		if(dim == Config.depths) generateDepths(w, r, chunkX * 16, chunkZ * 16);
		if(dim == Config.euca) generateEuca(w, r, chunkX * 16, chunkZ * 16);
		if(dim == Config.frozen) generateFrozen(w, r, chunkX * 16, chunkZ * 16);
		if(dim == Config.corba) generateCorba(w, r, chunkX * 16, chunkZ * 16);
		if(dim == Config.cloudia) generateCloudia(w, r, chunkX * 16, chunkZ * 16);
		//if(dim == Config.terrania) generateTerrania(w, r, chunkX * 16, chunkZ * 16);
		int i;
	}
	
	public void generateNether(World w, Random r, int chunkX, int chunkZ) {
		int x, y, z;
		int times;
		for(times = 0; times < 20; times++) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			worldMinableGenNether(JourneyBlocks.lavaRock, 5, w, x, y, z);
		}
		
		for(times = 0; times < 20; times++) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			worldMinableGenNether(JourneyBlocks.hellstoneOre, 5, w, x, y, z);
		}
		
		if(r.nextInt(20)==0) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
				new WorldGenNetherTower().generate(w, r, new BlockPos(x, y, z));
		}

		if(r.nextInt(40)==0) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
			new WorldGenBoilPortal().generate(w, r, new BlockPos(x, y, z));
		}
		
		if(r.nextInt(40)==0) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
			new WorldGenNetherDungeons().generate(w, r, new BlockPos(x, y, z));
		}
		
		for(times = 0; times < 1; times++) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
			new WorldGenHellThornTall().generate(w, r, new BlockPos(x, y, z));
		}
		
		for(times = 0; times < 1; times++) {
			y = r.nextInt(128) + 1;
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
			new WorldGenHellThornMedium().generate(w, r, new BlockPos(x, y, z));
		}
		
		for(times = 0; times < 1; times++) {
			y = r.nextInt(64); 
			x = chunkX + r.nextInt(16);
			z = chunkZ + r.nextInt(16);
			if(y > 20 && y < 110) if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.netherrack.getDefaultState()) 
			new WorldGenHellThorn().generate(w, r, new BlockPos(x, y, z));
		}
	}
	
	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return worldObj.getBlockState(new BlockPos(x, y, z)) == grass.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.air.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 2, z)) == Blocks.air.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 3, z)) == Blocks.air.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 4, z)) == Blocks.air.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 5, z)) == Blocks.air.getDefaultState();
	}
	
	public void generateOverworld(World w, Random rand, int chunkX, int chunkZ) {
		int x, y, z;
		int times;
		for(times = 0; times < 100; times++) {
			y = r.nextInt(63); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenTallGlowshrooms()).generate(w, r, new BlockPos(x, y, z));
		}
		for(times = 0; times < 100; times++) {
			y = r.nextInt(63); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenSmallGlowshrooms()).generate(w, r, new BlockPos(x, y, z));
		}
		if(r.nextInt(1)==0) {
			y = r.nextInt(20); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			worldMinableGenVanilla(JourneyBlocks.shadiumOre, 10, w, x, y, z);
		}
		if(r.nextInt(1)==0) {
			y = r.nextInt(25); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			worldMinableGenVanilla(JourneyBlocks.luniumOre, 10, w, x, y, z);
		}
		if(r.nextInt(1)==0) {
			y = r.nextInt(20); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			worldMinableGenVanilla(JourneyBlocks.sapphireOre, 10, w, x, y, z);
		}
		if(r.nextInt(6)==0) {
			y = r.nextInt(200); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.grass.getDefaultState()) 
				new WorldGenTowerDungeon().generate(w, r, new BlockPos(x, y, z));
		}
		for(times = 0; times < 6; times++) {
			y = r.nextInt(160); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			worldMinableGenEnd(JourneyBlocks.enderilliumOre, 5, w, x, y, z);
		}
		if(r.nextInt(10)==0) {
			y = r.nextInt(200); 
			x = chunkX + r.nextInt(16) + 8; 
			z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.grass.getDefaultState() || 
				w.getBlockState(new BlockPos(x, y, z)) == Blocks.grass.getDefaultState() ||
				w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.sand.getDefaultState() || 
				w.getBlockState(new BlockPos(x, y, z)) == Blocks.sand.getDefaultState())
			new WorldGenMageHouse().generate(w, r, new BlockPos(x, y, z));
		}
		if(r.nextInt(10)==0) {
			y = r.nextInt(200); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.grass.getDefaultState() || 
				w.getBlockState(new BlockPos(x, y, z)) == Blocks.grass.getDefaultState() ||
			    w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.sand.getDefaultState() || 
			    w.getBlockState(new BlockPos(x, y, z)) == Blocks.sand.getDefaultState())
			new WorldGenBlacksmithHouse().generate(w, r, new BlockPos(x, y, z));
		}
		if(r.nextInt(10)==0) {
			y = r.nextInt(200); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.grass.getDefaultState() || 
				w.getBlockState(new BlockPos(x, y, z)) == Blocks.grass.getDefaultState() ||
				w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.sand.getDefaultState() || 
				w.getBlockState(new BlockPos(x, y, z)) == Blocks.sand.getDefaultState())
			new WorldGenMerchant().generate(w, r, new BlockPos(x, y, z));
		}
	}

	public static void generateEssenceDimensions(int gen, World w, int chunkX, int chunkZ) {
		int x, y, z;
		switch(gen) {
		case 0:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.celestiumOre.getDefaultState(), 10, BlockHelper.forBlock(JourneyBlocks.eucaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 1:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.flairiumOre.getDefaultState(), 8, BlockHelper.forBlock(JourneyBlocks.depthsStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 2:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y, z)) != JourneyBlocks.depthsGrass || w.getBlockState(new BlockPos(x, y - 1, z)) != JourneyBlocks.depthsGrass)
				new WorldGenDepthsTree(true).generate(w, r, new BlockPos(x, y, z));
			break;
		case 3:
			y = r.nextInt(250) + 1; x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.ashualOre.getDefaultState(), 7, BlockHelper.forBlock(JourneyBlocks.ashBlock))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 4:
			y = r.nextInt(200) + 1; x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenBoilingLava(Blocks.lava)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 5:
			y = r.nextInt(200) + 1; x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenBoilingFire()).generate(w, r, new BlockPos(x, y, z));
			break;
		case 6:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenModFlower(JourneyBlocks.eucaTallGrass)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 7:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenModFlower(JourneyBlocks.eucaTallFlowers)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 8:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenModFlower(JourneyBlocks.eucaBlueFlower)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 9:
			y = r.nextInt(60); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenModFlower(JourneyBlocks.frozenFlower)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 10:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.depthsLights.getDefaultState(), 25, BlockHelper.forBlock(JourneyBlocks.depthsGrass))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 11:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.depthsLights.getDefaultState(), 25, BlockHelper.forBlock(JourneyBlocks.depthsStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 12:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenModFlower(JourneyBlocks.depthsFlower)).generate(w, r, new BlockPos(x, y, z));
			break;
		case 13:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.gorbiteOre.getDefaultState(), 6, BlockHelper.forBlock(JourneyBlocks.corbaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 14:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.orbaditeOre.getDefaultState(), 6, BlockHelper.forBlock(JourneyBlocks.corbaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 15:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.storonOre.getDefaultState(), 10, BlockHelper.forBlock(JourneyBlocks.eucaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 16:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.koriteOre.getDefaultState(), 10, BlockHelper.forBlock(JourneyBlocks.eucaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 17:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.mekyumOre.getDefaultState(), 10, BlockHelper.forBlock(JourneyBlocks.eucaStone))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 18:
			y = r.nextInt(200); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(w.getBlockState(new BlockPos(x, y - 1, z)) == JourneyBlocks.eucaGrass.getDefaultState()) new WorldGenSmeltery().generate(w, r, new BlockPos(x, y, z));
			break;
		case 19:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(y < 64 && y > 30)
			(new WorldGenMinable(JourneyBlocks.pinkCloudiaCloud.getDefaultState(), 40, BlockHelper.forBlock(Blocks.air))).generate(w, r, new BlockPos(x, y, z));
			break;
		case 20:
			y = r.nextInt(250); x = chunkX + r.nextInt(16) + 8; z = chunkZ + r.nextInt(16) + 8;
			if(y > 99 && y < 130)
			(new WorldGenMinable(JourneyBlocks.lightBlueCloudiaCloud.getDefaultState(), 40, BlockHelper.forBlock(Blocks.air))).generate(w, r, new BlockPos(x, y, z));
			break;
		}
	}

	private static void worldMinableGenVanilla(Block spawn, int vein, World w, int x, int y, int z){
		(new WorldGenMinable(spawn.getDefaultState(), vein)).generate(w, r, new BlockPos(x, y, z));
	}

	private static void worldMinableGenNether(Block spawn, int vein, World w, int x, int y, int z){
		(new WorldGenMinable(spawn.getDefaultState(), vein, BlockHelper.forBlock(Blocks.netherrack))).generate(w, r, new BlockPos(x, y, z));
	}

	private static void worldMinableGenEnd(Block spawn, int vein, World w, int x, int y, int z){
		(new WorldGenMinable(spawn.getDefaultState(), vein, BlockHelper.forBlock(Blocks.end_stone))).generate(w, r, new BlockPos(x, y, z));
	}

	private void generateCorba(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		for(i = 0; i < 10; i++) WorldGenEssence.generateEssenceDimensions(13, w, chunkX, chunkZ);
		for(i = 0; i < 10; i++) WorldGenEssence.generateEssenceDimensions(14, w, chunkX, chunkZ);
	}

	private void generateFrozen(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		for(i = 0; i < 25; i++) WorldGenEssence.generateEssenceDimensions(9, w, chunkX, chunkZ);
	}

	private void generateBoilingPoint(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		//if(rand.nextInt(4) == 0) GenerationHelper.generateEssenceDimensions(4, w, chunkX, chunkZ);
		for(i = 0; i < 50; i++) WorldGenEssence.generateEssenceDimensions(5, w, chunkX , chunkZ);
		for(i = 0; i < 40; i++) WorldGenEssence.generateEssenceDimensions(3, w, chunkX , chunkZ);
	}

	private void generateDepths(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		//for(i = 0; i < 25; i++) GenerationHelper.generateEssenceDimensions(12, w, chunkX, chunkZ);
		//for(i = 0; i < 25; i++) GenerationHelper.generateEssenceDimensions(2, w, chunkX, chunkZ);
		//for(i = 0; i < 5; i++) GenerationHelper.generateEssenceDimensions(10, w, chunkX, chunkZ);
	}

	private void generateEuca(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		for(i = 0; i < 30; i++) WorldGenEssence.generateEssenceDimensions(0, w, chunkX, chunkZ);
		for(i = 0; i < 30; i++) WorldGenEssence.generateEssenceDimensions(15, w, chunkX, chunkZ);
		for(i = 0; i < 30; i++) WorldGenEssence.generateEssenceDimensions(16, w, chunkX, chunkZ);
		for(i = 0; i < 30; i++) WorldGenEssence.generateEssenceDimensions(17, w, chunkX, chunkZ);
		for(i = 0; i < 70; i++) WorldGenEssence.generateEssenceDimensions(6, w, chunkX, chunkZ);
		for(i = 0; i < 15; i++) WorldGenEssence.generateEssenceDimensions(7, w, chunkX, chunkZ);
		for(i = 0; i < 15; i++) WorldGenEssence.generateEssenceDimensions(8, w, chunkX, chunkZ);
	}
	
	private void generateCloudia(World w, Random r, int chunkX, int chunkZ) {
		int i = 0;
		for(i = 0; i < 15; i++) WorldGenEssence.generateEssenceDimensions(19, w, chunkX, chunkZ);
		for(i = 0; i < 3; i++) WorldGenEssence.generateEssenceDimensions(20, w, chunkX, chunkZ);
	}
}