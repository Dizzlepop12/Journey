package net.journey.dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.journey.dimension.boil.BiomeGenBoiling;
import net.journey.dimension.boil.WorldProviderBoiling;
import net.journey.dimension.cloudia.BiomeGenCloudia;
import net.journey.dimension.cloudia.WorldProviderCloudia;
import net.journey.dimension.corba.BiomeGenCorba;
import net.journey.dimension.corba.WorldProviderCorba;
import net.journey.dimension.depths.BiomeGenDepths;
import net.journey.dimension.depths.WorldProviderDepths;
import net.journey.dimension.euca.BiomeGenEuca;
import net.journey.dimension.euca.WorldProviderEuca;
import net.journey.dimension.frozen.BiomeGenFrozenLands;
import net.journey.dimension.frozen.WorldProviderFrozenLands;
import net.journey.dimension.golden.BiomeGenGoldenGrains;
import net.journey.dimension.golden.WorldProviderGoldenGrains;
import net.journey.dimension.senterian.BiomeGenSenterian;
import net.journey.dimension.senterian.WorldProviderSenterian;
import net.journey.dimension.terrania.BiomeGenTerrania;
import net.journey.dimension.terrania.WorldProviderTerrania;
import net.journey.dimension.wither.BiomeGenWither;
import net.journey.dimension.wither.WorldProviderWither;
import net.journey.entity.mob.boiling.EntityBurningLight;
import net.journey.entity.mob.boiling.EntityFrightener;
import net.journey.entity.mob.boiling.EntityHellwing;
import net.journey.entity.mob.boiling.EntityMagmaBlaze;
import net.journey.entity.mob.boiling.EntityObserver;
import net.journey.entity.mob.boiling.EntityPhoenix;
import net.journey.entity.mob.cloudia.EntityCloudFlyer;
import net.journey.entity.mob.cloudia.EntityCloudGhost;
import net.journey.entity.mob.cloudia.EntitySkyEel;
import net.journey.entity.mob.cloudia.EntityStarlightGolem;
import net.journey.entity.mob.cloudia.EntityStarlightTransporter;
import net.journey.entity.mob.cloudia.EntityStarlightWalker;
import net.journey.entity.mob.corba.EntityLeafBlower;
import net.journey.entity.mob.corba.EntitySurfaceSeer;
import net.journey.entity.mob.corba.EntityTreeGolem;
import net.journey.entity.mob.corba.EntityWoodCreature;
import net.journey.entity.mob.corba.EntityWoodpecker;
import net.journey.entity.mob.depths.EntityDarkSorcerer;
import net.journey.entity.mob.depths.EntityDarkener;
import net.journey.entity.mob.depths.EntityDarkfish;
import net.journey.entity.mob.depths.EntityDarknessCrawler;
import net.journey.entity.mob.depths.EntityDepthsBeast;
import net.journey.entity.mob.depths.EntityDepthsHunter;
import net.journey.entity.mob.depths.EntityLightener;
import net.journey.entity.mob.depths.EntityRoc;
import net.journey.entity.mob.depths.EntitySpikedBeast;
import net.journey.entity.mob.euca.EntityDynaster;
import net.journey.entity.mob.euca.EntityEucaCharger;
import net.journey.entity.mob.euca.EntityGolder;
import net.journey.entity.mob.euca.EntityGolditeMage;
import net.journey.entity.mob.euca.EntityGoldwing;
import net.journey.entity.mob.euca.EntityShimmerer;
import net.journey.entity.mob.frozen.EntityCrystalCluster;
import net.journey.entity.mob.frozen.EntityFrozenTroll;
import net.journey.entity.mob.frozen.EntityPermafraust;
import net.journey.entity.mob.frozen.EntityShatterer;
import net.journey.entity.mob.frozen.EntityShiveringBushwalker;
import net.journey.entity.mob.frozen.EntityShiverwing;
import net.journey.entity.mob.nether.EntityHellCow;
import net.journey.entity.mob.nether.EntityLavasnake;
import net.journey.entity.mob.nether.EntityMiniGhast;
import net.journey.entity.mob.nether.EntityReaper;
import net.journey.entity.mob.nether.EntityWitherspine;
import net.journey.entity.mob.overworld.EntityBigHongo;
import net.journey.entity.mob.overworld.EntityBoom;
import net.journey.entity.mob.overworld.EntityFireMage;
import net.journey.entity.mob.overworld.EntityFloro;
import net.journey.entity.mob.overworld.EntityIceMage;
import net.journey.entity.mob.overworld.EntityMediumHongo;
import net.journey.entity.mob.overworld.EntityRobot;
import net.journey.entity.mob.overworld.EntitySandCrawler;
import net.journey.entity.mob.overworld.EntitySmallHongo;
import net.journey.entity.mob.overworld.EntitySpectre;
import net.journey.entity.mob.overworld.EntitySpyclops;
import net.journey.entity.mob.overworld.EntitySwampFly;
import net.journey.entity.mob.overworld.EntityTurducken;
import net.journey.entity.mob.overworld.EntityWraith;
import net.journey.entity.mob.overworld.cold.EntityBlizzard;
import net.journey.entity.mob.overworld.underground.EntityBlueHonglow;
import net.journey.entity.mob.overworld.underground.EntityCaveMage;
import net.journey.entity.mob.overworld.underground.EntityCaveling;
import net.journey.entity.mob.overworld.underground.EntityCavurn;
import net.journey.entity.mob.overworld.underground.EntityGreenHonglow;
import net.journey.entity.mob.overworld.underground.EntityHonglow;
import net.journey.entity.mob.overworld.underground.EntityStonewalker;
import net.journey.entity.mob.overworld.underground.npc.EntityRockiteGolem;
import net.journey.entity.mob.pet.EntityFerret;
import net.journey.entity.mob.terrania.mob.EntityTerraScatterer;
import net.journey.entity.mob.terrania.mob.EntityTerragrow;
import net.journey.entity.mob.terrania.mob.EntityTerralight;
import net.journey.entity.mob.terrania.mob.EntityTerrashroom;
import net.journey.entity.mob.terrania.mob.EntityTerraslug;
import net.journey.util.Config;
import net.journey.util.LogHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.slayer.api.SlayerAPI;

