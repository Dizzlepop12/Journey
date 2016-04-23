package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import static net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityRockProjectile extends EntityThrowable {

	private float damage = 9.0F;
	
	public EntityRockProjectile(World w) {
		super(w);
	}
	
	public EntityRockProjectile(World worldIn, EntityLivingBase throwerIn, float damage) {
		super(worldIn, throwerIn);
		this.damage = damage;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit != null) mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		if(!worldObj.isRemote) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
        return 0.035F;
    }
}