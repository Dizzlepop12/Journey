package net.journey.book;

import java.awt.Color;
import java.util.ArrayList;

import amerifrance.guideapi.ModInformation;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageImage;
import amerifrance.guideapi.page.PageSound;
import amerifrance.guideapi.page.PageText;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.slayer.api.SlayerAPI;

public class BookOverworld {
	
	public static void journeyBook(Book book, String title) {
        PageText page2 = new PageText("wewewe.\n\nNew paragraph!");
        //PageImage page3 = new PageImage(new ResourceLocation(ModInformation.GUITEXLOC + "testimage.png"));
        //PageIRecipe page4 = new PageIRecipe(GameRegistry.addShapedRecipe(new ItemStack(Items.diamond), "XXX", "YYY", "ZZZ", 'X', Items.apple, 'Y', Blocks.beacon, 'Z', Items.beef));
        //ShapedOreRecipe shapedOreRecipe = new ShapedOreRecipe(Items.beef, "XXX", "YYY", "ZZZ", 'X', "stairWood", 'Y', "stone", 'Z', "ingotIron");
        //PageIRecipe page5 = new PageIRecipe(shapedOreRecipe);
        //ArrayList<ItemStack> shapelessList = new ArrayList<ItemStack>();
        //shapelessList.add(new ItemStack(Items.cauldron));
        //shapelessList.add(new ItemStack(Items.golden_carrot));
        //ShapelessRecipes shapelessRecipes = new ShapelessRecipes(new ItemStack(Items.blaze_rod), shapelessList);
        //PageIRecipe page6 = new PageIRecipe(shapelessRecipes);
        //ShapelessOreRecipe shapelessOreRecipe = new ShapelessOreRecipe(new ItemStack(Items.baked_potato), "ingotIron", "stairWood");
        //PageIRecipe page7 = new PageIRecipe(shapelessOreRecipe);
        //PageSound page8 = new PageSound(page6, "mob.pig.say");
        //PageFurnaceRecipe page9 = new PageFurnaceRecipe(new ItemStack(Items.potato));
        //PageFurnaceRecipe page10 = new PageFurnaceRecipe(new ItemStack(Items.diamond_axe));

        ArrayList<IPage> pagesStart = new ArrayList<IPage>();
        pagesStart.addAll(PageHelper.pagesForLongText("Hello! It seems you're about to begin your Journey. "
        		+ 									  "Getting started in this world is different from how "
        		+ 									  "you normally would start. The immediate thing to do "
        		+                                     "is to get some wood, and immediately make a shelter."
        		+                                     " It doesn't matter if it's made out of dirt. As long "
        		+                                     "as you're safe from the dangers during the day."));
        pagesStart.add(page2);

        EntryItemStack started1 = new EntryItemStack(pagesStart, "Part 1: First Spawning", new ItemStack(Blocks.log));
        EntryItemStack started2 = new EntryItemStack(pagesStart, "Part 2: Food", new ItemStack(JourneyItems.hongoShroom));
        EntryItemStack started4 = new EntryItemStack(pagesStart, "Part 3: Spelunking", new ItemStack(JourneyBlocks.greenGlowshroomTop));
        EntryItemStack started5 = new EntryItemStack(pagesStart, "Part 4: Structures", new ItemStack(JourneyBlocks.dungeonBrickCarved));
        ArrayList<EntryAbstract> entriesStarted = new ArrayList<EntryAbstract>();
        entriesStarted.add(started1);
        entriesStarted.add(started2);
        entriesStarted.add(started4);
        entriesStarted.add(started5);

        CategoryItemStack category1 = new CategoryItemStack(entriesStarted, "Getting Started", new ItemStack(Blocks.sapling));
        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(category1);

        book.setSpawnWithBook(true);
        book.setCategoryList(categories);
        book.setTitle(SlayerAPI.Colour.GOLD + title);
        book.setWelcomeMessage("Catagories:");
        book.setDisplayName(SlayerAPI.Colour.GOLD + title);
        book.setColor(new Color(100, 50, 0));
        GuideRegistry.registerBook(book, true);		
	}
}
