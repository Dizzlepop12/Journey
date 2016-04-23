package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ArmorDescription;
import net.journey.client.ItemDescription;
import net.journey.client.server.DarkEnergyBar;
import net.journey.enums.EnumSounds;
import net.journey.util.LangRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemMod extends Item {

	protected int healAmount = 0;

	public ItemMod(String name, String finalName){
		this(name, finalName, JourneyTabs.items);
	}

	public ItemMod(String name, String finalName, CreativeTabs tab){
		LangRegistry.addItem(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);
	}

	public ItemMod setHealAmount(int healAmount){
		this.healAmount = healAmount;
		return this;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(healAmount != 0){
			if(player.getHealth() < player.getMaxHealth()){
				player.heal(healAmount);
				player.inventory.consumeInventoryItem(this);
			}
		}
		return stack;
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			if(magic) w.spawnEntityInWorld(entity);
		}
		if(magic) {
			EnumSounds.playSound(sound, w, p);
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, String sound, boolean damage, ItemStack item, int dam) {
		if(!w.isRemote){
			w.spawnEntityInWorld(entity);
			EnumSounds.playSound(sound, w, p);
			if(damage) item.damageItem(dam, p);
		}
	}

	public void spawnEntityIntoWorld(World w, EntityPlayer p, Entity entity, boolean magic, String sound) {
		spawnEntityIntoWorld(w, p, entity, magic, sound, false, new ItemStack(Items.apple), 0);
	}
	

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list){ }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		ItemDescription.addInformation(item, player, list);
		addInformation(item, player, list);
	}
}