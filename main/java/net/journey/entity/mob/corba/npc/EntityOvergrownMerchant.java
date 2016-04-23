package net.journey.entity.mob.corba.npc;

import net.journey.JourneyItems;
import net.journey.client.GuiHandler.GuiIDs;
import net.journey.items.ItemPresent;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModVillager;

public class EntityOvergrownMerchant extends EntityModVillager {

	public EntityOvergrownMerchant(World var1) {
		super(var1);
	}

	@Override
	public void abstractInteract(EntityPlayer p) {
		switch(rand.nextInt(4)) {
		case 0:
			SlayerAPI.addFormattedChatMessage(p, "Merchant: This place isn't as terrible as it seems. At least, that's what they tell me.");
			break;
		case 1:
			SlayerAPI.addFormattedChatMessage(p, "Merchant: Some say this place was a Utopian World long ago. I personally think it's always been miserable...");
			break;
		case 2:
			SlayerAPI.addFormattedChatMessage(p, "Merchant: If you travel far enough, some speculate that you'll reach an Enchanted Jungle.");
			break;
		case 3:
			SlayerAPI.addFormattedChatMessage(p, "Merchant: My goods are enchanted with the greatest magic of the land! Not that it's anything special, however.");
			break;
		}
	}

	@Override
	public GuiIDs guiID() {
		return GuiIDs.OVERGROWN_MERCHANT;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public void addRecipies(MerchantRecipeList list) {
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 64), new ItemStack(JourneyItems.gorbiteGem, 16), new ItemStack(JourneyItems.overgrownHammer, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 64), new ItemStack(JourneyItems.orbaditeIngot, 16), new ItemStack(JourneyItems.overgrownStaff, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 64), new ItemStack(JourneyItems.gorbiteGem, 20), new ItemStack(JourneyItems.darkPineSword, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 48), new ItemStack(JourneyItems.gorbiteGem, 16), new ItemStack(JourneyItems.vinestrandBlade, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.collectorRock, 24), new ItemStack(JourneyItems.overseeingEye, 16), new ItemStack(JourneyItems.elderKey, 1)));
		
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 16), new ItemStack(JourneyItems.orbaditeIngot, 16), new ItemStack(JourneyItems.livegreenHelmet, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 16), new ItemStack(JourneyItems.orbaditeIngot, 16), new ItemStack(JourneyItems.livegreenChest, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 16), new ItemStack(JourneyItems.orbaditeIngot, 16), new ItemStack(JourneyItems.livegreenLegs, 1)));
		list.add(new MerchantRecipe(new ItemStack(JourneyItems.enchantedLeaf, 16), new ItemStack(JourneyItems.orbaditeIngot, 16), new ItemStack(JourneyItems.livegreenBoots, 1)));
	}
}