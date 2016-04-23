package net.journey.client.render.particles;

import net.journey.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

public class EntitySummoningFX extends EntityFX{

	public static final ResourceLocation texture = new ResourceLocation(SlayerAPI.MOD_ID, "textures/particle/summoning.png");
	private float scale;
	private int color;
	private double relativeTextureHeight;
	
	
	public EntitySummoningFX(World w, double x, double y, double z) {
		super(w, x, y, z);
		setMaxAge(particleMaxAge);
		setGravity(-0.01);
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.noClip = true;
	}
	
	@Override
	public void renderParticle(WorldRenderer wr, Entity e, float partialTicks, float f3, float f4, float f5, float f6, float f7) {
		Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
		GL11.glDepthMask(false);
		GL11.glEnable(GL_BLEND);		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);		
		wr.begin(GL11.GL_QUADS, wr.getVertexFormat());
		this.getBrightness(getBrightnessForRender(partialTicks));
		/**super.renderParticle(wr, e, partialTicks, f3, f7, f5, f6, f7);
        		GlStateManager.disableBlend();
        		GlStateManager.enableLighting(); */
		float scale = 0.1F*particleScale;
		float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
		float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
		float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
		wr.pos(x - f3 * scale - f6 * scale, y - f4 * scale, z - f5 * scale - f7 * scale).endVertex();;
		wr.pos(x - f3 * scale + f6 * scale, y + f4 * scale, z - f5 * scale + f7 * scale).endVertex();;
		wr.pos(x + f3 * scale + f6 * scale, y + f4 * scale, z + f5 * scale + f7 * scale).endVertex();;
		wr.pos(x + f3 * scale - f6 * scale, y - f4 * scale, z + f5 * scale - f7 * scale).endVertex();;
		Tessellator.getInstance().draw();
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);
	}
	
	public int getFXLayer(){
		return 3;
		
	}
	
	public EntitySummoningFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}
	
	public EntitySummoningFX setGravity(double d) {
		particleGravity = (float) d;
		return this;
	}
	
	public EntitySummoningFX setScale(float scale) {
		particleScale = scale;
		return this;		
	}
}