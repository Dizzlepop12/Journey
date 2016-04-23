package net.journey.blocks;

import net.journey.JourneyBlocks;
import net.journey.entity.mob.boss.EntityFourfa;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockPattern.PatternHelper;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import net.slayer.api.worldgen.WorldGenAPI;

public class BlockEucaPumpkin extends BlockMod {

	private BlockPattern fourfaBasePattern;
	private BlockPattern fourfaPattern;
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockEucaPumpkin(String name, String f) {
		super(EnumMaterialTypes.GOURD, name, f, 0.5F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setTickRandomly(true);
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.spawn(worldIn, pos);
	}

	private void spawn(World worldIn, BlockPos pos) {
		spawnEntity(new EntityFourfa(worldIn), getFourfaPattern(), worldIn, pos);
	}

	public void spawnEntity(Entity entity, BlockPattern blockPattern, World worldIn, BlockPos pos) {
		PatternHelper patternhelper;
		int i;
		int j;
		if ((patternhelper = blockPattern.match(worldIn, pos)) != null) {
			for (i = 0; i < blockPattern.getThumbLength(); ++i) {
				BlockWorldState blockworldstate = patternhelper.translateOffset(0, i, 0);
				worldIn.setBlockState(blockworldstate.getPos(), Blocks.air.getDefaultState(), 2);
			}
			BlockPos blockpos2 = patternhelper.translateOffset(0, 2, 0).getPos();
			entity.setLocationAndAngles((double)blockpos2.getX() + 0.5D, (double)blockpos2.getY() + 0.05D, (double)blockpos2.getZ() + 0.5D, 0.0F, 0.0F);
			worldIn.spawnEntityInWorld(entity);
			if(entity instanceof EntityFourfa) WorldGenAPI.addRectangle(2, 2, 3, worldIn, pos.getX() - 1, pos.getY() - 2, pos.getZ() - 1, Blocks.air);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && World.doesBlockHaveSolidTopSurface(worldIn, pos.down());
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING});
	}

	protected BlockPattern getFourfaPattern() {
		if(this.fourfaPattern == null)
			this.fourfaPattern = FactoryBlockPattern.start().aisle(new String[] {"^^", "OO", "##"}).where('O', BlockWorldState.hasState(BlockStateHelper.forBlock(JourneyBlocks.celestiumOre))).where('^', BlockWorldState.hasState(BlockStateHelper.forBlock(JourneyBlocks.eucaPumpkin))).where('#', BlockWorldState.hasState(BlockStateHelper.forBlock(JourneyBlocks.eucaStone))).build();
		return this.fourfaPattern;
	}
}