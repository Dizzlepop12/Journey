package net.slayer.api.entity;

import net.journey.client.render.particles.EntityModFireFX;
import net.journey.client.render.particles.EntityModLavaFX;
import net.journey.enums.EnumSounds;
import net.journey.util.IEssenceBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public abstract class EntityEssenceBoss extends EntityModMob implements IEssenceBoss {

	private int deathTicks;
	public EntityEssenceBoss(World par1World) {
		super(par1World);
		isImmuneToFire = true;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public final float getModHealth() {
		return super.getHealth();
	}
	
	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
    }
    
    @Override
    public float getSoundVolume() {
    	return 10.0F;
    }
	
	@Override
	public float getModMaxHealth() {
		return getMaxHealth();
	}

	@Override
	protected void onDeathUpdate()
    {
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 1.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 3.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 2.5D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 1.5D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (double)f, this.posY + 3.5D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
         
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + 0.0D + (double)f, this.posY + 1.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + 0.0D + (double)f, this.posY + 3.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + 0.0D + (double)f, this.posY + 1.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + 0.0D + (double)f, this.posY + 1.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + 0.0D + (double)f, this.posY + 3.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + 0.0D + (double)f, this.posY + 1.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
        }

        int i;
        int j;

        if (!this.worldObj.isRemote)
        {
            {
                i = 1000;

                while (i > 0)
                {
                    j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }
        }
        this.renderYawOffset = this.rotationYaw += 20.0F;
        {
            i = 2000;

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
            }
            this.setDead();
        }
    }
}