package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntitySandCrawler extends EntityModMob {

	public EntitySandCrawler(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.8F, 1.0F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.lowJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.overworldHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.SAND_CRAWLER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.MAGMA_GIANT_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.MAGMA_GIANT_HURT;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		for(int i = 0; i < 32 + rand.nextInt(16); i++)
			this.dropItem(SlayerAPI.toItem(Blocks.sand), 2);
	}
}