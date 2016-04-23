package net.journey.blocks;

import java.util.Random;

import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModFlower;

public class BlockEucaSapling extends BlockModFlower implements IGrowable {

	public BlockEucaSapling(String name, String f1) {
		super(name, f1);
		this.setTickRandomly(true);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public void updateTick(World w, BlockPos pos, IBlockState s, Random r)  {
		if(!w.isRemote) {
			super.updateTick(w, pos, s, r);
			if(w.getLightFromNeighbors(pos.up()) >= 9 && r.nextInt(9) == 0)
				this.generate(w, pos, r);
		}
	}

	private void generate(World w, BlockPos pos, Random r) {
		Object tree = new WorldGenEucaTree();
		/*switch(r.nextInt(2)) {
		case 0:
			tree = new WorldGenBigEucaTree();
			break;
		case 1:
			tree = new WorldGenSmallEucaTree();
			break;
		}*/
		((WorldGenerator)tree).generate(w, r, pos);
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		generate(worldIn, pos, rand);
	}
}