public class DimensionHelper {
	
	protected static final BiomeGenBase.Height boilHeight = new BiomeGenBase.Height(0.125F, 0.1F); 
	protected static final BiomeGenBase.Height corbaHeight = new BiomeGenBase.Height(0.2F, 0.2F); 

	public static BiomeGenBase euca = new BiomeGenEuca(Config.eucaBiome).setHeight(new BiomeGenBase.Height(0.125F, 0.05F));
	public static BiomeGenBase depths = new BiomeGenDepths(Config.depthsBiome);
	public static BiomeGenBase boiling = new BiomeGenBoiling(Config.boilBiome).setHeight(boilHeight);
	public static BiomeGenBase frozen = new BiomeGenFrozenLands(Config.frozenBiome);
	public static BiomeGenBase corba = new BiomeGenCorba(Config.corbaBiome).setHeight(corbaHeight);
	//public static BiomeGenBase wastelands = new BiomeGenWastelands(Config.wastelandsBiome);
	public static BiomeGenBase cloudia = new BiomeGenCloudia(Config.cloudiaBiome);
	public static BiomeGenBase terrania = new BiomeGenTerrania(Config.terraniaBiome);
	public static BiomeGenBase golden = new BiomeGenGoldenGrains(Config.goldenBiome);
	public static BiomeGenBase senterian = new BiomeGenSenterian(Config.senterianBiome);
	public static BiomeGenBase wither = new BiomeGenWither(Config.witherBiome);

	public static void init(){
		LogHelper.info("Registering Dimensions...");
		addDimension(Config.euca, WorldProviderEuca.class, Config.keepLoadingEuca);
		addDimension(Config.depths, WorldProviderDepths.class, Config.keepLoadingDepths);
		addDimension(Config.boil, WorldProviderBoiling.class, Config.keepLoadingBoil);
		addDimension(Config.frozen, WorldProviderFrozenLands.class, Config.keepLoadingFrozen);
		addDimension(Config.corba, WorldProviderCorba.class, Config.keepLoadingCorba);
		//addDimension(Config.wastelands, WorldProviderWastelands.class, Config.keepLoadingWastelands);
		addDimension(Config.cloudia, WorldProviderCloudia.class, Config.keepLoadingCloudia);
		addDimension(Config.terrania, WorldProviderTerrania.class, Config.keepLoadingTerrania);
		addDimension(Config.golden, WorldProviderGoldenGrains.class, Config.keepLoadingGolden);
		addDimension(Config.senterian, WorldProviderSenterian.class, Config.keepLoadingSenterian);
		addDimension(Config.wither, WorldProviderWither.class, Config.keepLoadingWither);
	}

