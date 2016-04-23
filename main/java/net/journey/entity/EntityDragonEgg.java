package net.journey.entity;

import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityModMob;

public class EntityDragonEgg extends EntityModMob {

	private int colour = 0;
	
	public static final int ENTITY_TYPE = 27;
	
	public EntityDragonEgg(World w, int colour) {
		super(w);
		setSize(0.4F, 0.5F);
		this.colour = colour;
		dataWatcher.updateObject(ENTITY_TYPE, colour);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(ENTITY_TYPE, (int)0);
	}
	
	public int getColour() {
		return colour;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return 20;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected boolean isMovementBlocked() {
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean canPickUpLoot() {
		return false;
	}

	@Override
	public boolean allowLeashing() {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1) {
		return 15728880;
	}

	@Override
	public float getBrightness(float par1) {
		return 1.0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
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