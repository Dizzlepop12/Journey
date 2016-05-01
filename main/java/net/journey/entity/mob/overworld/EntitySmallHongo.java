package net.journey.entity.mob.overworld;

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

public class EntitySmallHongo extends EntityModMob {

	public EntitySmallHongo(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(0.5F, 0.7F);
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
		return EnumSounds.SMALL_HONGO;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SMALL_HONGO_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SMALL_HONGO_HURT;
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
		return SlayerAPI.toItem(Blocks.brown_mushroom);
	}
}