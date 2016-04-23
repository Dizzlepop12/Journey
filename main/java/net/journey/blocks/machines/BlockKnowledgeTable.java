package net.journey.blocks.machines;

import java.util.Random;

import net.journey.JourneyTabs;
import net.journey.JITL;
import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.journey.client.GuiHandler.GuiIDs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockKnowledgeTable extends BlockModContainer {

	public BlockKnowledgeTable(String name, String f) {
		super(EnumMaterialTypes.STONE, name, f, 2.0F, JourneyTabs.machineBlocks);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityKnowledgeTable tile = (TileEntityKnowledgeTable)worldIn.getTileEntity(pos);
		if(!worldIn.isRemote) {
			if(tile != null) { 
				playerIn.openGui(JITL.instance, GuiIDs.KNOWLEDGE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityKnowledgeTable();
	}

	@Override
	public int getRenderType() {
		return 3;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}