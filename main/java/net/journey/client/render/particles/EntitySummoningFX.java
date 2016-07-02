package net.journey.client.render.particles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.NoteBlockEvent.Note;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class EntitySummoningFX extends EntityFX {
	private ResourceLocation tex = new ResourceLocation(SlayerAPI.PREFIX + "textures/particle/summoning.png");
	private int textures;
	private int color;
	private float scale;
	private double texHeight;
	private int textureCount = 0;
	private int currentTexture = 0;
	
	public EntitySummoningFX(World world, double x, double y, double z, double motionx, double motiony, double motionz) {
		super(world, x, y, z, 0, 0, 0);
		this.posX = this.prevPosX = x;
		this.posY = this.prevPosY = y;
		this.posZ = this.prevPosZ = z;
		this.motionX = motionx;
		this.motionY = motiony;
		this.motionZ = motionz;
		this.particleMaxAge = (int)(Math.random() + 1.2F / 3F);
		this.noClip = true;
		this.texHeight = 1.0D / this.textures;
	}
	@Override
	public void renderParticle(WorldRenderer wr, Entity entity, float partialTicks, float rx, float rxz, float rz, float ryz, float rxy) {
		Tessellator tessel = Tessellator.getInstance();
		WorldRenderer worldrender = tessel.getWorldRenderer();
		float ipx = (float)((this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks) - this.interpPosX);
		float ipy = (float)((this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks) - this.interpPosY);
		float ipz = (float)((this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks) - this.interpPosZ);
		int prevTex = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.tex);
		float r = (float)(this.color >> 16 & 0xff) / 255F;
		float g = (float)(this.color >> 8 & 0xff) / 255F;
		float b = (float)(this.color & 0xff) / 255F;
		worldrender.begin(GL11.GL_QUADS, worldrender.getVertexFormat());
		this.getBrightnessForRender(partialTicks);
		this.setRBGColorF(r, g, b);
		wr.pos(ipx - rx * this.scale - ryz * this.scale, ipy - rxz * this.scale, (this.currentTexture + 1) * this.texHeight).endVertex();
		tessel.draw();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, prevTex);
	}
	@Override
	public int getFXLayer() {
		return 3;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!this.onGround) this.textureCount++;
		if(this.textureCount >= 3) this.textureCount = 0;
		this.currentTexture++;
		if(this.currentTexture >= this.textures) this.currentTexture = 0;
	}
}
	/*float summonScale;

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
}*/