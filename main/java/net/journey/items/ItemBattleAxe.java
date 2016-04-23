package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.util.EssenceToolMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemModAxe;

public class ItemBattleAxe extends ItemModAxe {
	
	public ItemBattleAxe(String name, String f, EssenceToolMaterial m) {
		super(name, f, m);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.battleaxes);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
    	list.add(SlayerAPI.Colour.AQUA + "Battle Axe");
        list.add(SlayerAPI.Colour.BLUE + "Efficiency: " + toolMaterial.getEfficiencyOnProperMaterial());
        if (stack.getMaxDamage() != -1) list.add(stack.getMaxDamage() - stack.getItemDamage() + " Uses");
        else list.add(SlayerAPI.Colour.GREEN + "Infinite Uses");
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack i) {
    	return EnumAction.BLOCK;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack i) {
        return 72000;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
        p.setItemInUse(i, this.getMaxItemUseDuration(i));
        return i;
    }
}