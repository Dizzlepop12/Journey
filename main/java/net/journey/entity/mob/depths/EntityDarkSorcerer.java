package net.journey.entity.mob.depths;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityBouncingProjectile;
import net.journey.entity.projectile.EntityGreenpace;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDarkSorcerer extends EntityModMob implements IRangedAttackMob {

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 15, 60);

	public EntityDarkSorcerer(World par1World) {
		super(par1World);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
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
	
	public void setCombatTask() {
		this.tasks.removeTask(this.aiArrowAttack); {
			this.tasks.addTask(4, this.aiArrowAttack);
		}
	}
	
	@Override
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		super.setCurrentItemOrArmor(par1, par2ItemStack);
		if(!this.worldObj.isRemote && par1 == 0) {
			this.setCombatTask();
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
        EntityBouncingProjectile b = new EntityBouncingProjectile(this.worldObj);
        b.setThrowableHeading(e.posX-this.posX, e.posY-this.posY, e.posZ-this.posZ, 1.6f, 12);
        EnumSounds.playSound(EnumSounds.SPARKLE, worldObj, this);
        this.worldObj.spawnEntityInWorld(b);
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
		return EnumSounds.SORCERER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SORCERER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SORCERER_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(90) == 0) dropItem(JourneyItems.darkOrb, 1);
		super.dropFewItems(b, j);
	}
}