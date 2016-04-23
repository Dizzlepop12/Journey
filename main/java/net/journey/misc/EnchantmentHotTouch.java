package net.journey.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;

public class EnchantmentHotTouch extends Enchantment {

	public EnchantmentHotTouch(int id, int weight) {
		super(id, new ResourceLocation("hotTouch"), weight, EnumEnchantmentType.DIGGER);
		this.setName("Hot Touch");
		Enchantment.addToBookList(this);
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() instanceof ItemTool;
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
        return super.canApplyTogether(e) && e.effectId != this.effectId && e.effectId != e.fortune.effectId && e.effectId != e.silkTouch.effectId;
    }
}