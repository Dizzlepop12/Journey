package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.journey.client.render.mob.layers.LayerBoomCharge;
import net.journey.client.render.model.mob.overworld.ModelBoomBoom;
import net.journey.entity.mob.overworld.EntityBoom;
import net.journey.util.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoomBoom extends RenderModMob {
	
    public RenderBoomBoom(ModelBase model, ResourceLocation tex) {
		super(model, tex);
		this.addLayer(new LayerBoomCharge(this));
	}

	private final ResourceLocation armoredBoomTextures = Textures.boomArmor;
    private final ResourceLocation boomTextures = Textures.boom;
    private final ModelBoomBoom boomModel = new ModelBoomBoom();
    
    protected void func_180570_a(EntityBoom p_180570_1_, float p_180570_2_)
    {
        float f1 = p_180570_1_.getFlashIntensity(p_180570_2_);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
        f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GlStateManager.scale(f3, f4, f3);
    }

    protected int func_180571_a(EntityBoom p_180571_1_, float p_180571_2_, float p_180571_3_)
    {
        float f2 = p_180571_1_.getFlashIntensity(p_180571_3_);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 16777215;
        }
    }

    protected ResourceLocation getEntityTexture(EntityBoom p_110775_1_)
    {
        return boomTextures;
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_180570_a((EntityBoom)p_77041_1_, p_77041_2_);
    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        return this.func_180571_a((EntityBoom)p_77030_1_, p_77030_2_, p_77030_3_);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityBoom)p_110775_1_);
    }
}