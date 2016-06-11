package net.journey.blocks.machines;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockIgniter extends BlockMod {

	public BlockIgniter(String name, String finalName) {
		super(name, finalName);
	}
	
	@Override
	public boolean isFireSource(World w, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	@Override
    public boolean canConnectRedstone(IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
            	worldIn.setBlockState(pos.up(), Blocks.fire.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
            }
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
        }
    }
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if(!worldIn.isRemote) {
			if(worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos.up(), Blocks.fire.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniterOn.getDefaultState());
			}
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState());
            	worldIn.setBlockState(pos, JourneyBlocks.igniter.getDefaultState());
			}
		}
	}
}