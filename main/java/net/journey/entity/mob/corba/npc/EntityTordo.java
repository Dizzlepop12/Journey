package net.journey.entity.mob.corba.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityTordo extends EntityModVillager {

	public EntityTordo(World par1World) {
		super(par1World);
		setSize(0.7F, 2.5F);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(3)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Tordo: You look like you could use some shiny new armor! Interested in an upgrade?");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Tordo: It may not seem to be any of my business, but... what exactly are you?");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Tordo: I have the strongest weapons of the land! Care to buy some?");
			break;
		}
		
	}

	@Override
	public GuiIDs guiID() {
		return GuiIDs.TORDO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 15), new ItemStack(JourneyItems.overseeingEye, 15), new ItemStack(JourneyItems.healersBlade, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 32), new ItemStack(JourneyItems.collectorRock, 32), new ItemStack(JourneyItems.treeHugger, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 32), new ItemStack(JourneyItems.collectorRock, 64), new ItemStack(JourneyItems.multiToolOfEternalSmelting, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 32), new ItemStack(JourneyItems.corbaStick, 10), new ItemStack(JourneyItems.treehuggersHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 32), new ItemStack(JourneyItems.corbaStick, 10), new ItemStack(JourneyItems.treehuggersChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 32), new ItemStack(JourneyItems.corbaStick, 10), new ItemStack(JourneyItems.treehuggersLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 32), new ItemStack(JourneyItems.corbaStick, 10), new ItemStack(JourneyItems.treehuggersBoots, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 10), new ItemStack(JourneyItems.overseeingEye, 32), new ItemStack(JourneyItems.leapersHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 10), new ItemStack(JourneyItems.overseeingEye, 32), new ItemStack(JourneyItems.leapersChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 10), new ItemStack(JourneyItems.overseeingEye, 32), new ItemStack(JourneyItems.leapersLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.natureTablet, 10), new ItemStack(JourneyItems.overseeingEye, 32), new ItemStack(JourneyItems.leapersBoots, 1)));
		
	}
}