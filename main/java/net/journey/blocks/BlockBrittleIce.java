package net.journey.blocks;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import net.slayer.api.block.BlockModGrass;

public class BlockBrittleIce extends BlockModGrass {

	protected BlockMod dirt; 
	protected String tex;

	public BlockBrittleIce(BlockMod dirt, String name, String finalName, float hardness) {
		super(dirt, name, finalName, hardness);
		this.dirt = dirt;
		this.isNormalCube = false;
		this.isOpaque = false;
		setCreativeTab(JourneyTabs.blocks);
		setTickRandomly(true);
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
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iba, BlockPos pos, EnumFacing side) {
		Block block = iba.getBlockState(pos).getBlock();
		return block == this ? false : super.shouldSideBeRendered(iba, pos, side);
	}
}