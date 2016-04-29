package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.enums.EnumSounds;
import net.journey.util.Config;
import net.journey.util.LangRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.PlayerHelper;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;
import net.slayer.api.item.ItemModFood;

public class ItemHealth extends ItemFood {

	public int max;
	public double hearts;
	public boolean isSentry;
    public ItemHealth(String name, String actual, int hearts, int heal, float f, boolean sat, boolean b, int max, boolean isSentry) {
        super(heal, sat);
        LangRegistry.addItem(name, actual);
        setUnlocalizedName(name);
        this.hearts = hearts;
        this.isSentry = isSentry;
        this.setAlwaysEdible();
        this.max = max;
        setMaxStackSize(8);
        GameRegistry.registerItem(this, name);
        JourneyItems.itemNames.add(name);
        setCreativeTab(JourneyTabs.crops);
    }

    @Override
    protected void onFoodEaten(ItemStack i, World w, EntityPlayer p) {
    	if(p.getMaxHealth() < max /*60*/) {
    		p.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(p.getMaxHealth() + hearts);
    		PlayerHelper.getPersistedpTag(p).setDouble("health", p.getMaxHealth());
    		EnumSounds.playSound(EnumSounds.SUMMON_TABLE, w, p);
    	}
    	if(isSentry && p.getMaxHealth() >= max) {
    		p.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(p.getMaxHealth() + hearts);
    		PlayerHelper.getPersistedpTag(p).setDouble("health", p.getMaxHealth());
    		EnumSounds.playSound(EnumSounds.SUMMON_TABLE, w, p);
    	}
    }
	
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(SlayerAPI.Colour.RED + "Adds " + hearts / 2F + " Heart(s)");
		if(player.getMaxHealth() >= 60 && !isSentry) {
			list.add(SlayerAPI.Colour.DARK_RED + "You have reached the maximum amount of health. No more can be achieved without a Sentry's Heart");
		}
		if(player.getMaxHealth() > max && isSentry) {
			list.add(SlayerAPI.Colour.DARK_RED + "You have reached the maximum amount of health. No more can be achieved.");
		}
		if(isSentry) {
			list.add(SlayerAPI.Colour.GOLD + "Grants 10 more health points");
			list.add(SlayerAPI.Colour.GOLD + "Recommended only if player has reached max health");
		}
	}
}
