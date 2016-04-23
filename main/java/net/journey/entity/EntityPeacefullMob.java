package net.journey.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import com.google.common.base.Predicate;

public abstract class EntityPeacefullMob extends EntityCreature implements IMob
{
    protected final EntityAIBase field_175455_a = new EntityAIAvoidEntity(this, null, new Predicate()
    {
        public boolean func_179911_a(Entity p_179911_1_)
        {
            return p_179911_1_ instanceof EntityCreeper && ((EntityCreeper)p_179911_1_).getCreeperState() > 0;
        }
        public boolean apply(Object p_apply_1_)
        {
            return this.func_179911_a((Entity)p_apply_1_);
        }
    }, 4.0F, 1.0D, 2.0D);

    public EntityPeacefullMob(World worldIn)
    {
        super(worldIn);
        this.experienceValue = 5;
        addBasicAI();
    }

    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();
        float f = this.getBrightness(1.0F);

        if (f > 0.5F)
        {
            this.entityAge += 2;
        }

        super.onLivingUpdate();
    }

    public double getHP(){return getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();}
	public double getMoveSpeed(){return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();}
	public double getAttackDamage(){return getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();}
	public double getFollowRange(){return getEntityAttribute(SharedMonsterAttributes.followRange).getAttributeValue();}
	public double getKnockbackResistance(){return getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();}

	protected void addAttackingAI() {
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.4D, false));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.4F, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	protected void addBasicAI(){
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWander(this, 1.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(setFollowRange());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(setMovementSpeed());
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(setKnockbackResistance());
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(setMaxHealth(new MobStats()));
	}

	public double setFollowRange(){return MobStats.follow;}
	public double setMovementSpeed(){return 0.200000011920929D;}
	public double setKnockbackResistance() {return MobStats.knockBackResistance;}

	public abstract double setMaxHealth(MobStats s);
	public abstract String setLivingSound();
	public abstract String setHurtSound();
	public abstract String setDeathSound();
	public abstract Item getItemDropped();

	@Override
	protected Item getDropItem() {
		return getItemDropped();
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		for(int i = 0; i < 1 + rand.nextInt(1); i++)
			this.dropItem(getItemDropped(), 1);
	}

	@Override
	protected String getLivingSound() {
		super.getLivingSound();
		return setLivingSound();
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox()) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
	}

    protected String getSwimSound()
    {
        return "game.hostile.swim";
    }

    protected String getSplashSound()
    {
        return "game.hostile.swim.splash";
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (super.attackEntityFrom(source, amount))
        {
            Entity entity = source.getEntity();
            return this.riddenByEntity != entity && this.ridingEntity != entity ? true : true;
        }
        else
        {
            return false;
        }
    }

    protected String getHurtSound()
    {
        return "game.hostile.hurt";
    }

    protected String getDeathSound()
    {
        return "game.hostile.die";
    }

    protected String getFallSoundString(int damageValue)
    {
        return damageValue > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (p_70652_1_ instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.func_152377_a(this.getHeldItem(), ((EntityLivingBase)p_70652_1_).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                p_70652_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                p_70652_1_.setFire(j * 4);
            }
            //this.func_174815_a(this, p_70652_1_);
        }

        return flag;
    }

    public float func_180484_a(BlockPos p_180484_1_)
    {
        return 0.5F - this.worldObj.getLightBrightness(p_180484_1_);
    }

    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.worldObj.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            return false;
        }
        else
        {
            int i = this.worldObj.getLightFromNeighbors(blockpos);

            if (this.worldObj.isThundering())
            {
                int j = this.worldObj.getSkylightSubtracted();
                this.worldObj.setSkylightSubtracted(10);
                i = this.worldObj.getLightFromNeighbors(blockpos);
                this.worldObj.setSkylightSubtracted(j);
            }

            return i <= this.rand.nextInt(8);
        }
    }

    protected boolean func_146066_aG()
    {
        return true;
    }
}