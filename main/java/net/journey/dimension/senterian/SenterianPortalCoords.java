package net.journey.dimension.senterian;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SenterianPortalCoords implements IExtendedEntityProperties {

	private final EntityPlayer player;
	public static final String NAME = "SenterianPort";
    public double returnPortalX;
    public double returnPortalY;
    public double returnPortalZ;
	
	public SenterianPortalCoords(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound n) {
		NBTTagCompound tag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
        tag.setDouble("returnPortalX", returnPortalX);
        tag.setDouble("returnPortalY", returnPortalY);
        tag.setDouble("returnPortalZ", returnPortalZ);
        player.getEntityData().setTag(player.PERSISTED_NBT_TAG, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound n) {
		NBTTagCompound tag = (NBTTagCompound) player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		if(!tag.hasKey("returnPortalX"))return;
        this.returnPortalX = tag.getDouble("returnPortalX");
        this.returnPortalY = tag.getDouble("returnPortalY");
        this.returnPortalZ = tag.getDouble("returnPortalZ");
        player.getEntityData().setTag(player.PERSISTED_NBT_TAG, tag);
	}
	
	public static void addProperties(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new SenterianPortalCoords(player));
	}
	
	public static SenterianPortalCoords getProperties(EntityPlayer player) {
		return (SenterianPortalCoords) player.getExtendedProperties(NAME);
	}

    public void setReturnPortalX(double x) {
        this.returnPortalX = x;
    }

    public double getReturnPortalX() {
        return this.returnPortalX;
    }

    public void setReturnPortalY(double y) {
        this.returnPortalY = y;
    }

    public double getReturnPortalY() {
        return this.returnPortalY;
    }

    public void setReturnPortalZ(double z) {
        this.returnPortalZ = z;
    }

    public double getReturnPortalZ() {
        return this.returnPortalZ;
    }

    @Override
	public void init(Entity entity, World world) {}
}