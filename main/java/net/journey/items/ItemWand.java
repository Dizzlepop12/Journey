package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.journey.entity.projectile.EntityChaosProjectile;
import net.journey.enums.EnumSounds;
import net.journey.util.LangHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class ItemWand extends ItemStaff {

	public ItemWand(String name, String f, boolean essence, int magic, int uses, int dam, boolean unbreakable, Class<? extends EntityBasicProjectile> projectile) {
		super(name, f, essence, magic, uses, dam, unbreakable, projectile);
	}
}