	private static void addDimension(int id, Class<? extends WorldProvider> w, boolean keeploading) {
		LogHelper.info("Registering dimension ID: " + id);
		DimensionManager.registerProviderType(id, w, keeploading);
		DimensionManager.registerDimension(id, id);
	}

	public static void addSpawns() {
		addEucaSpawns();
		addFrostFrozenSpawns();
		addColdFrozenSpawns();
		addBoilSpawns();
		addDepthsSpawns();
		addVanillaSpawns();
		addCaveSpawns();
		addCorbaSpawns();
		addCloudiaSpawns();
		addTerraniaSpawns();
		addCommonVanillaSpawns();
		addNetherSpawns();
	}
	
	private static void addCloudiaSpawns() {
		int amount = 1000;
		EntityRegistry.addSpawn(EntityStarlightTransporter.class, amount, 10, 20, EnumCreatureType.MONSTER, cloudia);
		EntityRegistry.addSpawn(EntityStarlightGolem.class, amount, 10, 20, EnumCreatureType.MONSTER, cloudia);
		EntityRegistry.addSpawn(EntityCloudFlyer.class, amount, 1, 1, EnumCreatureType.MONSTER, cloudia);
		EntityRegistry.addSpawn(EntityStarlightWalker.class, amount, 10, 20, EnumCreatureType.MONSTER, cloudia);
		EntityRegistry.addSpawn(EntitySkyEel.class, amount, 10, 20, EnumCreatureType.MONSTER, cloudia);
	}

	private static void addCorbaSpawns() {
		int amount = 20;
		EntityRegistry.addSpawn(EntityTreeGolem.class, amount, 1, 1, EnumCreatureType.MONSTER, corba);
		EntityRegistry.addSpawn(EntityLeafBlower.class, amount, 1, 1, EnumCreatureType.MONSTER, corba);
		EntityRegistry.addSpawn(EntitySurfaceSeer.class, amount, 1, 1, EnumCreatureType.MONSTER, corba);
		EntityRegistry.addSpawn(EntityWoodCreature.class, amount, 1, 1, EnumCreatureType.MONSTER, corba);
		EntityRegistry.addSpawn(EntityWoodpecker.class, amount, 1, 1, EnumCreatureType.CREATURE, corba);
	}
	private static void addColdFrozenSpawns() {
		int amount = 80;
		EntityRegistry.addSpawn(EntitySnowman.class, 2, 1, 6, EnumCreatureType.CREATURE, frozen);
		EntityRegistry.addSpawn(EntityFrozenTroll.class, amount, 10,  amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityPermafraust.class, amount, 10, 20, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityShiveringBushwalker.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityIceMage.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityShiverwing.class, amount, 10, amount, EnumCreatureType.CREATURE, frozen);
		
	}

	private static void addFrostFrozenSpawns() {
		int amount = 80;
		EntityRegistry.addSpawn(EntitySnowman.class, 2, 1, 6, EnumCreatureType.CREATURE, frozen);
		EntityRegistry.addSpawn(EntityPermafraust.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityShatterer.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityCrystalCluster.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityIceMage.class, amount, 10, amount, EnumCreatureType.MONSTER, frozen);
		EntityRegistry.addSpawn(EntityShiverwing.class, amount, 10, amount, EnumCreatureType.CREATURE, frozen);
	}
	
	private static void addTerraniaSpawns() {
		int amount = 20;
		EntityRegistry.addSpawn(EntityTerraScatterer.class, 5, 1, 1, EnumCreatureType.MONSTER, terrania);
		EntityRegistry.addSpawn(EntityTerralight.class, 5, 1, 1, EnumCreatureType.MONSTER, terrania);
		EntityRegistry.addSpawn(EntityTerragrow.class, 5, 1, 1, EnumCreatureType.MONSTER, terrania);
		EntityRegistry.addSpawn(EntityTerrashroom.class, 5, 1, 1, EnumCreatureType.MONSTER, terrania);
		EntityRegistry.addSpawn(EntityTerraslug.class, 5, 1, 1, EnumCreatureType.MONSTER, terrania);
	}

