package net.journey.items;

import net.journey.JourneyTabs;
import net.journey.util.Config;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.PlayerHelper;
import net.slayer.api.item.ItemMod;

public class ItemHealth extends ItemMod {

	public double hearts;
	public ItemHealth(String n, String fn, double hearts) {
		super(n, fn, JourneyTabs.items);
		this.hearts = hearts;
		this.setMaxStackSize(8);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World w, EntityPlayer p) {
		if(p.getMaxHealth() < Config.maximumHealthIncrease) {
			{
				p.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(p.getMaxHealth() + hearts);
				PlayerHelper.getPersistedPlayerTag(p).setDouble("health", p.getMaxHealth());
				--item.stackSize;
			}
		}
		return item;
	}
}
