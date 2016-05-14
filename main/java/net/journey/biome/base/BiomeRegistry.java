package net.journey.biome.base;

import net.journey.biome.nether.BiomeGenWitherSands;
import net.journey.biome.overworld.BiomeGenLushLands;
import net.journey.util.Config;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeRegistry {
	
	public static void mainClass() {
		initBiome();
		registerBiome();
	}
	
	public static BiomeGenBase netherWitherSands;
	public static BiomeGenBase lushLands;

	public static void initBiome() {
		netherWitherSands = new BiomeGenWitherSands(Config.witherSandsBiome).setBiomeName("Withering Sands").setTemperatureRainfall(1.2F, 0.9F);
		lushLands = new BiomeGenLushLands(Config.lushLandsBiome, true).setBiomeName("Lush Lands").setTemperatureRainfall(3.2F, 3.9F);
	}

	public static void registerBiome() {
		BiomeDictionary.registerBiomeType(netherWitherSands, Type.NETHER); BiomeManager.addSpawnBiome(netherWitherSands);
		BiomeDictionary.registerBiomeType(lushLands, Type.JUNGLE); BiomeManager.addSpawnBiome(lushLands);
	}
}
