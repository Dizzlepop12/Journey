package net.journey.client.render.mob.layers;

import net.journey.entity.mob.pet.EntityShiverwolf;
import net.minecraft.client.renderer.GlStateManager;
import net.journey.client.render.mob.RenderShiverwolf;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class LayerShiverwolfCollar implements LayerRenderer<EntityShiverwolf> {
    private static final ResourceLocation WOLF_COLLAR = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/shiverwolf_collar.png");
    private final RenderShiverwolf wolfRenderer;

    public LayerShiverwolfCollar(RenderShiverwolf wolfRendererIn) {
        this.wolfRenderer = wolfRendererIn;
    }

	@Override
	public void doRenderLayer(EntityShiverwolf entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible()) {
            this.wolfRenderer.bindTexture(WOLF_COLLAR);
            EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entitylivingbaseIn.getCollarColor().getMetadata());
            float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
            GlStateManager.color(afloat[0], afloat[1], afloat[2]);
            this.wolfRenderer.getMainModel().render(entitylivingbaseIn, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, scale);
        }
    }

	@Override
    public boolean shouldCombineTextures() {
        return true;
	}
}