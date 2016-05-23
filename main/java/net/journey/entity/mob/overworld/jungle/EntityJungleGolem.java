package net.journey.entity.mob.overworld.jungle;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityJungleGolem extends EntityModMob{

	public EntityJungleGolem(World par1World) {
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
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 1));
        }        
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.overworldHealth;
	}

	@Override
    public void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
    }
	
	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BUSH;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BUSH_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BUSH_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {}
}