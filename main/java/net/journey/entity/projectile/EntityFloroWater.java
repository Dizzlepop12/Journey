package net.journey.entity.projectile;

import java.util.Random;

import net.journey.client.render.particles.EntityFireballFX;
import net.journey.client.render.particles.EntityFloroWaterFX;
import net.journey.client.render.particles.EntityOvergrownFX;
import net.journey.client.render.particles.EntityWitherFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFloroWater extends EntityBasicProjectile {

	public EntityFloroWater(World var1) {
		super(var1);
	}
	
	public EntityFloroWater(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 20; ++i) {
			EntityFX effect = new EntityFloroWaterFX(this.worldObj, this.posX, this.posY - 1, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(var1.entityHit != null) { 
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
			((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(Potion.harm.id, 100, 1));
		}
		if(!worldObj.isRemote) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}