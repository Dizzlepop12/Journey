package net.journey.dimension.senterian;

import net.journey.JourneyBlocks;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterSenterian extends Teleporter {
	
	protected WorldServer world;

	public TeleporterSenterian(WorldServer world) {
		super(world);
		this.world = world;
	}

	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        SenterianPortalCoords props = SenterianPortalCoords.getProperties((EntityPlayer)entityIn);
	    if (entityIn.dimension == Config.senterian) {        
            int chunkX = (MathHelper.floor_double(entityIn.posX) & ~0xf);
            int chunkZ = (MathHelper.floor_double(entityIn.posZ) & ~0xf);
            int y;

            props.setReturnPortalX(entityIn.posX);
            props.setReturnPortalY(entityIn.posY);
            props.setReturnPortalZ(entityIn.posZ);

            for (y = 9; y < 40; y += 8) {
                if (this.world.getBlockState(new BlockPos(chunkX + 7, y, chunkZ + 7)) == JourneyBlocks.senterianPortal) {
                    entityIn.setLocationAndAngles(chunkX + 7.5D, y + 0.5D, chunkZ + 7.5D, entityIn.rotationYaw, 0.0F);
                    entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;            
                    return true;
                }
            }
            
            for (y = 8; y < 40; y += 8) {
                if (    this.world.getBlockState(new BlockPos(chunkX + 7, y, chunkZ + 7)) !=Blocks.air.getDefaultState() && 
                		this.world.getBlockState(new BlockPos(chunkX + 7, y + 8, chunkZ + 7)) !=Blocks.air.getDefaultState()) {
                    generatePortalRoom(this.world, chunkX, y, chunkZ);
                    entityIn.setLocationAndAngles(chunkX + 7.5D, y + 1.5D, chunkZ + 7.5D, entityIn.rotationYaw, 0.0F);
                    entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
                    return true;
                }
            }
	    } else {
            entityIn.setLocationAndAngles(props.getReturnPortalX(), props.getReturnPortalY(), props.getReturnPortalZ(), entityIn.rotationYaw, 0.0F);
            entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;            
            return true;
	    }
	    
	    return false;
	}

	private void generatePortalRoom(World world, int x, int y, int z) {

        for(int n = 0; n < 16; n++) {
            for(int m = 0; m < 16; m++) {
                for(int o = 1; o < 8; o++) {
                    world.setBlockState(new BlockPos(x+n, y+o, z+m), Blocks.air.getDefaultState());
                }
            }
        }
/*Portal Blocks*/
	}
	
	@Override
	public boolean makePortal(Entity entity) {
	    return false;
	}
}