package net.journey.entity.mob.euca;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEucaFighter extends EntityModMob {

	public static final int ENTITY_TYPE = 22;

	public EntityEucaFighter(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.7F, 1.7F);
		dataWatcher.updateObject(ENTITY_TYPE, rand.nextInt(4));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(ENTITY_TYPE, (int)0);
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
		return EnumSounds.INSECTO;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.INSECTO_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.INSECTO_HURT;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.eucaMeat;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(70) == 0) dropItem(JourneyItems.eucaTablet, 1);
		super.dropFewItems(b, j);
	}
}