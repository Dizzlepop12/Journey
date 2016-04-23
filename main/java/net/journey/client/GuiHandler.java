package net.journey.client;

import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.journey.blocks.tileentity.TileEntityTrophyTable;
import net.journey.blocks.tileentity.container.ContainerKnowledgeTable;
import net.journey.blocks.tileentity.container.ContainerSummoningTable;
import net.journey.blocks.tileentity.container.ContainerTrophy;
import net.journey.client.render.gui.GuiAlloyMender;
import net.journey.client.render.gui.GuiBlacksmith;
import net.journey.client.render.gui.GuiBoilTrader;
import net.journey.client.render.gui.GuiEscaped;
import net.journey.client.render.gui.GuiFrozenMerchant;
import net.journey.client.render.gui.GuiKnowledgeTable;
import net.journey.client.render.gui.GuiMage;
import net.journey.client.render.gui.GuiOvergrownMerchant;
import net.journey.client.render.gui.GuiRockite;
import net.journey.client.render.gui.GuiStaringGuardian;
import net.journey.client.render.gui.GuiStarlightBlacksmith;
import net.journey.client.render.gui.GuiStarlightVillager;
import net.journey.client.render.gui.GuiStoneCraftingTable;
import net.journey.client.render.gui.GuiSummoningTable;
import net.journey.client.render.gui.GuiTerranian;
import net.journey.client.render.gui.GuiTordo;
import net.journey.client.render.gui.GuiTrophyTable;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.slayer.api.entity.tileentity.container.ContainerModCrafting;
import net.slayer.api.entity.tileentity.container.ContainerModVillager;

public class GuiHandler implements IGuiHandler {

	public enum GuiIDs {
		MAGE, 
		BLACKSMITH, 
		FROZEN_MERCHANT, 
		KNOWLEDGE, 
		SUMMONING, 
		STARING_GUARDIAN, 
		TORDO, BOIL_TRADER, 
		ALLOY_MENDER, 
		STARLIGHT_VILLAGER, 
		STARLIGHT_BLACKSMITH, 
		TERRANIAN, 
		TERRANIAN_ENCHANTER, 
		OVERGROWN_MERCHANT, 
		ESCAPED, 
		CRAFTING, 
		TROPHY, 
		ROCKITE;
	}

	public static int CRAFTING;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		if(ID == GuiIDs.MAGE.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.BLACKSMITH.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.FROZEN_MERCHANT.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.KNOWLEDGE.ordinal()) return new ContainerKnowledgeTable(player.inventory, (TileEntityKnowledgeTable)entity, world);
		if(ID == GuiIDs.SUMMONING.ordinal()) return new ContainerSummoningTable(player.inventory, (TileEntitySummoningTable)entity, world);
		if(ID == GuiIDs.TROPHY.ordinal()) return new ContainerTrophy(player.inventory, (TileEntityTrophyTable)entity, world);
		if(ID == GuiIDs.STARING_GUARDIAN.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.TORDO.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.BOIL_TRADER.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.ALLOY_MENDER.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.STARLIGHT_VILLAGER.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.STARLIGHT_BLACKSMITH.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.TERRANIAN.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.TERRANIAN_ENCHANTER.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.OVERGROWN_MERCHANT.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.ESCAPED.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if(ID == GuiIDs.ROCKITE.ordinal()) return new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world);
		if (ID == GuiIDs.CRAFTING.ordinal())
        {
            return new GuiStoneCraftingTable(player.inventory, null, world);
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		if(ID == GuiIDs.MAGE.ordinal()) return new GuiMage(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.BLACKSMITH.ordinal()) return new GuiBlacksmith(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.FROZEN_MERCHANT.ordinal()) return new GuiFrozenMerchant(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.KNOWLEDGE.ordinal()) return new GuiKnowledgeTable(player.inventory, (TileEntityKnowledgeTable)entity, world);
		if(ID == GuiIDs.SUMMONING.ordinal()) return new GuiSummoningTable(player.inventory, (TileEntitySummoningTable)entity, world);
		if(ID == GuiIDs.TROPHY.ordinal()) return new GuiTrophyTable(player.inventory, (TileEntityTrophyTable)entity, world);
		if(ID == GuiIDs.STARING_GUARDIAN.ordinal()) return new GuiStaringGuardian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.TORDO.ordinal()) return new GuiTordo(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.BOIL_TRADER.ordinal()) return new GuiBoilTrader(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.ALLOY_MENDER.ordinal()) return new GuiAlloyMender(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.STARLIGHT_VILLAGER.ordinal()) return new GuiStarlightVillager(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.STARLIGHT_BLACKSMITH.ordinal()) return new GuiStarlightBlacksmith(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.TERRANIAN.ordinal()) return new GuiTerranian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.TERRANIAN_ENCHANTER.ordinal()) return new GuiTerranian(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.OVERGROWN_MERCHANT.ordinal()) return new GuiOvergrownMerchant(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.ESCAPED.ordinal()) return new GuiEscaped(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.ROCKITE.ordinal()) return new GuiRockite(new ContainerModVillager(player.inventory, (IMerchant)getEntityByID(x, world), world), (IMerchant)getEntityByID(x, world));
		if(ID == GuiIDs.CRAFTING.ordinal()) return new GuiStoneCraftingTable(player.inventory, null, world);
		return null;
	}

	private Entity getEntityByID(int entityID, World world) {
		for(int i = 0; i < world.loadedEntityList.size(); i++) {
			if(((Entity)world.loadedEntityList.get(i)).getEntityId() == entityID) {
				return ((Entity)world.loadedEntityList.get(i));
			}
		}
		return null;
	}
}