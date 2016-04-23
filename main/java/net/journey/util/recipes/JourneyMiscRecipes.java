package net.journey.util.recipes;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.BlockColouredBricks;
import net.journey.blocks.BlockMiniColouredBricks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JourneyMiscRecipes {

	public static void init() {
		initMiscCrafting();
	}

	public static void initMiscCrafting() {
		JourneyBlocks b = new JourneyBlocks();
		JourneyItems i = new JourneyItems();
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornHelmet), new Object[] {"idi", "d d", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornChest), new Object[] {"i i", "did", "idi", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornLegs), new Object[] {"idi", "d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.blazehornBoots), new Object[] {"d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundHelmet), new Object[] {"idi", "d d", 'd', Items.blaze_rod, 'i', JourneyBlocks.hellstoneBlock});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundChest), new Object[] {"i i", "did", "idi", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundLegs), new Object[] {"idi", "d d", "i i", 'd', Items.blaze_rod, 'i', JourneyItems.horn});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.fireboundBoots), new Object[] {"d d", "i i", 'd', Items.blaze_rod, 'i', JourneyBlocks.hellstoneBlock});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustHelmet), new Object[] {"idi", "d d", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustChest), new Object[] {"i i", "did", "idi", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustLegs), new Object[] {"idi", "d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bloodcrustBoots), new Object[] {"d d", "i i", 'd', JourneyItems.hellstoneIngot, 'i', JourneyItems.hellcrustIngot});
		
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockHelmet), new Object[] {"idi", "d d", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockChest), new Object[] {"i i", "did", "idi", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockLegs), new Object[] {"idi", "d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addRecipe(new ItemStack(JourneyItems.bleedrockBoots), new Object[] {"d d", "i i", 'd', JourneyBlocks.lavaRock, 'i', JourneyItems.concentratedBlood});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.woodMultiTool), new Object[] {Items.wooden_pickaxe, Items.wooden_shovel, Items.wooden_hoe, Items.wooden_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.stoneMultiTool), new Object[] {Items.stone_pickaxe, Items.stone_shovel, Items.stone_hoe, Items.stone_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.ironMultiTool), new Object[] {Items.iron_pickaxe, Items.iron_shovel, Items.iron_hoe, Items.iron_axe});
		GameRegistry.addShapelessRecipe(new ItemStack(JourneyItems.diamondMultiTool), new Object[] {Items.diamond_pickaxe, Items.diamond_shovel, Items.diamond_hoe, Items.diamond_axe});
		
		addOre(b.celestiumOre, i.celestiumIngot, b.celestiumBlock, i.celestiumAxe, i.celestiumPickaxe, i.celestiumShovel, i.celestiumHoe, i.celestiumSword, i.celestiumMultiTool, i.celestiumHelmet, i.celestiumChest, i.celestiumLegs, i.celestiumBoots, i.celestiumDust);
		addOre(b.hellstoneOre, i.hellstoneIngot, b.hellstoneBlock, i.hellstoneAxe, i.hellstonePickaxe, i.hellstoneShovel, i.hellstoneHoe, i.hellstoneSword, i.hellstoneMultiTool, i.hellstoneHelmet, i.hellstoneChest, i.hellstoneLegs, i.hellstoneBoots, i.hellstoneDust);
		addOre(b.flairiumOre, i.flairiumIngot, b.flairiumBlock, i.flairiumAxe, i.flairiumPickaxe, i.flairiumShovel, i.flairiumHoe, i.flairiumSword, i.flairiumMultiTool, i.flairiumHelmet, i.flairiumChest, i.flairiumLegs, i.flairiumBoots, i.flairiumDust);
		addOre(b.desOre, i.desIngot, b.desBlock, i.desAxe, i.desPickaxe, i.desShovel, i.desHoe, i.desSword, i.desMultiTool, null, null, null, null, null);
		addOre(b.shadiumOre, i.shadiumIngot, b.shadiumBlock, i.shadiumAxe, i.shadiumPickaxe, i.shadiumShovel, i.shadiumHoe, i.shadiumSword, i.shadiumMultiTool, i.shadiumHelmet, i.shadiumChest, i.shadiumLegs, i.shadiumBoots, i.shadiumDust);
		addOre(b.luniumOre, i.luniumIngot, b.luniumBlock, i.luniumAxe, i.luniumPickaxe, i.luniumShovel, i.luniumHoe, i.luniumSword, i.luniumMultiTool, i.luniumHelmet, i.luniumChest, i.luniumLegs, i.luniumBoots, i.luniumDust);
		addOre(b.sapphireOre, i.sapphire, b.sapphireBlock, i.sapphireAxe, i.sapphirePickaxe, i.sapphireShovel, i.sapphireHoe, i.sapphireSword, i.sapphireMultiTool, i.sapphireHelmet, i.sapphireChest, i.sapphireLegs, i.sapphireBoots, i.sapphireDust);
		addOre(b.gorbiteOre, i.gorbiteGem, b.gorbiteBlock, i.gorbiteAxe, i.gorbitePickaxe, i.gorbiteShovel, i.gorbiteHoe, i.gorbiteSword, i.gorbiteMultiTool, i.gorbiteHelmet, i.gorbiteChest, i.gorbiteLegs, i.gorbiteBoots, i.gorbiteDust);
		addOre(b.orbaditeOre, i.orbaditeIngot, b.orbaditeBlock, i.orbaditeAxe, i.orbaditePickaxe, i.orbaditeShovel, i.orbaditeHoe, i.orbaditeSword, i.orbaditeMultiTool, i.orbaditeHelmet, i.orbaditeChest, i.orbaditeLegs, i.orbaditeBoots, i.orbaditeDust);
		addOre(b.koriteOre, i.koriteIngot, b.koriteBlock, i.koriteAxe, i.koritePickaxe, i.koriteShovel, i.koriteHoe, i.koriteSword, i.koriteMultiTool, null, null, null, null, null);
		addOre(b.storonOre, i.storonIngot, b.storonBlock, i.storonAxe, i.storonPickaxe, i.storonShovel, i.storonHoe, i.storonSword, i.storonMultiTool, null, null, null, null, null);
		addOre(b.mekyumOre, i.mekyumIngot, b.mekyumBlock, i.mekyumAxe, i.mekyumPickaxe, i.mekyumShovel, i.mekyumHoe, i.mekyumSword, i.mekyumMultiTool, null, null, null, null, null);
		//addOre(b.ashualOre, i.ash, b.ashualBlock, null, null, null, null, null, null, null, null, null, null, i.ashDust);
	}
	
	public static void addBlock(Block made, Item used) {
		GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addBlock(Block made, Block used) {
		GameRegistry.addRecipe(new ItemStack(made), new Object[] {"iii", "iii", "iii", 'i', used});
	}
	
	public static void addOPFood(Item nonOP, Item OP, Item base) {
		GameRegistry.addRecipe(new ItemStack(nonOP), new Object[] {"iii", "ibi", "iii", 'i', Items.gold_ingot, 'b', base});
		GameRegistry.addRecipe(new ItemStack(OP), new Object[] {"iii", "ibi", "iii", 'i', Blocks.gold_block, 'b', base});

	}

	public static void addOre(Block ore, Item ingot, Block block, Item axe, Item pick, Item shovel, Item hoe, Item sword, Item multiTool, Item helmet, Item chest, Item legs, Item boots, Item dust) {
		addAxe(axe, ingot);
		addPickaxe(pick, ingot);
		addShovel(shovel, ingot);
		addHoe(hoe, ingot);
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"b", "b", "s", 'b', block, 's', Items.stick});
		addBlock(block, ingot);
		addHelmet(helmet, ingot);
		addChestplate(chest, ingot);
		addLeggings(legs, ingot);
		addBoots(boots, ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(multiTool), new Object[] {pick, shovel, hoe, axe});
		GameRegistry.addShapelessRecipe(new ItemStack(ingot, 9), new Object[] {block});
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0.5F);
		if(dust !=null)GameRegistry.addSmelting(dust, new ItemStack(ingot), 0.5F);
	}

	private static void addWood(Block log, Block plank, Block stair, int slabMeta, boolean smelt) {
		GameRegistry.addShapelessRecipe(new ItemStack(plank, 4), new Object[] {log});
		GameRegistry.addRecipe(new ItemStack(stair, 4), new Object[] {"i  ", "ii ", "iii", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[] {"i", "i", 'i', plank});
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table), new Object[] {"ii", "ii", 'i', plank});
		//GameRegistry.addRecipe(new ItemStack(EssenceBlocks.halfSlab, 6, slabMeta), new Object[] {"iii", 'i', plank});
		if(smelt) GameRegistry.addSmelting(log, new ItemStack(Items.coal), 0.5F);
	}

	private static void addAxe(Item axe, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {" ii", " si", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addPickaxe(Item pick, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(pick), new Object[] {"iii", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addShovel(Item shovel, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {" i ", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addSword(Item sword, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" i ", " i ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addHoe(Item hoe, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {" ii", " s ", " s ", 'i', ingot, 's', Items.stick});
	}

	private static void addHelmet(Item helmet, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(helmet), new Object[] {"iii", "i i", 'i', ingot});
	}

	private static void addChestplate(Item chest, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(chest), new Object[] {"i i", "iii", "iii", 'i', ingot});
	}

	private static void addLeggings(Item legs, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(legs), new Object[] {"iii", "i i", "i i", 'i', ingot});
	}

	private static void addBoots(Item boots, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(boots), new Object[] {"i i", "i i", 'i', ingot});
	}
}