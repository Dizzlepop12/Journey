package net.journey.blocks.base;

import net.journey.JourneyTabs;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class BlockModRail extends BlockRail {

	private boolean power;
	private float speed;
	
	public BlockModRail(String name, boolean isPowered, float speed) {
		setCreativeTab(JourneyTabs.blocks);
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, name);
		power = isPowered;
		this.speed = speed;
	}
	
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
		return speed;
	}
}