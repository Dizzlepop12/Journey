package net.journey.entity.mob.frozen;

import net.journey.JourneyBlocks;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityShiveringShrieker extends EntityModMob {

	public EntityShiveringShrieker(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(0.65F, 1F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.frozenHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.SMALL_HONGO;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SMALL_HONGO_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SMALL_HONGO_HURT;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 60.0D && super.getCanSpawnHere();
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
}