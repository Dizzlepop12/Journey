package net.journey.blocks.base;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockCanBeTilled extends BlockMod {

	Block farmland;
	public BlockCanBeTilled(EnumMaterialTypes types, String name, String finalName, float hardness, Block farmland) {
		super(types, name, finalName, hardness);
		this.farmland = farmland;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(
		playerIn.getHeldItem() != null && 
		playerIn.getHeldItem().getItem() == Items.diamond_hoe || 
		playerIn.getHeldItem().getItem() == Items.iron_hoe || 
		playerIn.getHeldItem().getItem() == Items.golden_hoe|| 
		playerIn.getHeldItem().getItem() == Items.stone_hoe|| 
		playerIn.getHeldItem().getItem() == Items.wooden_hoe) {
			if(worldIn.isRemote);
			worldIn.setBlockState(new BlockPos(0, 0, 0), farmland.getDefaultState());
			worldIn.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), farmland.stepSound.getStepSound(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.frequency * 0.8F);
			return true;
		}
		return false;
	}
}
