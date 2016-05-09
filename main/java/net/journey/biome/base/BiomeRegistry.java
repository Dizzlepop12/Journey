package net.journey.biome.base;

import net.journey.biome.nether.BiomeGenWitherSands;
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

	public static void initBiome() {
		netherWitherSands = new BiomeGenWitherSands(Config.witherSandsBiome).setBiomeName("Withering Sands").setTemperatureRainfall(1.2F, 0.9F);
	}
	
	public static void registerBiome() {
		BiomeDictionary.registerBiomeType(netherWitherSands, Type.NETHER);
		BiomeManager.addSpawnBiome(netherWitherSands);
	}
}
