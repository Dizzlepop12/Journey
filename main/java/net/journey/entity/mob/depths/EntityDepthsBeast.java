package net.journey.entity.mob.depths;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDepthsBeast extends EntityModMob{

	public EntityDepthsBeast(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 2.5F);
	}
	
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float var1 = getBrightness(1.0F);
        }
        
        List<Entity> e = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 60, 1));
        }        
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.depthsHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.MAGMA_GIANT;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SPYCLOPS_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SPYCLOPS_HURT;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
		
	}
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.beastlyStomach, 2);
		super.dropFewItems(b, j);
		
		
	}
	
}