package net.journey.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockDepthsLeaves extends BlockMod {

    public BlockDepthsLeaves(String name, String finalName, float hardness) {
        super(EnumMaterialTypes.LEAVES, name, finalName, hardness);
        this.setHardness(0.3F);
		isOpaque = false;
		isNormalCube = false;
		setLightOpacity(3);
        this.setTickRandomly(true);
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