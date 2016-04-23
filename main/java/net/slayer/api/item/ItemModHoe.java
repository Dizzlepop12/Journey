package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModHoe extends ItemHoe {

	protected EssenceToolMaterial mat;
	
    public ItemModHoe(String name, String f, EssenceToolMaterial tool) {
        super(tool.getToolMaterial());
        LangRegistry.addItem(name, f);
		mat = tool;
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.tools);
        JourneyItems.itemNames.add(name);
        GameRegistry.registerItem(this, name);
    }
    
	@Override
	public boolean isItemTool(ItemStack i) {
		return true;
	}
    
	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack i1) {
		boolean canRepair = mat.getRepairItem() != null;
		if(canRepair) return mat.getRepairItem() == i1.getItem() ? true : super.getIsRepairable(i, i1);
		return super.getIsRepairable(i, i1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}