	private static void addEucaSpawns() {
		int amount = 20;
		EntityRegistry.addSpawn(EntityEucaCharger.class, amount, 1, 1, EnumCreatureType.MONSTER, euca);
		EntityRegistry.addSpawn(EntityDynaster.class, amount, 1, 1, EnumCreatureType.MONSTER, euca);
		EntityRegistry.addSpawn(EntityShimmerer.class, amount, 1, 1, EnumCreatureType.MONSTER, euca);
		EntityRegistry.addSpawn(EntityGolditeMage.class, amount, 1, 1, EnumCreatureType.MONSTER, euca);
		EntityRegistry.addSpawn(EntityGoldwing.class, amount, 1, 1, EnumCreatureType.CREATURE, euca);
	}

	private static void addBoilSpawns() {
		int amount = 20;
		EntityRegistry.addSpawn(EntityFrightener.class, amount, 1, 1, EnumCreatureType.MONSTER, boiling);
		EntityRegistry.addSpawn(EntityBurningLight.class, amount, 1, 1, EnumCreatureType.MONSTER, boiling);
		EntityRegistry.addSpawn(EntityMagmaBlaze.class, amount, 1, 1, EnumCreatureType.MONSTER, boiling);
		EntityRegistry.addSpawn(EntityHellwing.class, amount, 1, 1, EnumCreatureType.MONSTER, boiling);
		EntityRegistry.addSpawn(EntityPhoenix.class, amount, 1, 1, EnumCreatureType.MONSTER, boiling);
	}

	private static void addDepthsSpawns() {
		int amount = 20;
		EntityRegistry.addSpawn(EntityDarknessCrawler.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntityDepthsBeast.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntitySpikedBeast.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntityDepthsHunter.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntityRoc.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntityDarkener.class, amount, 10, 1, EnumCreatureType.MONSTER, depths);
		EntityRegistry.addSpawn(EntityLightener.class, 4, 10, 1, EnumCreatureType.MONSTER, depths);
	}
	
	private static void addCaveSpawns() {
		int amount = 250;
		int amount2 = 5;
		for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[i];
			if (biome != null){
				EntityRegistry.addSpawn(EntityCaveMage.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityCaveling.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityCavurn.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityStonewalker.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityBlueHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityGreenHonglow.class, amount, 3, 4, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityRockiteGolem.class, amount2, 3, 4, EnumCreatureType.MONSTER, biome);
			}
		}
	}

	private static void addCommonVanillaSpawns() {
		int amount = 100;
		EntityRegistry.addSpawn(EntitySwampFly.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SWAMP));
		
	}
	private static void addNetherSpawns() {
		EntityRegistry.addSpawn(EntityLavasnake.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityWitherspine.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityReaper.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityHellCow.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityMiniGhast.class, 100, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
	}
	private static void addVanillaSpawns() {
		int amount = 4;
		EntityRegistry.addSpawn(EntitySandCrawler.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SANDY));
		EntityRegistry.addSpawn(EntitySpyclops.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SANDY));
		EntityRegistry.addSpawn(EntityFerret.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SAVANNA));
		EntityRegistry.addSpawn(EntityFerret.class, 1, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SANDY));
		EntityRegistry.addSpawn(EntityFireMage.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.NETHER));
		EntityRegistry.addSpawn(EntityFireMage.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.BEACH));
		EntityRegistry.addSpawn(EntityFireMage.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.HOT));
		EntityRegistry.addSpawn(EntityFireMage.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SANDY));
		EntityRegistry.addSpawn(EntityBlizzard.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.SNOWY));
		EntityRegistry.addSpawn(EntityTurducken.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.FOREST));
		EntityRegistry.addSpawn(EntityFloro.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.FOREST));
		EntityRegistry.addSpawn(EntityBigHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.FOREST));
		EntityRegistry.addSpawn(EntityMediumHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.FOREST));
		EntityRegistry.addSpawn(EntityBigHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.MUSHROOM));
		EntityRegistry.addSpawn(EntityMediumHongo.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.MUSHROOM));
		EntityRegistry.addSpawn(EntityBoom.class, amount, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(Type.FOREST));
		
		for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[i];
			if (biome != null){
				EntityRegistry.addSpawn(EntitySpectre.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
				EntityRegistry.addSpawn(EntityWraith.class, amount, 1, 1, EnumCreatureType.MONSTER, biome);
			}
		}
	}
}