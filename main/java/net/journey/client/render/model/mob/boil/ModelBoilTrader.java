package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBoilTrader extends ModelBase {

	ModelRenderer head;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer top;

	public ModelBoilTrader() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 64);
		setRotation(head, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 2, 12, 4);
		rightarm.setRotationPoint(-3F, -8F, 0F);
		rightarm.setTextureSize(64, 64);
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 2, 12, 4);
		leftarm.setRotationPoint(5F, -8F, 0F);
		leftarm.setTextureSize(64, 64);
		setRotation(leftarm, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 16);
		top.addBox(0F, 0F, 0F, 10, 1, 10);
		top.setRotationPoint(-5F, -9F, -5F);
		top.setTextureSize(64, 64);
		setRotation(top, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		top.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
	}
}