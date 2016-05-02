package net.journey.blocks;

import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityTrap;
import net.journey.entity.mob.corba.EntityOverseerElder;
import net.journey.entity.projectile.EntityBasicProjectile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockTrap extends BlockMod {

	public BlockTrap(String name, String finalName) {
		super(name, finalName);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override 
	public void onEntityCollidedWithBlock(World w, BlockPos pos, Entity e)  {
		EntityOverseerElder EntityOverseerElder = new EntityOverseerElder(w);
		if(!w.isRemote) {
			try {
				w.spawnEntityInWorld(EntityOverseerElder);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}