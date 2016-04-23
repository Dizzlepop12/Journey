package net.journey.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatHandler {

	@SideOnly(Side.CLIENT)
	public static void sendChat(Entity entityLiving, String string, Object...o) {
		ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(string, o);
		chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.GRAY);
		if(entityLiving != null && entityLiving instanceof EntityPlayerMP) ((EntityPlayerMP) entityLiving).addChatMessage(chatcomponenttranslation);
	}

	@SideOnly(Side.CLIENT)
	public static void sendFormattedChat(EntityPlayer entityLiving, EnumChatFormatting chatformat, String string, Object...o) {
		ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(string, o);
		chatcomponenttranslation.getChatStyle().setColor(chatformat);
		if(entityLiving != null) entityLiving.addChatMessage(chatcomponenttranslation);
	}

	@SideOnly(Side.SERVER)
	public static void sendServerMessage(String string) {
		ChatComponentTranslation translation = new ChatComponentTranslation(string);
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(translation);
	}
}