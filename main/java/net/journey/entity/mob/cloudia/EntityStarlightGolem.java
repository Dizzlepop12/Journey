package net.journey.entity.mob.cloudia;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;
import net.slayer.api.entity.EntityPeacefullUntillAttacked;

public class EntityStarlightGolem extends EntityModMob {

	public EntityStarlightGolem(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize((float) 2.0, 2);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.cloudiaHealth;
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
	
	@Override
    protected String getLivingSound() {
        return "mob.irongolem.hit";
    }
	
	@Override
    protected String getHurtSound() {
        return "mob.irongolem.hit";
    }
	
	@Override
    protected String getDeathSound() {
        return "mob.irongolem.death";
    }
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.golemChunk, 1);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.golemChunk, 2);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.golemChunk, 4);
		if(rand.nextInt(40) == 0) dropItem(JourneyItems.cloudiaOrb, 1);
		super.dropFewItems(b, j);
		
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

}