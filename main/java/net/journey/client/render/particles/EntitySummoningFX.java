package net.journey.client.render.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntitySummoningFX extends EntityFX {
	float summonScale;

	public EntitySummoningFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float x, float y, float z) {
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, 1.0F, x, y, z);
	}

	public EntitySummoningFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float idek, float x, float y, float z) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.1D;
		this.motionY *= 0.1D;
		this.motionZ *= 0.1D;
		this.particleGravity = -1.0F;
		if (x == 0.0F) {
			x = 1.0F;
		}
		this.particleGreen = 0.5F;
		this.particleRed = 0.0F;
		this.particleBlue = 0.5F;
		this.particleScale *= 0.75F;
		this.particleScale *= idek;
		this.summonScale = this.particleScale;
		this.setGravity(-0.01);
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
		this.noClip = false;
	}

	@Override
	public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float par4, float par5, float par6, float par7, float par8) {
		float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F;
		f = MathHelper.clamp_float(f, 0.0F, 1.0F);
		this.particleScale = this.summonScale * f;
		super.renderParticle(worldRendererIn, entityIn, partialTicks, par4, par5, par6, par7, par8);
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

        this.setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
	
	public EntitySummoningFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}
	
	public EntitySummoningFX setGravity(double d) {
		particleGravity = (float) d;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
		{
			return new EntitySummoningFX(worldIn, xCoordIn, yCoordIn, zCoordIn, (float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
		}
	}
}