package net.journey.blocks;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.items.block.ItemMiniBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.EnumMaterialTypes;

public class BlockMiniColouredBricks extends Block {
	
	public static final PropertyEnum type = PropertyEnum.create("variant", BlockMiniColouredBricks.EnumMetadata.class);

	public static String[] textures = {"black", "blue", "brown", "cyan", "gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"};
	public static String[] names = {"Black", "Blue", "Brown", "Cyan", "Gray", "Lime", "Magenta", "Orange", "Pink", "Purple", "Red", "White", "Yellow"};

	public static ItemStack[] crafting = new ItemStack[] {
			new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 3),
			new ItemStack(Items.dye, 1, 6), new ItemStack(Items.dye, 1, 8), new ItemStack(Items.dye, 1, 10),
			new ItemStack(Items.dye, 1, 13), new ItemStack(Items.dye, 1, 14), new ItemStack(Items.dye, 1, 9), 
			new ItemStack(Items.dye, 1, 5), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 11)};

	public BlockMiniColouredBricks() {
		super(EnumMaterialTypes.STONE.getMaterial());
		setStepSound(EnumMaterialTypes.STONE.getSound());
		setCreativeTab(JourneyTabs.decoration);
		setHardness(2.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(type, BlockMiniColouredBricks.EnumMetadata.BLACK));
		GameRegistry.registerBlock(this, ItemMiniBlockMetadata.class, "blockMiniColouredBricks");
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, getStateFromMeta(stack.getItemDamage()), 2);
	}

	@Override
	public void getSubBlocks(Item it, CreativeTabs c, List l) {
		BlockMiniColouredBricks.EnumMetadata[] aenumtype = BlockMiniColouredBricks.EnumMetadata.values();
        int i = aenumtype.length;
        for(int j = 0; j < i; ++j) {
        	BlockMiniColouredBricks.EnumMetadata enumtype = aenumtype[j];
            l.add(new ItemStack(it, 1, enumtype.getMetadata()));
        }
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(type, BlockMiniColouredBricks.EnumMetadata.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((BlockMiniColouredBricks.EnumMetadata)state.getValue(type)).getMetadata();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {type});
	}
	
    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockMiniColouredBricks.EnumMetadata)state.getValue(type)).getMetadata();
    }

    public static enum EnumMetadata implements IStringSerializable {
        BLACK(0, "black"),
        BLUE(1, "blue"),
        BROWN(2, "brown"),
        CYAN(3, "cyan"),
        GRAY(4, "gray"),
        LIME(5, "lime"),
        MAGENTA(6, "magenta"),
        ORANGE(7, "orange"),
        PINK(8, "pink"),
        PURPLE(9, "purple"),
        RED(10, "red"),
        WHITE(11, "white"),
        YELLOW(12, "yellow");
        
        private static final BlockMiniColouredBricks.EnumMetadata[] META_LOOKUP = new BlockMiniColouredBricks.EnumMetadata[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumMetadata(int meta, String name) {
            this(meta, name, name);
        }

        private EnumMetadata(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockMiniColouredBricks.EnumMetadata byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            BlockMiniColouredBricks.EnumMetadata[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
            	BlockMiniColouredBricks.EnumMetadata var3 = var0[var2];
                META_LOOKUP[var3.getMetadata()] = var3;
            }
        }
    }
}