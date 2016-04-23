package net.journey.entity.AI;

import net.journey.entity.mob.overworld.EntityBoom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIBoomSwell extends EntityAIBase {
	
    EntityBoom swellingCreeper;
    EntityLivingBase creeperAttackTarget;

    public EntityAIBoomSwell(EntityBoom b) {
        this.swellingCreeper = b;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute(){
        EntityLivingBase entitylivingbase = this.swellingCreeper.getAttackTarget();
        return this.swellingCreeper.getBoomBoomState() > 0 || entitylivingbase != null && this.swellingCreeper.getDistanceSqToEntity(entitylivingbase) < 9.0D;
    }

    @Override
    public void startExecuting() {
        this.swellingCreeper.getNavigator().clearPathEntity();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
    }

    @Override
    public void resetTask() {
        this.creeperAttackTarget = null;
    }

    @Override
    public void updateTask() {
        if (this.creeperAttackTarget == null) {
            this.swellingCreeper.setBoomBoomState(-1);
        }
        else if (this.swellingCreeper.getDistanceSqToEntity(this.creeperAttackTarget) > 49.0D) {
            this.swellingCreeper.setBoomBoomState(-1);
        }
        else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
            this.swellingCreeper.setBoomBoomState(-1);
        } else {
            this.swellingCreeper.setBoomBoomState(1);
        }
    }
}