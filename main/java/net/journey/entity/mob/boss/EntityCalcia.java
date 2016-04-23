package net.journey.entity.mob.boss;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityCalcia extends EntityEssenceBoss {

	private int firetick;
	private int firemax = 400, firemax2 = 300;
	private boolean isInvi;

	public EntityCalcia(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.6F, 3.2F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return s.calciaDamage;
	}

	@Override
	public double setKnockbackResistance() {
		return 1.0D;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.calciaHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.CALCIA;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.CALCIA_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.CALCIA_HURT;
	}

	public boolean isInv() {
		return isInvi;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(isInv()) {
			for(int i = 0; i < 5; i++) this.worldObj.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
			Entity entity = attackingPlayer;
			if(entity != null)
				((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 25, 2));
		}
	}

	@Override
	public void onLivingUpdate() {
		if(firemax == firetick && firetick != 0) {
			this.isInvi = true;
			this.firetick = 0;
		} else {
			firetick++;
		} 

		if(firemax2 == firetick && firetick != 0) {
			this.isInvi = false;
			this.firetick = 0;
		} else {
			firetick++;
		}
		super.onLivingUpdate();
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.eucaPortalGem;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(JourneyItems.eucaPortalGem, 6 + rand.nextInt(4));
		this.dropItem(JourneyItems.calciaSword, 1);

		//if(rand.nextInt(1) == 0)
		//	this.dropItem(Item.getItemFromBlock(EssenceBlocks.calciaStatue), 1);
	}
}