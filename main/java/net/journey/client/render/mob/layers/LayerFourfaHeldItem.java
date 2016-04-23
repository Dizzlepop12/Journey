package net.journey.client.render.mob.layers;

import org.lwjgl.opengl.GL11;

import net.journey.JourneyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerFourfaHeldItem implements LayerRenderer {
	
    private final RendererLivingEntity entity;

    public LayerFourfaHeldItem(RendererLivingEntity r) {
        this.entity = r;
    }

    @Override
    public void doRenderLayer(EntityLivingBase e, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
        ItemStack itemstack = e.getHeldItem();
        if(itemstack != null) {
            GlStateManager.pushMatrix();
            if(e instanceof EntityPlayer && ((EntityPlayer)e).fishEntity != null) 
                itemstack = new ItemStack(Items.fishing_rod, 0);
            Item item = itemstack.getItem();
            Minecraft minecraft = Minecraft.getMinecraft();
            GL11.glPushMatrix();
            GL11.glTranslatef(1.0F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON);
            GL11.glPopMatrix();     
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyItems.flameBow), ItemCameraTransforms.TransformType.THIRD_PERSON);
            GL11.glPopMatrix();          
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.75F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyItems.frozenBow), ItemCameraTransforms.TransformType.THIRD_PERSON);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(-1.25F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyItems.poisonBow), ItemCameraTransforms.TransformType.THIRD_PERSON);
            GL11.glPopMatrix();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}