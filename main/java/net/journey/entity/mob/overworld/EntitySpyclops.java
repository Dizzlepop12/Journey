package net.journey.entity.mob.overworld;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySpyclops extends EntityModMob {

	public static final int ENTITY_TYPE = 24;
	
	public EntitySpyclops(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.5F, 2.0F);
		dataWatcher.updateObject(ENTITY_TYPE, rand.nextInt(4));
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(ENTITY_TYPE, (int)0);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.lowJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.overworldHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.SPYCLOPS;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SPYCLOPS;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SPYCLOPS_HURT;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.spyclopseEye;
	}
}