package net.journey.dimension.euca;

import java.awt.Color;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenEuca extends BiomeGenBase {

	public BiomeGenEuca(int id) {
		super(id);
		this.setBiomeName("Euca");
		this.topBlock = JourneyBlocks.eucaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.eucaStone.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		setColor(0xffd800);
	}

	@Override
	public int getSkyColorByTemp(float f) {
		return 0xffd800;
	}
}