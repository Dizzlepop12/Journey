package net.journey.entity.mob.overworld;

import net.journey.JourneyItems;
import net.journey.JITL;
import net.journey.JourneyAchievements;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityMediumHongo extends EntityModMob {

	public EntityMediumHongo(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.0F, 1.0F);
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
		return EnumSounds.HONGO;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.HONGO_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.HONGO_HURT;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return 
			   this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.grass || 
			   		this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.leaves || 
			   			this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.sand || 
			   				this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.dirt;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.hongoShroom;
	}
}