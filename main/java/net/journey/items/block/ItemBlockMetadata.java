package net.journey.items.block;

import java.util.List;

import net.journey.blocks.BlockColouredBricks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.slayer.api.SlayerAPI.Colour;

public class ItemBlockMetadata extends ItemBlock {

	public ItemBlockMetadata(Block b) {
		super(b);
		hasSubtypes = true;
	}
	
	@Override
    public String getUnlocalizedName(ItemStack it)  {
        int i = MathHelper.clamp_int(it.getItemDamage(), 0, 13);
        return "tile." + BlockColouredBricks.textures[i] + "ColouredBricks";
    }
	
	@Override
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
		l.add(Colour.DARK_AQUA + "Just for decoration!");
	}
}