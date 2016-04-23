package net.journey.entity.mob.boiling;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityModMob;

public class EntityScreamer extends EntityModMob {

	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;
	private int attackTimer;

	public EntityScreamer(World w) {
		super(w);
		this.experienceValue = 10;
        this.tasks.addTask(4, new EntityScreamer.AIFireballAttack());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(10) == 0) dropItem(JourneyItems.boilingSkull, 1);
		super.dropFewItems(b, j);
	    if(rand.nextInt(3) == 0) dropItem(JourneyItems.boilPowder, 6);
		super.dropFewItems(b, j);
		if(rand.nextInt(70) == 0) dropItem(JourneyItems.sizzlingEye, 1);
		super.dropFewItems(b, j); {
		

		}
	}
	
	@Override
	public void onLivingUpdate() {
		
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float var1 = getBrightness(1.0F);
		}
        
        List<Entity> e = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).setFire(5 + rand.nextInt(7));
        }        
        
		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        if (this.worldObj.isRemote)
        {
            if (this.rand.nextInt(24) == 0 && !this.isSilent())
            {
                this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		if (this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
	}

	@Override
	public void fall(float distance, float damageMultiplier) { }

	@Override
	public boolean isBurning() {
		return this.isFlying();
	}

	public boolean isFlying() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setFlying(boolean b) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		if(b) b0 = (byte)(b0 | 1);
		else b0 &= -2;
		this.dataWatcher.updateObject(16, Byte.valueOf(b0));
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
	public Item getItemDropped() {
		return Items.blaze_rod;
	}
	
    class AIFireballAttack extends EntityAIBase
    {
        private EntityScreamer field_179469_a = EntityScreamer.this;
        private int field_179467_b;
        private int field_179468_c;
        private static final String __OBFID = "CL_00002225";

        public AIFireballAttack()
        {
            this.setMutexBits(3);
        }

        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public void startExecuting()
        {
            this.field_179467_b = 0;
        }

        public void resetTask()
        {
            this.field_179469_a.setFlying(false);
        }

        public void updateTask()
        {
            --this.field_179468_c;
            EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
            double d0 = this.field_179469_a.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.field_179468_c <= 0)
                {
                    this.field_179468_c = 20;
                    this.field_179469_a.attackEntityAsMob(entitylivingbase);
                }

                this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D)
            {
                double d1 = entitylivingbase.posX - this.field_179469_a.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.field_179469_a.posY + (double)(this.field_179469_a.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;

                if (this.field_179468_c <= 0)
                {
                    ++this.field_179467_b;

                    if (this.field_179467_b == 1)
                    {
                        this.field_179468_c = 60;
                        this.field_179469_a.setFlying(true);
                    }
                    else if (this.field_179467_b <= 4)
                    {
                        this.field_179468_c = 6;
                    }
                    else
                    {
                        this.field_179468_c = 100;
                        this.field_179467_b = 0;
                        this.field_179469_a.setFlying(false);
                    }

                    if (this.field_179467_b > 1)
                    {
                        float f = MathHelper.sqrt_float(MathHelper.sqrt_double(d0)) * 0.5F;
                        this.field_179469_a.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);

                        for (int i = 0; i < 1; ++i)
                        {
                            EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.field_179469_a.worldObj, this.field_179469_a, d1 + this.field_179469_a.getRNG().nextGaussian() * (double)f, d2, d3 + this.field_179469_a.getRNG().nextGaussian() * (double)f);
                            entitysmallfireball.posY = this.field_179469_a.posY + (double)(this.field_179469_a.height / 2.0F) + 0.5D;
                            this.field_179469_a.worldObj.spawnEntityInWorld(entitysmallfireball);
                        }
                    }
                }

                this.field_179469_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.field_179469_a.getNavigator().clearPathEntity();
                this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }
    }
}