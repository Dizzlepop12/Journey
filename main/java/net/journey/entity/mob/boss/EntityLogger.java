package net.journey.entity.mob.boss;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityLogger extends EntityEssenceBoss {

	private int attackTimer;

	public EntityLogger(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(3.0F, 3.5F);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.attackTimer > 0) --this.attackTimer;
		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D);
			int k = MathHelper.floor_double(this.posZ);
			IBlockState iblockstate = this.worldObj.getBlockState(new BlockPos(i, j, k));
            Block block = iblockstate.getBlock();
			if(block.getMaterial() != Material.air)
				 this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D, new int[] {Block.getStateId(iblockstate)});
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		this.attackTimer = 5;
		this.worldObj.setEntityState(this, (byte)4);
		boolean flag = e.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getAttackDamage());
		if(flag) { 
			e.motionY += 1.0000000059604645D;
			e.setFire(10 + rand.nextInt(10));
		}
		this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.loggerDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.loggerHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.NETHER_BEAST;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.NETHER_BEAST_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
	}
	
	@Override
	public void onDeath(DamageSource damage) {
		if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementlogger); {
			}
		}
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyLogger.getStateFromMeta(5));
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.corbaChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)worldObj.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
		case 0:
			te.setInventorySlotContents(15, new ItemStack(JourneyItems.terraniaPortalGem, 8));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.naturesBlade, 1));
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.loggersBow, 1));
			
			te.setInventorySlotContents(3, new ItemStack(JourneyItems.hollowChest, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.hollowHelmet, 1));
			te.setInventorySlotContents(7, new ItemStack(JourneyItems.hollowLegs, 1));
			te.setInventorySlotContents(8, new ItemStack(JourneyItems.hollowBoots, 1));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.terraniaPortalGem, 10));
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.loggersSword, 1));
			te.setInventorySlotContents(10, new ItemStack(JourneyItems.loggersBow, 1));
			
			te.setInventorySlotContents(3, new ItemStack(JourneyItems.hollowChest, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.hollowHelmet, 1));
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.hollowLegs, 1));
			te.setInventorySlotContents(6, new ItemStack(JourneyItems.hollowBoots, 1));
			break;
		}
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		switch(rand.nextInt(3)) {
		}
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}