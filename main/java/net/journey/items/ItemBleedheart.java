package net.journey.items;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.slayer.api.item.ItemModFood;

public class ItemBleedheart extends ItemModFood {

	public ItemBleedheart(String name, String f, int food, float sat, boolean wolfFood) {
		super(name, f, food, sat, wolfFood);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return false;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (block == JourneyBlocks.sizzlerWoodLog) {
				if (side == EnumFacing.DOWN) {
					return false;
				}

				if (side == EnumFacing.UP) {
					return false;
				}

				pos = pos.offset(side);

				if (worldIn.isAirBlock(pos)) {
					IBlockState iblockstate1 = JourneyBlocks.bleedheartFruit.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn);
					worldIn.setBlockState(pos, iblockstate1, 2);

					if (!playerIn.capabilities.isCreativeMode) {
						--stack.stackSize;
					}
				}
				return true;
			}
			return false;
		}
	}
}
