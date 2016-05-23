package net.journey.blocks.crop;

import java.util.List;
import java.util.Random;

import net.journey.JourneyCrops;
import net.journey.JourneyItems;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.block.BlockModCrop;

public class BlockAirRootCrop extends BlockModCrop {

	public BlockAirRootCrop(String name) {
		super(name);
	}

	@Override
	public PropertyInteger getAge() {
		return PropertyInteger.create("age", 0, 4);
	}
	
	@Override
	public Item getSeed() {
		return JourneyCrops.airRootSeed;
	}

	@Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        this.setBlockBoundsForItemRender();
    }
    
	@Override
	public Item getCrop() {
		return JourneyItems.airMelon;
	}
	
	@Override
    public int quantityDropped(Random random) {
        return 1 + random.nextInt(2);
    }

	@Override
	public int getStages() {
		return 4;
	}
}