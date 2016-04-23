package net.slayer.api.item;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.journey.util.EssenceToolMaterial;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModAxe extends ItemAxe {

	protected EssenceToolMaterial mat;

	public ItemModAxe(String name, String f, EssenceToolMaterial tool) {
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
	public float getStrVsBlock(ItemStack stack, Block block) {
		return block.getMaterial() != Material.wood && block.getMaterial() != Material.web && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.getStrVsBlock(stack, block) : this.efficiencyOnProperMaterial;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
		ItemDescription.addInformation(item, player, infoList);
		infoList.add(SlayerAPI.Colour.BLUE + LangHelper.getEfficiency() + ": " + toolMaterial.getEfficiencyOnProperMaterial());
		if(item.getMaxDamage() != -1) infoList.add(item.getMaxDamage() - item.getItemDamage() + " " + LangHelper.getUsesRemaining());
		else infoList.add(SlayerAPI.Colour.GREEN + LangHelper.getInfiniteUses());
	}
}