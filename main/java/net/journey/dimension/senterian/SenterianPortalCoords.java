package net.journey.dimension.senterian;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SenterianPortalCoords implements IExtendedEntityProperties {

	public final EntityPlayer p;
	public static final String NAME = "SenterianPort";
    public double coordX;
    public double coordY;
    public double coordZ;
	
	public SenterianPortalCoords(EntityPlayer p) {
		this.p = p;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = p.getEntityData().getCompoundTag(p.PERSISTED_NBT_TAG);
        tag.setDouble("returnPortalX", 
        		coordX);
        tag.setDouble("returnPortalY", 
        		coordY);
        tag.setDouble("returnPortalZ", 
        		coordZ);
        p.getEntityData().setTag(p.PERSISTED_NBT_TAG, tag);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound tag = (NBTTagCompound) p.getEntityData().getCompoundTag(p.PERSISTED_NBT_TAG);
		if(!tag.hasKey("returnPortalX"))return;
        this.coordX = 
        		tag.getDouble("returnPortalX");
        this.coordY = 
        		tag.getDouble("returnPortalY");
        this.coordZ = 
        		tag.getDouble("returnPortalZ");
        p.getEntityData().setTag(p.PERSISTED_NBT_TAG, tag);
	}
	
	public static void addPortalProperties(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new SenterianPortalCoords(player));
	}
	
	public static SenterianPortalCoords getPortalProperties(EntityPlayer player) {
		return (SenterianPortalCoords) player.getExtendedProperties(NAME);
	}

    public void setReturnPortalX(double x) {
        this.coordX = x;
    }

    public double getReturnPortalX() {
        return this.coordX;
    }

    public void setReturnPortalY(double y) {
        this.coordY = y;
    }

    public double getReturnPortalY() {
        return this.coordY;
    }

    public void setReturnPortalZ(double z) {
        this.coordZ = z;
    }

    public double getReturnPortalZ() {
        return this.coordZ;
    }

    @Override
	public void init(Entity entity, World world) {}
}