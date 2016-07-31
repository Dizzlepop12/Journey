package net.journey.book;

import amerifrance.guideapi.GuideAPI;
import amerifrance.guideapi.ModInformation;
import amerifrance.guideapi.api.GuideAPIItems;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.base.EntryBase;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.category.CategoryResourceLocation;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.entry.EntryResourceLocation;
import amerifrance.guideapi.page.*;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.slayer.api.SlayerAPI;

import java.awt.*;
import java.util.ArrayList;

public class JourneyBook {

    public static void registerTests(int amountOfBooks) {
        for (int i = 0; i < amountOfBooks; i++)
            journeyBook(new Book(), "Journey Guide");
    }

    public static void journeyBook(Book book, String title) {
        PageText page2 = new PageText("wewewe.\n\nNew paragraph!");
        PageImage page3 = new PageImage(new ResourceLocation(ModInformation.GUITEXLOC + "testimage.png"));
        PageIRecipe page4 = new PageIRecipe(GameRegistry.addShapedRecipe(new ItemStack(Items.diamond), "XXX", "YYY", "ZZZ", 'X', Items.apple, 'Y', Blocks.beacon, 'Z', Items.beef));
        ShapedOreRecipe shapedOreRecipe = new ShapedOreRecipe(Items.beef, "XXX", "YYY", "ZZZ", 'X', "stairWood", 'Y', "stone", 'Z', "ingotIron");
        PageIRecipe page5 = new PageIRecipe(shapedOreRecipe);
        ArrayList<ItemStack> shapelessList = new ArrayList<ItemStack>();
        shapelessList.add(new ItemStack(Items.cauldron));
        shapelessList.add(new ItemStack(Items.golden_carrot));
        ShapelessRecipes shapelessRecipes = new ShapelessRecipes(new ItemStack(Items.blaze_rod), shapelessList);
        PageIRecipe page6 = new PageIRecipe(shapelessRecipes);
        ShapelessOreRecipe shapelessOreRecipe = new ShapelessOreRecipe(new ItemStack(Items.baked_potato), "ingotIron", "stairWood");
        PageIRecipe page7 = new PageIRecipe(shapelessOreRecipe);
        PageSound page8 = new PageSound(page6, "mob.pig.say");
        PageFurnaceRecipe page9 = new PageFurnaceRecipe(new ItemStack(Items.potato));
        PageFurnaceRecipe page10 = new PageFurnaceRecipe(new ItemStack(Items.diamond_axe));

        ArrayList<IPage> pagesStart = new ArrayList<IPage>();
        pagesStart.add(page2);
        pagesStart.addAll(PageHelper.pagesForLongText("Hello! It seems you're about to begin your Journey. "
        		+ "									   To get started, you'll want to chop down a few trees, as you would normally do. "
        		+ "									   Creating a shelter is the most essential thing to do before you go out and explore", 
        		new ItemStack(Items.apple)));
        pagesStart.add(page3);
        pagesStart.add(page4);
        pagesStart.add(page5);
        pagesStart.add(page6);
        pagesStart.add(page7);
        pagesStart.add(page8);
        pagesStart.add(page9);
        pagesStart.add(page10);
        
        ArrayList<IPage> pagesDim = new ArrayList<IPage>();
        pagesDim.add(page2);
        pagesDim.addAll(PageHelper.pagesForLongText("Caving dood lol", new ItemStack(Items.apple)));
        pagesDim.add(page3);
        pagesDim.add(page4);
        pagesDim.add(page5);
        pagesDim.add(page6);
        pagesDim.add(page7);
        pagesDim.add(page8);
        pagesDim.add(page9);
        pagesDim.add(page10);

        EntryItemStack started1 = new EntryItemStack(pagesStart, "Part 1: First Spawning", new ItemStack(Blocks.log));
        EntryItemStack started2 = new EntryItemStack(pagesStart, "Part 2: Getting Food", new ItemStack(JourneyItems.hongoShroom));
        EntryItemStack started3 = new EntryItemStack(pagesStart, "Part 3: Getting Shelter", new ItemStack(Blocks.planks));
        EntryItemStack started4 = new EntryItemStack(pagesStart, "Part 4: Spelunking", new ItemStack(JourneyBlocks.greenGlowshroomTop));
        EntryItemStack started5 = new EntryItemStack(pagesStart, "Part 5: Structures", new ItemStack(JourneyBlocks.dungeonBrickCarved));
        ArrayList<EntryAbstract> entriesStarted = new ArrayList<EntryAbstract>();
        entriesStarted.add(started1);
        entriesStarted.add(started2);
        entriesStarted.add(started3);
        entriesStarted.add(started4);
        entriesStarted.add(started5);
        
        EntryItemStack dimension1 = new EntryItemStack(pagesDim, "Part 1: Adventuring", new ItemStack(JourneyItems.flameCoin));
        ArrayList<EntryAbstract> entriesDimension = new ArrayList<EntryAbstract>();
        entriesDimension.add(dimension1);
        
        EntryItemStack tools1 = new EntryItemStack(pagesDim, "Part 1: The New Tools", new ItemStack(JourneyItems.diamondMultiTool));
        ArrayList<EntryAbstract> entriesTools = new ArrayList<EntryAbstract>();
        entriesTools.add(tools1);
        
        EntryItemStack blocks1 = new EntryItemStack(pagesDim, "Part 1: The Grindstone", new ItemStack(JourneyBlocks.grindstone));
        EntryItemStack blocks2 = new EntryItemStack(pagesDim, "Part 2: The Summoning Table", new ItemStack(JourneyBlocks.summoningTable));
        ArrayList<EntryAbstract> entriesBlocks = new ArrayList<EntryAbstract>();
        entriesBlocks.add(blocks1);
        entriesBlocks.add(blocks2);

        CategoryItemStack category1 = new CategoryItemStack(entriesStarted, "Getting Started", new ItemStack(Blocks.sapling));
        CategoryItemStack category2 = new CategoryItemStack(entriesDimension, "Dimensions", new ItemStack(JourneyBlocks.eucaPortal));
        CategoryItemStack category3 = new CategoryItemStack(entriesTools, "Tools", new ItemStack(JourneyItems.multiToolOfEternalSmelting));
        CategoryItemStack category4 = new CategoryItemStack(entriesBlocks, "Utility Blocks", new ItemStack(JourneyBlocks.boilingChest));
        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);

        book.setSpawnWithBook(true);
        book.setCategoryList(categories);
        book.setTitle(SlayerAPI.Colour.GOLD + title);
        book.setWelcomeMessage("Catagories:");
        book.setDisplayName(SlayerAPI.Colour.GOLD + title);
        book.setColor(new Color(100, 50, 0));
        GuideRegistry.registerBook(book, true);		
    }
}