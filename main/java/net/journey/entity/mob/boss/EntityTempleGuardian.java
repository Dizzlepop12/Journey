package net.journey.entity.mob.boss;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.EntityPeacefullMob;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityDeathSkull;
import net.journey.entity.projectile.EntityFloroWater;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.entity.projectile.EntityTempleBall;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import net.slayer.api.entity.EntityModMob;

public class EntityTempleGuardian extends EntityEssenceBoss implements IRangedAttackMob {

	private int ticks;
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 23, 10);
	
	public EntityTempleGuardian(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(2.0F, 3.8F);
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}
	
	public void setCombatTask() {
		this.tasks.removeTask(this.aiArrowAttack); {
			this.tasks.addTask(4, this.aiArrowAttack);
		}
	}
	
	@Override
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		super.setCurrentItemOrArmor(par1, par2ItemStack);
		if(!this.worldObj.isRemote && par1 == 0) {
			this.setCombatTask();
		}
	}
	
	@Override
	public void onDeath(DamageSource damage){
		this.worldObj.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), Blocks.chest.getStateFromMeta(5));
		TileEntityChest te = (TileEntityChest)worldObj.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(4)) {
		case 0:
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.yellowGem, 3));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.blueGem, 2));
			te.setInventorySlotContents(16, new ItemStack(JourneyItems.greenGem, 5));
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.yellowGem, 4));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.blueGem, 5));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.greenGem, 2));
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.purpleGem, 6));
			break;
		case 2:
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.yellowGem, 2));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.blueGem, 4));
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.greenGem, 6));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		case 3:
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.yellowGem, 5));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.blueGem, 1));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.greenGem, 4));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		}
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) { }
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public ItemStack getHeldItem() {
		return new ItemStack(JourneyItems.staffOfHellstone);
	}

	@Override
	public void dropFewItems(boolean b, int j) {
		
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.templeGuardianHealth;
	}
	
	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
	}
	
	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BLAZE;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BLAZE_HURT;
	}
	
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f1) {
        this.launchWitherSkullToEntity(0, e);
        EntityFloroWater b = new EntityFloroWater(this.worldObj, this, 8F);
        b.setThrowableHeading(e.posX-this.posX, e.posY-this.posY, e.posZ-this.posZ, 1.6f, 12);
        EnumSounds.playSound(EnumSounds.HAMMER, worldObj, this);
        this.worldObj.spawnEntityInWorld(b);
	}
    
    private void launchWitherSkullToEntity(int var1, EntityLivingBase e) {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + (double)e.getEyeHeight() * 0.5D, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001F);
        
    }
    
    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityMagmaFireball entitydeathskull = new EntityMagmaFireball(this.worldObj, this, d6, d7, d8);
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

    private double coordY(int par1) {
        return par1 <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }

    private double coordZ(int par1) {
        if (par1 <= 0) {
            return this.posZ;
        }
        else {
            float f = (this.renderYawOffset + (float)(180 * (par1 - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + (double)f1 * 1.3D;
        }
    }
}