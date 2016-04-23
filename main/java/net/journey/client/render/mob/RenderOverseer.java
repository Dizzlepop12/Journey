package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderOverseer extends RenderModMob {

	public RenderOverseer(ModelBase model, ResourceLocation tex) {
		super(model, tex);
	}
}