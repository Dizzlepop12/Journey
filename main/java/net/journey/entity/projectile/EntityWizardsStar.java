package net.journey.entity.projectile;

import java.util.Random;

import net.journey.client.render.particles.EntityWizardsFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityWizardsStar extends EntityBasicProjectile {

	public EntityWizardsStar(World var1) {
		super(var1);
	}
	
	public EntityWizardsStar(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			EntityFX effect = new EntityWizardsFX(this.worldObj, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}
}