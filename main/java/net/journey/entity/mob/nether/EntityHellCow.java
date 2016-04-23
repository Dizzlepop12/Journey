package net.journey.entity.mob.nether;

import net.journey.JourneyAchievements;
import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityHellCow extends EntityPeacefullUntillAttacked {

	public EntityHellCow(World par1World) {
		super(par1World);
        ((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
		this.isImmuneToFire = true;
		setSize(0.7F, 2.0F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}
	
	@Override
	public boolean allowLeashing() {
		return true;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.baseNetherHealth;
	}

    protected String getLivingSound()
    {
        return "mob.cow.say";
    }
    
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }
    
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }
	
	@Override
	public Item getItemDropped() {
		return JourneyItems.flamingHide;
	}
	
	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementKillNether);
		}
	}
	
	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }
	
	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == Items.bucket && !this.isChild()) {
			if (itemstack.stackSize-- == 1) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.lava_bucket));
			}
			else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.lava_bucket))) {
				player.dropPlayerItemWithRandomChoice(new ItemStack(Items.lava_bucket, 1, 0), false);
			}

			return true;
		}
		else {
			return super.interact(player);
		}
	}
	
	public EntityHellCow createChild(EntityAgeable ageable) {
        return new EntityHellCow(this.worldObj);
    }
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.blood, 1);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.blood, 2);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.flamingHide, 2);
		if(rand.nextInt(30) == 0) dropItem(JourneyItems.horn, 1);
		if(rand.nextInt(40) == 0) dropItem(JourneyItems.horn, 2);
		if (this.isBurning()) {
			if(rand.nextInt(4) == 0)dropItem(JourneyItems.flamingBeefCooked, 2);
			if(rand.nextInt(1) == 0)dropItem(JourneyItems.flamingBeefCooked, 1); 
			}
		else {
			if(rand.nextInt(4) == 0)dropItem(JourneyItems.flamingBeef, 2); 
			if(rand.nextInt(1) == 0)dropItem(JourneyItems.flamingBeef, 1); 
			}
		super.dropFewItems(b, j);
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.EMPTY;
	}
}