package net.journey.entity.mob.boiling;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityBurningLight extends EntityModMob{

	public EntityBurningLight(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.7F, 2.0F);
		isImmuneToFire = true;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.boilHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BLAZE;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BLAZE_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BLAZE_DEATH;
	}
	
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float var1 = getBrightness(1.0F);
        }
        
        List<Entity> e = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).setFire(5 + rand.nextInt(7));
        }        
    }
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	public ItemStack getHeldItem() {
		return new ItemStack(JourneyItems.boilingBlade);
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		Item it = getItemDropped();
		this.dropItem(it, 1);
		if(rand.nextInt(14) == 0) dropItem(JourneyItems.boilPowder, 2);
		super.dropFewItems(b, j);
	    if(rand.nextInt(20) == 0) dropItem(JourneyItems.boilPowder, 4);
		super.dropFewItems(b, j); 
		if(rand.nextInt(40) == 0) dropItem(JourneyItems.blazingFireball, 1);
		super.dropFewItems(b, j); 
		
		}
	}