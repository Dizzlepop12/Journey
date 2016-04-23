package net.journey.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentWaterWalk extends Enchantment {

	public EnchantmentWaterWalk(int id, int weight) {
		super(id, new ResourceLocation("waterWalk"), weight, EnumEnchantmentType.ARMOR_FEET);
		this.setName("Water Walker");
		Enchantment.addToBookList(this);
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() instanceof ItemArmor;
	}

    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public int getMinEnchantability(int par1) {
    	return 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        return super.canApplyTogether(e) && e.effectId != this.effectId || e.effectId != this.depthStrider.effectId;
    }
}