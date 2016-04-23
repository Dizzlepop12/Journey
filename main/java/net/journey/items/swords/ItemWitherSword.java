package net.journey.items.swords;

import java.util.List;

import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModSword;

public class ItemWitherSword extends ItemModSword {

	public ItemWitherSword(String name, String f, EssenceToolMaterial toolMaterial) {
		super(name, f, toolMaterial);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase hit, EntityLivingBase player) {
		hit.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 5));
		return super.hitEntity(par1ItemStack, hit, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		infoList.add(SlayerAPI.Colour.DARK_GRAY + LangHelper.setWitherSword(6));
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}