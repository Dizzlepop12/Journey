package net.journey.entity.mob.overworld;

import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityRobot extends EntityModMob {

	public EntityRobot(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.7F, 2.3F);
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
		return EnumSounds.ROBOT;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.ROBOT_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.ROBOT_HURT;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(2) == 0) this.dropItem(Items.iron_ingot, 1 + rand.nextInt(1));
		if(rand.nextInt(2) == 0) this.dropItem(Items.stick, 1 + rand.nextInt(4));
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}