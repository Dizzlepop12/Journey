package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFireBomb extends EntityThrowable{

	public float damage;

	public EntityFireBomb(World var1) {
		super(var1);
	}
	public EntityFireBomb(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3);
		damage = dam;
	}
	public float getDamage() {
		return damage;
	}
	
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	@Override
    protected float getGravityVelocity()
    {
        return 0.08F;
    }

	@Override
    protected float getVelocity()
    {
        return 0.8F;
    }

	@Override
    protected float getInaccuracy()
    {
        return -10.0F;
    }

	@Override
	protected void onImpact(MovingObjectPosition j) {
			float f = (this.rand.nextFloat() - 0.0F) * 0.0F;
       		float f1 = (this.rand.nextFloat() - 0.0F) * 0.0F;
        	float f2 = (this.rand.nextFloat() - 0.0F) * 0.0F;
        	if(j.entityHit !=null) j.entityHit.setFire(10);
			this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + (double)f, this.posY + 0.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
			if(j.entityHit !=null) j.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,getThrower()), damage);
            this.worldObj.playAuxSFX(2002, new BlockPos(this), 5);
            int i = 3 + this.worldObj.rand.nextInt(5) + this.worldObj.rand.nextInt(5);
            this.setDead();
        }   
	}


