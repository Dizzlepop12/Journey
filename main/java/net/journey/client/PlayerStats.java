package net.journey.client;

import net.journey.util.Config;
import net.journey.util.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class PlayerStats {


	@SubscribeEvent
	public void tickEvent(RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		GuiIngame gig = mc.ingameGUI;
		FontRenderer font = mc.fontRendererObj;
		EntityPlayer player = mc.thePlayer;
		if(mc.currentScreen == null) {
			if(!mc.gameSettings.showDebugInfo) 
				if(SlayerAPI.BETA) mc.fontRendererObj.drawString(EnumChatFormatting.DARK_GREEN + "Essence of the Gods: " + EnumChatFormatting.DARK_RED + SlayerAPI.MOD_VERSION, 5, 5, 0);

			if(mc.gameSettings.showDebugInfo) {
				String st = I18n.format("essence.time", new Object[0]) + " " + formatTime(getWorldTime(mc));
				font.drawString(st, 2, 138, 0xFFFFFF);
			}
		}
	}

	public static Long getWorldTime(Minecraft mc) {
		return Long.valueOf(mc.theWorld.provider.getWorldTime());
	}

	public static String formatTime(Long time) {
		int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
		int hours = hours24 % 12;
		int minutes = (int)((float)time.longValue() / 16.666666F % 60.0F);
		String time1 = String.format("%02d:%02d %s", new Object[] { Integer.valueOf(hours < 1 ? 12 : hours), Integer.valueOf(minutes), hours24 < 12 ? "AM" : "PM" });
		return time1;
	}
}