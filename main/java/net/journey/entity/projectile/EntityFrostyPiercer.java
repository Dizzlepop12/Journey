package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFrostyPiercer extends EntityThrowable {

	public float damage;
	public EntityLivingBase thrower;
	protected int bounces, maxBounces;

	public EntityFrostyPiercer(World var1) {
		super(var1);
	}

	public EntityFrostyPiercer(World var1, EntityLivingBase var3, float dam, int max) {
		super(var1, var3);
		this.damage = dam;
		this.thrower = var3;
		this.maxBounces = max;
	}

	@Override
	protected void onImpact(MovingObjectPosition par1) {
		if(par1.entityHit != null && par1.entityHit != this.thrower) {
			par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
			if(!this.worldObj.isRemote) this.setDead();
			return;
		}
		if(par1.sideHit == EnumFacing.UP || par1.sideHit == EnumFacing.DOWN) {
			this.motionY *= -1.0D;
		} else if(par1.sideHit == EnumFacing.SOUTH || par1.sideHit == EnumFacing.NORTH) {
			this.motionZ *= -1.0D;
		} else if(par1.sideHit == EnumFacing.EAST || par1.sideHit == EnumFacing.WEST) {
			this.motionX *= -1.0D;
		}		
		this.bounces++;
		if(this.bounces == maxBounces) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.031F;
	}
}