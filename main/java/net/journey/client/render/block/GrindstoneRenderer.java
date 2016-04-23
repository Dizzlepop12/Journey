package net.journey.client.render.block;

import net.journey.JourneyBlocks;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.client.render.model.block.ModelGrindstone;
import net.journey.util.GL11Helper;
import net.journey.util.Helper;
import net.journey.util.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class GrindstoneRenderer extends TileEntitySpecialRenderer {

	private Minecraft mc = Minecraft.getMinecraft();
	private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	private ModelGrindstone stone = new ModelGrindstone();

	@Override
	public void renderTileEntityAt(TileEntity e, double x, double y, double z, float f, int i) {
		int rotation = 0;
		if(e.getWorld() != null) rotation = e.getBlockMetadata();    
		GL11.glPushMatrix();
		bindTexture(Textures.grindstone);
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180F, 0.0F, 0F, 1.0F);
		float scale = 1.0F;
		switch(rotation) {
		case 5:
			GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
			break;
		case 2:
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			break;
		case 3:
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			break;
		case 4:
			GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
			break;
		}
		GL11.glScalef(scale, scale, scale);
		if(((TileEntityGrindstone)e).isActivated()) {
			stone.render(0.0625F, false, ((TileEntityGrindstone)e).getRotaton());
			GL11.glPushMatrix();
			stone.setGrindstoneRotation();
			GL11.glPopMatrix();
		} else {
			stone.render(0.0625F, true, 0F);
		}
		GL11.glPopMatrix();
		Item toRender = ((TileEntityGrindstone)e).itemOnGrind;
		GL11.glPushMatrix();
		if(toRender != null) {
			switch(rotation) {
			case 5:
				GL11.glTranslated(x + 0.5, y + 1.2, z + 0.9);
				GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
				break;
			case 2:
				GL11.glTranslated(x + 0.08, y + 1.2, z + 0.5);
				break;
			case 3:
				GL11.glTranslated(x + 0.9, y + 1.2, z + 0.5);
				break;
			case 4:
				GL11.glTranslated(x + 0.55, y + 1.2, z + 1.0);
				GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
				break;
			}
			GL11Helper.scale(0.8F);
	        this.renderItem.renderItem(new ItemStack(toRender), ItemCameraTransforms.TransformType.GROUND);
		}
		GL11.glPopMatrix();
	}
}
