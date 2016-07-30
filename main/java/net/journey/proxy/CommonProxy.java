package net.journey.proxy;

import amerifrance.guideapi.GuideAPI;
import amerifrance.guideapi.api.base.Book;
import net.journey.*;
import net.journey.achievement.event.JourneyDungeonEvent;
import net.journey.achievement.event.JourneySapphireEvent;
import net.journey.achievement.event.JourneySapphireSwordEvent;
import net.journey.biome.base.BiomeRegistry;
import net.journey.biome.base.WorldTypeJourney;
import net.journey.blocks.tileentity.*;
import net.journey.book.JourneyBook;
import net.journey.client.BarTickHandler;
import net.journey.dimension.*;
import net.journey.enums.*;
import net.journey.event.*;
import net.journey.misc.*;
import net.journey.util.*;
import net.journey.util.EntityRegistry;
import net.journey.util.recipes.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.stats.Achievement;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.oredict.*;
import net.slayer.api.*;

public class CommonProxy {

	public void updateDarkEnergy(int amount) { }
	public void updateEssence(int amount) { }
	public void updatePower(int amount) { }
	public void registerClient() { }
	public void clientInit(FMLInitializationEvent event) { }
	public void clientPreInit() { }
	public void registerSounds() { }
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double x, double y, double z, boolean b) { }
	public void registerModModels() { }
	
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event);
		//Essence.tropicalWater.setBlock(EssenceBlocks.tropicalWater);
		EntityRegistry.init();
		JourneyChestGenerator.init();
		JourneyAchievements.init();
		JourneyMaterialRecipes.init();
		JourneyBlockRecipes.init();
		JourneyMiscRecipes.init();
		JourneySmeltingRecipes.init();
		JourneyWeaponRecipes.init();
		DimensionHelper.init();
		BiomeRegistry.init();
		DimensionHelper.addSpawns();
		JourneyTabs.init();
		BiomeRegistry.mainClass();
		JourneyBook.registerTests(1);
		
		if(SlayerAPI.DEVMODE) LangRegistry.instance.register();
		addOreDictionary();
		SlayerAPI.registerEvent(new ArmorAbilityEvent());
		SlayerAPI.registerEvent(new PlayerEvent());
		SlayerAPI.registerEvent(new BarTickHandler());
		GameRegistry.registerTileEntity(TileEntityGrindstone.class, "grindstone");
		GameRegistry.registerTileEntity(TileEntityKnowledgeTable.class, "Knowledge Table");
		GameRegistry.registerTileEntity(TileEntitySummoningTable.class, "Summoning Table");
		GameRegistry.registerTileEntity(TileEntityTrophyTable.class, "Trophy Table");
		GameRegistry.registerTileEntity(TileEntityJourneyChest.class, "Journey Chest");
		GameRegistry.registerTileEntity(TileEntityNetherFurnace.class, "Nether Furnace");
		GameRegistry.registerTileEntity(TileEntitySenterianPortal.class, "Senterian Labyrinth Portal");
		GameRegistry.registerFuelHandler(new JourneyFuelHandler());
		//SlayerAPI.addMapGen(MapGenBoilVillage.Start.class, "Boil Village");
		//SlayerAPI.addMapGen(MapGenBoilBridge.Start.class, "Boil Bridge");
		//StructureBoilBridgePieces.registerBoilFortressPieces();
		//StructureBoilVillagePieces.registerVillagePieces();
		MinecraftForge.addGrassSeed(new ItemStack(JourneyCrops.tomatoSeeds), 5);
		FMLCommonHandler.instance().bus().register(new JourneySapphireSwordEvent());
		FMLCommonHandler.instance().bus().register(new JourneySapphireEvent());
		FMLCommonHandler.instance().bus().register(new JourneyDungeonEvent());
		DimensionManager.unregisterDimension(-1);
	}
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenEssence(), 2);
		SlayerAPI.registerEvent(new PlayerEvent());
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		WorldType JOURNEY = new WorldTypeJourney(3, "Journey");
	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		SlayerAPI.registerCommand(new EssenceCommands());
		SlayerAPI.registerCommand(new DimensionCommand());
	}
	
	private void addOreDictionary() {
		OreDictionary.registerOre("oreAshual", JourneyBlocks.ashualOre);
		OreDictionary.registerOre("oreCelestium", JourneyBlocks.celestiumOre);
		OreDictionary.registerOre("oreLunium", JourneyBlocks.luniumOre);
		OreDictionary.registerOre("oreShadium", JourneyBlocks.shadiumOre);
		OreDictionary.registerOre("oreFlairium", JourneyBlocks.flairiumOre);
		OreDictionary.registerOre("oreSapphire", JourneyBlocks.sapphireOre);
		OreDictionary.registerOre("ash", JourneyItems.ash);
		OreDictionary.registerOre("ingotCelestium", JourneyItems.celestiumIngot);
		OreDictionary.registerOre("ingotLunium", JourneyItems.luniumIngot);
		OreDictionary.registerOre("ingotShadium", JourneyItems.shadiumIngot);
		OreDictionary.registerOre("ingotFlairium", JourneyItems.flairiumIngot);
		OreDictionary.registerOre("gemSapphire", JourneyItems.sapphire);
		OreDictionary.registerOre("gemBlazium", JourneyItems.blazium);
	}
}