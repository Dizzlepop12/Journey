package net.journey.blocks;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockHalfSlab extends BlockMod {

	public BlockHalfSlab(String name, String f) {
		super(name, f, 1.0F);
		setCreativeTab(JourneyTabs.blocks);
		setLightOpacity(255);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SlayerAPI.toItem(this);
	}

	@Override
	public Item getItem(World worldIn, BlockPos pos) {
		return SlayerAPI.toItem(this);
	}
}