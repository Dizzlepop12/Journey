package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.LangRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModFood extends ItemFood {

	private int time = 32;
	
    public ItemModFood(String name, String f, int food, float sat, boolean wolfFood) {
        super(food, sat, wolfFood);
        LangRegistry.addItem(name, f);
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.crops);
        JourneyItems.itemNames.add(name);
        GameRegistry.registerItem(this, name);
    }
    
    public ItemModFood(String name, String f, int food, float sat, int timeToEat, boolean wolfFood) {
       this(name, f, food, sat, wolfFood);
       time = timeToEat;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
    	return time;
    }

    public ItemModFood(String name, String f, int food, float sat, boolean wolfFood, int potionID, int potionDuration, int potionAmplifier, float potionEffectProbability) {
        this(name, f, food, sat, wolfFood);
        setPotionEffect(potionID, potionDuration, potionAmplifier, potionEffectProbability);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		ItemDescription.addInformation(stack, player, list);
        list.add("Fills " + (double) getHealAmount(stack) / 2 + " Hunger Bars");
        list.add(getSaturationModifier(stack) + " Saturation");
        if(time <= 32) list.add("Faster eating");
    }
}