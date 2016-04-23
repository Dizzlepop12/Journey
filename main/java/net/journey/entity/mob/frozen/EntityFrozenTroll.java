package net.journey.entity.mob.frozen;

import net.journey.JourneyBlocks;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityFrozenTroll extends EntityModMob {

	public EntityFrozenTroll(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(0.5F, 0.7F);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity e) {
		boolean attacked = super.attackEntityAsMob(e);
		if(attacked) {
			if(e instanceof EntityLivingBase) 
				((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 2, true, false));
			e.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F)) * 2, 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F)) * 2);
		}
		return attacked;
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
		return this.posY > 60.0D && super.getCanSpawnHere();
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
}