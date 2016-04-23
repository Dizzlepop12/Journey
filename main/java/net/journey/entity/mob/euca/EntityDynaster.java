package net.journey.entity.mob.euca;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDynaster extends EntityModMob{

	
	public EntityDynaster(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.0F, 1.7F);
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
		return EnumSounds.DYNASTER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.DYNASTER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.DYNASTER_DEATH;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource e, float a) {
		if(e.getSourceOfDamage() instanceof EntityPlayer)
			((EntityPlayer)e.getSourceOfDamage()).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 1));
		return super.attackEntityFrom(e, a);

	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(54) == 0) dropItem(JourneyItems.royalDisk, 1);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.shimmerdust, 2);
		if(rand.nextInt(10) == 0) dropItem(JourneyItems.shimmerdust, 4);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}