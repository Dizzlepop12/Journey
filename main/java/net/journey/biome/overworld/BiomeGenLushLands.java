package net.journey.biome.overworld;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenLushLands extends BiomeGenBase {
    private boolean par2;
    private static final IBlockState f1 = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState f2 = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockState f3 = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    public BiomeGenLushLands(int par1, boolean par2) {
        super(par1);
        this.theBiomeDecorator.flowersPerChunk = 25;
        this.theBiomeDecorator.deadBushPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 10;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator.sandPerChunk2 = 3;
        this.theBiomeDecorator.sandPerChunk = 3;
        this.waterColorMultiplier = 3;
        this.par2 = par2;
        if (par2) {
            this.theBiomeDecorator.treesPerChunk = 2;
        }
        else {
            this.theBiomeDecorator.treesPerChunk = 50;
        }
        this.theBiomeDecorator.grassPerChunk = 25;

    }

    @Override
    public WorldGenAbstractTree genBigTreeChance(Random rand) {
    	return (WorldGenAbstractTree)
    			(rand.nextInt(10) == 0 ? this.worldGeneratorBigTree : 
    			(rand.nextInt(2) == 0 ? new WorldGenShrub(f1, f3) : 
    			(!this.par2 && rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, f1, f2) : 
    			new WorldGenTrees(false, 4 + rand.nextInt(7), f1, f2, true))));
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int par1, int par2, double par3) {
        double d0 = GRASS_COLOR_NOISE.func_151601_a((double)par1 * 0.25D, (double)par2 * 0.25D);

        if (d0 > 0.0D) {
            int i = par1 & 15;
            int j = par2 & 15;

            for (int k = 255; k >= 0; --k) {
                if (chunkPrimerIn.getBlockState(j, k, i).getBlock().getMaterial() != Material.air) {
                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.water) {
                        chunkPrimerIn.setBlockState(j, k, i, Blocks.water.getDefaultState());

                        if (d0 < 0.12D) {
                            chunkPrimerIn.setBlockState(j, k + 1, i, Blocks.waterlily.getDefaultState());
                        }
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, par1, par2, par3);
    }

    @Override
    public void addDefaultFlowers() {
        addFlower(
        		Blocks.red_flower.getDefaultState().withProperty(
        		Blocks.red_flower.getTypeProperty(), 
        		BlockFlower.EnumFlowerType.BLUE_ORCHID), 10);
    }
    
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = rand.nextInt(height);
        (new WorldGenMelon()).generate(worldIn, rand, pos.add(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        for (j = 0; j < 50; ++j) {
            k = rand.nextInt(16) + 8;
            int l = 128;
            int i1 = rand.nextInt(16) + 8;
            worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
        }
    }
}