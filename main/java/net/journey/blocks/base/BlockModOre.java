package net.journey.blocks.base;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.client.render.particles.OreParticleFX;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockMod;

public class BlockModOre extends BlockMod {

	public BlockModOre(String name, String finalName) {
		super(name, finalName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random r) {
		renderParticle(worldIn, pos.getX(), pos.getY(), pos.getZ());
	}

	@SideOnly(Side.CLIENT)
	private void renderParticle(World w, int x, int y, int z) {
		float r = 0.0F, g = 0.0F, b = 0.0F;
		if(this == JourneyBlocks.celestiumOre || this == JourneyBlocks.celestiumBlock || this == JourneyBlocks.orbaditeOre || this == JourneyBlocks.orbaditeBlock) {
			r = 0.0F;
			g = 1.0F;
			b = 0.0F;
		}
		if(this == JourneyBlocks.shadiumOre || this == JourneyBlocks.shadiumBlock || this == JourneyBlocks.gorbiteOre || this == JourneyBlocks.desOre || 
				 this == JourneyBlocks.gorbiteBlock || this == JourneyBlocks.desBlock) {
			r = 0.0F;
			g = 0.0F;
			b = 1.0F;
		}
		if(this == JourneyBlocks.luniumOre || this == JourneyBlocks.luniumBlock || this == JourneyBlocks.storonOre || this == JourneyBlocks.storonBlock) {
			r = 1.0F;
			g = 1.0F;
			b = 0.0F;
		}
		if(this == JourneyBlocks.flairiumOre || this == JourneyBlocks.flairiumBlock) {
			r = 1.0F;
			g = 0.5F;
			b = 0.0F;
		}
		if(this == JourneyBlocks.hellstoneBlock || this == JourneyBlocks.hellstoneOre) {
			r = 1.0F;
			g = 0.0F;
			b = 0.0F;
		}
		if(this == JourneyBlocks.sapphireOre || this == JourneyBlocks.sapphireBlock || this == JourneyBlocks.luniteOre || this == JourneyBlocks.luniteBlock) {
			r = 0.5F;
			g = 0.0F;
			b = 1.0F;
		}
		if(this == JourneyBlocks.enderilliumOre || this == JourneyBlocks.enderilliumBlock || this == JourneyBlocks.mekyumOre || this == JourneyBlocks.mekyumBlock) {
			r = 1.0F;
			g = 0.0F;
			b = 1.0F;
		}
		
		if(this == JourneyBlocks.koriteOre || this == JourneyBlocks.koriteBlock) {
			r = 0.4F;
			g = 0.8F;
			b = 0.8F;
		}
		Random random = w.rand;
		double d0 = 0.0625D;
		for(int l = 0; l < 6; ++l) {
			double d1 = (double)((float)x + random.nextFloat());
			double d2 = (double)((float)y + random.nextFloat());
			double d3 = (double)((float)z + random.nextFloat());
			if(l == 0 && !w.getBlockState(new BlockPos(x, y + 1, z)).getBlock().isOpaqueCube()) d2 = (double)(y + 1) + d0;
			if(l == 1 && !w.getBlockState(new BlockPos(x, y - 1, z)).getBlock().isOpaqueCube()) d2 = (double)(y + 0) - d0;
			if(l == 2 && !w.getBlockState(new BlockPos(x, y, z + 1)).getBlock().isOpaqueCube()) d3 = (double)(z + 1) + d0;    
			if(l == 3 && !w.getBlockState(new BlockPos(x, y, z - 1)).getBlock().isOpaqueCube()) d3 = (double)(z + 0) - d0;
			if(l == 4 && !w.getBlockState(new BlockPos(x + 1, y, z)).getBlock().isOpaqueCube()) d1 = (double)(x + 1) + d0;
			if(l == 5 && !w.getBlockState(new BlockPos(x - 1, y, z)).getBlock().isOpaqueCube()) d1 = (double)(x + 0) - d0;
			if(d1 < (double)x || d1 > (double)(x + 1) || d2 < 0.0D || d2 > (double)(y + 1) || d3 < (double)z || d3 > (double)(z + 1)) {
				OreParticleFX var20 = new OreParticleFX(w, d1, d2, d3, r, g, b);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
			}
		}
	}
	
	@Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if(fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int j = random.nextInt(fortune + 2) - 1;
            if(j < 0) j = 0; 
            return this.quantityDropped(random) * (j + 1);
        } else {
            return 1;
        }
    }

	@Override
	public Item getItemDropped(IBlockState par1, Random par2, int par3) {
		if(this == JourneyBlocks.ashualOre) return JourneyItems.ash;
		if(this == JourneyBlocks.sapphireOre) return JourneyItems.sapphire;
		if(this == JourneyBlocks.enderilliumOre) return JourneyItems.enderilliumShard;
		if(this == JourneyBlocks.gorbiteOre) return JourneyItems.gorbiteGem;
		if(this == JourneyBlocks.luniteOre) return JourneyItems.luniteChunk;
		return Item.getItemFromBlock(this);
	}
}