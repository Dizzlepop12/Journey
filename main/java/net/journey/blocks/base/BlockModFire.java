package net.journey.blocks.base;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockModFire extends BlockMod {

	public BlockModFire(String name, String finalN) {
		super(EnumMaterialTypes.FIRE, name, finalN, 0.0F);
		setLightLevel(1.0F);
		setUnlocalizedName(name);
		setCreativeTab(null);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World w, BlockPos pos, IBlockState s) {
		return null;
	}

	@Override
	public boolean isFullBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setFire(6);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setFire(6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World w, BlockPos pos, IBlockState state, Random random) {
		for(int i = 0; i < 3; ++i) {
			double d0 = (double)pos.getX() + rand.nextDouble();
			double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
			double d2 = (double)pos.getZ() + rand.nextDouble();
			w.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState s) {
		if(!JourneyBlocks.eucaPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.depthsPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.boilPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.frozenPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.cloudiaPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.terraniaPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.goldenPortal.makePortal(world, pos)) {
			if(!World.doesBlockHaveSolidTopSurface(world, pos.down())) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}
}