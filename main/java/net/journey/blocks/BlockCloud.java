package net.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockCloud extends BlockMod {

	public BlockCloud(String name, String f) {
		super(EnumMaterialTypes.WOOL, name, f, 0.2F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.4D;
        entityIn.motionY *= 0.4D;
        entityIn.motionZ *= 0.4D;
    }
	
	@Override
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess iba, BlockPos pos, EnumFacing side) {
		Block block = iba.getBlockState(pos).getBlock();
        return block == this ? false : super.shouldSideBeRendered(iba, pos, side);
    }
}