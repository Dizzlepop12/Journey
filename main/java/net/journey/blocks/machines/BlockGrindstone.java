package net.journey.blocks.machines;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;

public class BlockGrindstone extends BlockContainer {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockGrindstone(String name) {
		super(EnumMaterialTypes.STONE.getMaterial());
		LangRegistry.addBlock(name, "Grindstone");
		setUnlocalizedName(name);
		setStepSound(soundTypeStone);
		setHardness(3.0F);
		setCreativeTab(JourneyTabs.machineBlocks);
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityGrindstone();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullBlock() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		TileEntityGrindstone stone = (TileEntityGrindstone)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote) {
			if(worldIn.isBlockPowered(pos)) {
				stone.setActivated(true);
			} else {
				stone.setActivated(false);
			}
		}
	}

	@Override
	public int getRenderType() {
		return 2;
	}

	//public void setBlockBounds(int stage) {
	//	this.setBlockBounds(0f, 0, 0f, 4f, 1.0f, 1f);
	//}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean b) {
		onBlockDestroyed(world, pos);
		return super.removedByPlayer(world, pos, player, b);
	}

	private void onBlockDestroyed(World world, BlockPos pos) {
		if(!world.isRemote) {
			if(((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind != null) {
				Item item = ((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind;
				EntityItem var3 = null;
				if(item != null) var3 = new EntityItem(world, pos.getX(), pos.getY() + maxY, pos.getZ(), new ItemStack(item));
				world.spawnEntityInWorld(var3);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) return false;
		TileEntityGrindstone stone = (TileEntityGrindstone) world.getTileEntity(pos);
		if(((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind != null) {
			Item item = ((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind;
			if(item == null) {
				((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind = null;
				return true;
			}
			if(stone.getItem() instanceof ItemBlock) SlayerAPI.giveItemStackToPlayer(player, 1, new ItemStack(item));
			else SlayerAPI.giveItemStackToPlayer(player, new Random().nextInt(3) + 1, new ItemStack(item));
			((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind = null;
			((TileEntityGrindstone) world.getTileEntity(pos)).state = 0;
		}
		Item item = player.getCurrentEquippedItem().getItem();
		if(player.getCurrentEquippedItem() != null) {
			if(item == SlayerAPI.toItem(JourneyBlocks.celestiumOre) || item == SlayerAPI.toItem(JourneyBlocks.hellstoneOre) || item == SlayerAPI.toItem(JourneyBlocks.shadiumOre) 
					|| item == SlayerAPI.toItem(JourneyBlocks.luniumOre) || item == SlayerAPI.toItem(JourneyBlocks.flairiumOre) || item == SlayerAPI.toItem(JourneyBlocks.ashualOre) ||
					item == SlayerAPI.toItem(JourneyBlocks.sapphireOre) || item == SlayerAPI.toItem(JourneyBlocks.enderilliumOre) || item == SlayerAPI.toItem(Blocks.gold_ore) || item == SlayerAPI.toItem(Blocks.diamond_ore)
					|| item == SlayerAPI.toItem(Blocks.iron_ore) || item == SlayerAPI.toItem(JourneyBlocks.gorbiteOre) || item == SlayerAPI.toItem(JourneyBlocks.orbaditeOre)) {
				if(stone.getItem() == null) {
					((TileEntityGrindstone) world.getTileEntity(pos)).itemOnGrind = player.getCurrentEquippedItem().getItem();
					player.getCurrentEquippedItem().stackSize--;
					world.playAuxSFX(1022, pos, 0);
					((WorldServer)world).getPlayerManager().markBlockForUpdate(pos);
					return true;
				}
			}
		}
		((WorldServer)world).getPlayerManager().markBlockForUpdate(pos);
		return false;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
		state = state.withProperty(FACING, enumfacing);
		worldIn.setBlockState(pos, state, 2);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if(enumfacing.getAxis() == EnumFacing.Axis.Y) enumfacing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING});
	}
}