package net.journey.entity.mob.end;

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

public class EntityEnderCrawler extends EntityModMob{

	
	public EntityEnderCrawler(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.2F, 1.7F);
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
		return EnumSounds.REAPER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.REAPER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.REAPER_HURT;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource e, float a) {
		if(e.getSourceOfDamage() instanceof EntityPlayer)
			((EntityPlayer)e.getSourceOfDamage()).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 1));
		return super.attackEntityFrom(e, a);
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		
	}
}