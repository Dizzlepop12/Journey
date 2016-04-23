package net.journey.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockChristmasLights extends BlockMod implements IShearable {

	public BlockChristmasLights(String name, String f) {
		super(EnumMaterialTypes.LEAVES, name, f, 0.2F);
		setLightLevel(0.35F);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SlayerAPI.toItem(this);
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	@Override
	public int getRenderType() {
		return JourneyBlocks.mossyEssenceStone.getRenderType();
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}
}