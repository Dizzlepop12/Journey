package net.journey.client.render.mob;

import net.journey.entity.EntityDragonEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class RenderDragonEgg extends RenderLiving {

	private ResourceLocation loc;

	public RenderDragonEgg(ModelBase model) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e) {
		EntityDragonEgg egg = (EntityDragonEgg)e;		
		String colour = "";
		switch(egg.getColour()) {
		case 0: colour = "blue"; break;
		case 1: colour = "cyan"; break;
		case 2: colour = "green"; break;
		case 3: colour = "orange"; break;
		case 4: colour = "purple"; break;
		case 5: colour = "red"; break;
		case 6: colour = "yellow"; break;
		case 7: colour = "pink"; break;
		}
		return new ResourceLocation(SlayerAPI.PREFIX + "models/entity/dragonEgg_" + colour + ".png");
	}
}