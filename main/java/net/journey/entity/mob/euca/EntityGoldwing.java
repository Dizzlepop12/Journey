package net.journey.entity.mob.euca;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityGoldwing extends EntityPeacefullUntillAttacked {

	public EntityGoldwing(World w) {
		super(w);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.eucaHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BIRD;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BIRD_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BIRD_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return JourneyItems.rocMeat;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.rocFeather, 4);
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.rocFeather, 2);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.rocMeat, 1);
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.rocMeat, 2);
		super.dropFewItems(b, j);
	}

}