package net.journey;

import java.util.ArrayList;

import net.journey.event.message.MessageDarkEnergyBar;
import net.journey.event.message.MessageEssenceBar;
import net.journey.event.message.MessagePowerBar;
import net.journey.misc.EnchantmentHotTouch;
import net.journey.misc.EnchantmentWaterWalk;
import net.journey.proxy.CommonProxy;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.slayer.api.SlayerAPI;

@Mod(name = SlayerAPI.MOD_NAME, modid = SlayerAPI.MOD_ID, version = SlayerAPI.MOD_VERSION, dependencies = "after:guideapi")
public class JITL {

	@Instance(SlayerAPI.MOD_ID)
	public static JITL instance;

	@SidedProxy(clientSide = "net.journey.proxy.ClientProxy", serverSide = "net.journey.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Enchantment hotTouch = new EnchantmentHotTouch(165, 3);
	public static final Enchantment waterWalk = new EnchantmentWaterWalk(164, 3);
	public static final Material tropicalMat = new Material(MapColor.waterColor);
	
	public static SimpleNetworkWrapper wrapper;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("EssenceNetwork");
		wrapper.registerMessage(MessageDarkEnergyBar.DarkEnergyHandler.class, MessageDarkEnergyBar.class, 0, Side.CLIENT);
		wrapper.registerMessage(MessageEssenceBar.EssenceHandler.class, MessageEssenceBar.class, 1, Side.CLIENT);
		wrapper.registerMessage(MessagePowerBar.PowerHandler.class, MessagePowerBar.class, 2, Side.CLIENT);
		proxy.preInit(event);
		proxy.registerClient();
		proxy.clientPreInit();
		ArrayList<String> author = new ArrayList<String>();
		author.add("The_SlayerMC");
		event.getModMetadata().autogenerated = false;
		event.getModMetadata().credits = 
				SlayerAPI.Colour.AQUA +		 "Dizzlepop12 - Owner/Developer/Artist/Models, " + 
				SlayerAPI.Colour.BLUE + 	 "The_SlayerMC - Creator/Code/Ex-Developer, " + 
				SlayerAPI.Colour.DARK_AQUA + "Eternaldoom - Code Helper, " + 
				SlayerAPI.Colour.DARK_BLUE + "Drybones - Assistant Artist";
		
		event.getModMetadata().description = "A full progressional experience that adds weapons, mobs, dimensions, bosses, and much more to your game.";
		event.getModMetadata().modId = SlayerAPI.MOD_ID;
		event.getModMetadata().url = "wiki.essenceofthegods.net";
		event.getModMetadata().name = SlayerAPI.MOD_NAME;
		event.getModMetadata().version = SlayerAPI.MOD_VERSION;
		event.getModMetadata().logoFile = "assets/essence/textures/logo.png";
		event.getModMetadata().authorList = author;
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
		proxy.clientInit(event);
		proxy.registerModModels();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		proxy.registerSounds();
	}

	@EventHandler
	public static void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
}
