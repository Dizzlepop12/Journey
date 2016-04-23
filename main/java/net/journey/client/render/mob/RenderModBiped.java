package net.journey.client.render.mob;

import org.lwjgl.opengl.GL11;

import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class RenderModBiped<T extends EntityLiving> extends RenderBiped<T>   {

	public ResourceLocation tex;
	private float scale = 1.0F;
	
	public RenderModBiped(ModelBiped m, ResourceLocation tex) {
		super(Minecraft.getMinecraft().getRenderManager(), m, 0.5F);
		this.tex = tex;
	}
	
	public RenderModBiped(ModelBiped m, float size, ResourceLocation tex) {
		super(Minecraft.getMinecraft().getRenderManager(), m, 0.5F);
		this.tex = tex;
		this.scale = size;
	}
	
	@Override
	protected void preRenderCallback(T var1, float var2) {
		GL11.glScalef(scale, scale, scale);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float f, float partialTicks) {
		if(Minecraft.getMinecraft().gameSettings.showDebugInfo) {
			if(Config.showEntityHealth) {
				EntityLivingBase e = (EntityLivingBase)entity;
				renderHealth(e, EnumChatFormatting.GREEN + "Health: " + EnumChatFormatting.AQUA + (int)e.getHealth() + "/" + (int)e.getMaxHealth(), x, y, z, Config.entityHealthDistance);
			}
		}
		super.doRender(entity, x, y, z, f, partialTicks);
	}

	public void renderHealth(Entity e, String s, double x, double y, double z, int distance) {
		double d3 = e.getDistanceSqToEntity(this.renderManager.livingPlayer);
		if(d3 <= (double)(distance * distance)) {
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GlStateManager.pushMatrix();
			GlStateManager.translate((float)x + 0.0F, (float)y + e.height + 0.5F, (float)z);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(-f1, -f1, f1);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			byte b0 = 0;
			GlStateManager.disableTexture2D();
			worldrenderer.begin(GL11.GL_QUADS, worldrenderer.getVertexFormat());
			int j = fontrenderer.getStringWidth(s) / 2;
			worldrenderer.color(0.0F, 0.0F, 0.0F, 0.25F);
			worldrenderer.pos((double)(-j - 1), (double)(-1 + b0), 0.0D);
			worldrenderer.pos((double)(-j - 1), (double)(8 + b0), 0.0D);
			worldrenderer.pos((double)(j + 1), (double)(8 + b0), 0.0D);
			worldrenderer.pos((double)(j + 1), (double)(-1 + b0), 0.0D);
			tessellator.draw();
			GlStateManager.enableTexture2D();
			fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, 553648127);
			GlStateManager.enableDepth();
			GlStateManager.depthMask(true);
			fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, -1);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(T entity){
		return tex;
	}
}