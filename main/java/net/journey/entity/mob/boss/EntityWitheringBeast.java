package net.journey.entity.mob.boss;

import com.google.common.collect.Lists;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityDeathSkull;
import net.journey.entity.projectile.EntityFireBall;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityWitheringBeast extends EntityEssenceBoss implements IRangedAttackMob {

	public EntityWitheringBeast(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(2.0F, 3.8F);
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
	}

	@Override
	public void onDeath(DamageSource damage){
		if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementWitherBeast); {
			}
		}
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyWitherBeast.getStateFromMeta(5));
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.netherChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)worldObj.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
		case 0:
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.witheringBeastSword, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.eucaPortalPiece_0, 1));
			break;
		case 1:
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.witheringBeastSword, 1));
			te.setInventorySlotContents(15, new ItemStack(JourneyItems.eucaPortalPiece_0, 2));
			break;
		}
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.witheringBeastDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.witheringBeastHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.WITHER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.WITHER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

    public void attackEntityWithRangedAttack(EntityLivingBase e, float f1)
    {
        this.launchWitherSkullToEntity(0, e);
	}
    
    private void launchWitherSkullToEntity(int var1, EntityLivingBase e)
    {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + (double)e.getEyeHeight() * 0.5D, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001F);
        
    }
    
    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8)
    {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityDeathSkull entitydeathskull = new EntityDeathSkull(this.worldObj, this, d6, d7, d8);

        if (f8)
        {
        	entitydeathskull.setInvulnerable(true);
        }

        entitydeathskull.posY = d4;
        entitydeathskull.posX = d3;
        entitydeathskull.posZ = d5;
        this.worldObj.spawnEntityInWorld(entitydeathskull);
	}
    
    private double coordX(int par1) {
        if (par1 <= 0) {  
            return this.posX;
        }
        else {
            float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.cos(f);
            return this.posX + (double)f1 * 1.3D;
        }
    }

    private double coordY(int par1)
    {
        return par1 <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }

    private double coordZ(int par1)
    {
        if (par1 <= 0)
        {
            return this.posZ;
        }
        else
        {
            float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + (double)f1 * 1.3D;
        }
    }
}