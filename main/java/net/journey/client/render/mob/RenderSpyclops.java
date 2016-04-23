package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.journey.entity.mob.overworld.EntitySpyclops;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class RenderSpyclops extends RenderModMob {

	public RenderSpyclops(ModelBase model) {
		super(model, null);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		String[] tex = {"spyclops_0", "spyclops_1", "spyclops_2", "spyclops_3"};
		String name = "";
		switch(var1.getDataWatcher().getWatchableObjectInt(EntitySpyclops.ENTITY_TYPE)) {
		case 0:
			name = tex[0];
			break;
		case 1:
			name = tex[1];
			break;
		case 2:
			name = tex[2];
			break;
		case 3:
			name = tex[3];
			break;
		}
		return new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/" + name + ".png");
	}
}