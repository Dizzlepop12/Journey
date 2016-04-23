package net.journey.entity.mob.frozen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModFlying;
import net.slayer.api.entity.EntityModMob;

public class EntityShatterer extends EntityModFlying {

	public EntityShatterer(World par1World) {
		super(par1World);
		this.moveHelper = new EntityShatterer.ShattererMoveHelper();
        this.tasks.addTask(5, new EntityShatterer.AIRandomFly());
		setSize(2F, 2F);
	}

	@Override
	public void onUpdate() {
        super.onUpdate();
        if(!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.flyingHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.HONGO;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SAND_CRAWLER;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SAND_CRAWLER;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return this.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == JourneyBlocks.brittleIce;
	}
	
	private class AIRandomFly extends EntityAIBase {
        private EntityShatterer e = EntityShatterer.this;

        public AIRandomFly() {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            EntityMoveHelper entitymovehelper = this.e.getMoveHelper();
            if(!entitymovehelper.isUpdating()) {
                return true;
            } else {
				double d0 = entitymovehelper.getX() - this.e.posX;
				double d1 = entitymovehelper.getY() - this.e.posY;
				double d2 = entitymovehelper.getZ() - this.e.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        @Override
        public boolean continueExecuting() {
            return false;
        }

        @Override
        public void startExecuting() {
            Random random = this.e.getRNG();
            double d0 = this.e.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.e.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.e.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.e.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    private class ShattererMoveHelper extends EntityMoveHelper {
        private EntityShatterer e = EntityShatterer.this;
        private int height;

        public ShattererMoveHelper() {
            super(EntityShatterer.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            if(this.update) {
                double d0 = this.posX - this.e.posX;
                double d1 = this.posY - this.e.posY;
                double d2 = this.posZ - this.e.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if(this.height-- <= 0) {
                    this.height += this.e.getRNG().nextInt(5) + 2;
                    d3 = (double)MathHelper.sqrt_double(d3);
                    if(this.canMove(this.posX, this.posY, this.posZ, d3)) {
                        this.e.motionX += d0 / d3 * 0.1D;
                        this.e.motionY += d1 / d3 * 0.1D;
                        this.e.motionZ += d2 / d3 * 0.1D;
                    } else {
                        this.update = false;
                    }
                }
            }
        }

        private boolean canMove(double x, double y, double z, double h)  {
            double d4 = (x - this.e.posX) / h;
            double d5 = (y - this.e.posY) / h;
            double d6 = (z - this.e.posZ) / h;
            AxisAlignedBB axisalignedbb = this.e.getEntityBoundingBox();
            for(int i = 1; (double)i < h; ++i) {
                axisalignedbb = axisalignedbb.offset(d4, d5, d6);
                if(!this.e.worldObj.getCollidingBoundingBoxes(this.e, axisalignedbb).isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }
}