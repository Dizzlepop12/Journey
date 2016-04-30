package net.journey.blocks.portal;

import java.util.List;
import java.util.Random;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.dimension.corba.TeleporterCorba;
import net.journey.dimension.senterian.TeleporterSenterian;
import net.journey.enums.EnumSounds;
import net.journey.util.Config;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.EnumToolType;
import net.slayer.api.SlayerAPI;

public class BlockSenterianPortal extends Block{

	protected EnumMaterialTypes blockType;
	protected Item drop = null;
	protected Random rand;
	public int boostBrightnessLow;
	public int boostBrightnessHigh;
	public boolean enhanceBrightness;
	public String name;
	protected boolean isNormalCube = true;
	
	public BlockSenterianPortal(String name, String finalName, float hardness) {
		this(EnumMaterialTypes.STONE, name, finalName, hardness, JourneyTabs.portalBlocks);
	}

	public BlockSenterianPortal(String name, String finalName) {
		this(EnumMaterialTypes.STONE, name, finalName, 2.0F, JourneyTabs.portalBlocks);
	}

	public BlockSenterianPortal(EnumMaterialTypes type, String name, String finalName, float hardness) {
		this(type, name, finalName, hardness, JourneyTabs.blocks);
	}

	public BlockSenterianPortal(String name, String finalName, boolean breakable, CreativeTabs tab) {
		this(EnumMaterialTypes.STONE, name, finalName, tab);
	}

	public BlockSenterianPortal(String name, String finalName, boolean breakable) {
		this(name, finalName, breakable, JourneyTabs.blocks);
	}
	
    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
    	
    }
    
	public BlockSenterianPortal(EnumMaterialTypes blockType, String name, String finalName, CreativeTabs tab) {
		super(blockType.getMaterial());
		LangRegistry.addBlock(name, finalName);
		this.blockType = blockType;
		setHardness(2.0F);
		rand = new Random();
		setStepSound(blockType.getSound());
		setCreativeTab(tab);
		setUnlocalizedName(name);
		this.name = name; 
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	public BlockSenterianPortal(EnumMaterialTypes blockType, String name, String finalName, float hardness, CreativeTabs tab) {
		super(blockType.getMaterial());
		LangRegistry.addBlock(name, finalName);
		this.blockType = blockType;
		rand = new Random();
		setStepSound(blockType.getSound());
		setCreativeTab(tab);
		setUnlocalizedName(name);
		setHardness(hardness);
		this.name = name;
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}

	public Block addName(String name) {
		JourneyBlocks.blockName.add(name);
		return this;
	}

	public String getName() {
		return name;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(drop == null) return SlayerAPI.toItem(this);
		return drop;
	}

	public BlockSenterianPortal setHarvestLevel(EnumToolType type) {
		setHarvestLevel(type.getType(), type.getLevel());
		return this;
	}

	@Override
	public int getRenderType() {
		return 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if((entity.ridingEntity == null) && (entity.riddenByEntity == null) && ((entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
			thePlayer.triggerAchievement(JourneyAchievements.achievementCorba);
			int dimensionID = Config.senterian;
			Block blockFrame = JourneyBlocks.corbaStone;
			if(thePlayer.timeUntilPortal > 0) 
				thePlayer.timeUntilPortal = 10;
			else if(thePlayer.dimension != dimensionID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, dimensionID, new TeleporterSenterian(thePlayer.mcServer.worldServerForDimension(dimensionID)));

			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterSenterian(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (rand.nextInt(100) == 0)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }
        double d0 = (double)((float)pos.getX() + rand.nextFloat());
        double d1 = (double)((float)pos.getY() + 0.8F);
        double d2 = (double)((float)pos.getZ() + rand.nextFloat());
        double d3 = 0.0D;
        double d4 = 0.0D;
        double d5 = 0.0D;
        worldIn.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d0, d1, d2, d3, d4, d5, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, d3, d4, d5, new int[0]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return SlayerAPI.toItem(this);
    }

    @Override
    public MapColor getMapColor(IBlockState state) {
        return MapColor.obsidianColor;
    }
}