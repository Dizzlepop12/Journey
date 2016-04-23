package net.journey.blocks;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
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
import net.slayer.api.block.BlockModGrass;

public class BlockSwampLamp extends BlockMod {

	protected String tex;

	public BlockSwampLamp(String name, String finalName, float hardness) {
		super(EnumMaterialTypes.GLASS, name, finalName, hardness);
		this.isNormalCube = false;
		this.isOpaque = false;
		setCreativeTab(JourneyTabs.decoration);
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(pos.getX() + (5 * f), pos.getY(), pos.getZ() + (5 * f), pos.getX() + 1 - (5 * f), pos.getY() + 1.2D - (10 * f), pos.getZ() + 1 - (5 * f));
    }

	@Override 
    public boolean isOpaqueCube() {
    	return false;
    }
    
    @Override
    public boolean isNormalCube(){
		return false;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iba, BlockPos pos, EnumFacing side) {
		Block block = iba.getBlockState(pos).getBlock();
		return block == this ? false : super.shouldSideBeRendered(iba, pos, side);
	}
}