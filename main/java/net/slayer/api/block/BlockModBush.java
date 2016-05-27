package net.slayer.api.block;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

public class BlockModBush extends BlockMod implements IPlantable {

	private boolean isNether;
	Item berry;
	private static final PropertyInteger AGE = PropertyInteger.create("age", 0, 4);

	public BlockModBush(String name, String finalName, Item berry, boolean isNether) {
		super(EnumMaterialTypes.LEAVES, name, finalName, 1.0F);
		this.berry = berry;
		this.isNether = isNether;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		this.setLightOpacity(0);
		this.setCreativeTab(JourneyTabs.crops);
	}

	@Override
	public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing face, float x, float y, float z) {
		double 
		posX = player.posX, 
		posY = player.posY, 
		posZ = player.posZ;
		if (state.getValue(AGE) == 4) {
			if (w.isRemote) { 
				return true;
			}
			w.setBlockState(pos, this.getDefaultState(), 2);
			EntityItem entityItem = new EntityItem(w, posX, posY, posZ, new ItemStack(this.berry));
			w.spawnEntityInWorld(entityItem);
			return true;
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
		if (access.getBlockState(pos).getValue(AGE) == 1) {
			this.setBlockBounds(0, 0, 0, 0.3F, 0.3F, 0.3F);
		}
		
		if (access.getBlockState(pos).getValue(AGE) == 2) {
			this.setBlockBounds(0, 0, 0, 0.5F, 0.5F, 0.5F);
		}
		
		if (access.getBlockState(pos).getValue(AGE) == 3) {
			this.setBlockBounds(0, 0, 0, 0.7F, 0.7F, 0.7F);
		}
		
		if (access.getBlockState(pos).getValue(AGE) == 4) {
			this.setBlockBounds(0, 0, 0, 1.0F, 1.0F, 1.0F);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return null;
	}
	
	@Override
	public boolean canPlaceBlockAt(World w, BlockPos pos) {
		Block block = w.getBlockState(pos.down()).getBlock();
		if(block != Blocks.grass && this.isNether) {
			return block == Blocks.netherrack;
		}
		return false;
	}
	
	@Override
	public void updateTick(World w, BlockPos pos, IBlockState state, Random rand) {
		if (w.getBlockState(pos.down()).getBlock() == this) {
			int age = state.getValue(AGE).intValue();
			if (age < 4) {
				int chance = rand.nextInt(4);
				if (chance == 1) { 
					w.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
				}
			}
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE).intValue();
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, AGE);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return this.getDefaultState();
	}
}
