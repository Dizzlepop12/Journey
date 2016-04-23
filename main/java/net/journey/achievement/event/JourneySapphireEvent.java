package net.journey.achievement.event;

import net.journey.JourneyAchievements;
import net.journey.JourneyItems;
import net.journey.proxy.CommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JourneySapphireEvent {
	
	@SubscribeEvent
	public void onSapphireMineEvent(PlayerEvent.ItemPickupEvent e) {
		if(e.pickedUp.getEntityItem().isItemEqual(new ItemStack(JourneyItems.sapphire))) {
			e.player.addStat(JourneyAchievements.achievementOre, 1);
		}
	}
}