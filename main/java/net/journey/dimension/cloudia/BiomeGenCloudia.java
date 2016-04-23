package net.journey.dimension.cloudia;

import java.awt.Color;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCloudia extends BiomeGenBase {

	public BiomeGenCloudia(int par1) {
		super(par1);
		this.setBiomeName("Cloudia");
		this.topBlock = JourneyBlocks.cloudiaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.cloudiaDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        setColor(0x00FFD0);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float f) {
		return 0xFFB3DC;
    }
}