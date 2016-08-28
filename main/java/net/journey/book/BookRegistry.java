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

public class BookRegistry {

    public static void initBooks() {
        for (int i = 0; i < 1; i++)
            BookOverworld.journeyBook(new Book(), "Journey - Overworld Guide");
    }
}