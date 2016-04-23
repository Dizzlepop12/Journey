package net.journey.proxy;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JITL;
import net.journey.client.BarTickHandler;
import net.journey.client.BossTickHandler;
import net.journey.client.EntityRendering;
import net.journey.client.GuiHandler;
import net.journey.client.MusicHandler;
import net.journey.client.PlayerStats;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.PowerBar;
import net.journey.enums.EnumParticlesClasses;
import net.journey.event.ClientTickEvent;
import net.journey.event.UpdateCheckerEvent;
import net.journey.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerClient() {
		NetworkRegistry.INSTANCE.registerGuiHandler(JITL.instance, new GuiHandler());
		if(!SlayerAPI.DEVMODE) SlayerAPI.registerEvent(new UpdateCheckerEvent());
	}

	@Override
	public void clientPreInit() {
		SlayerAPI.registerModelBakery(JourneyBlocks.miniColouredBricks, miniBricks);
		SlayerAPI.registerModelBakery(JourneyBlocks.colouredBricks, brick);
		SlayerAPI.addBow(JourneyItems.flameBow, "flameBow");
		SlayerAPI.addBow(JourneyItems.poisonBow, "poisonBow");
		SlayerAPI.addBow(JourneyItems.darknessBow, "darknessBow");
		SlayerAPI.addBow(JourneyItems.frozenBow, "frozenBow");
		SlayerAPI.addBow(JourneyItems.staringBow, "staringBow");
		SlayerAPI.addBow(JourneyItems.deathPiercerBow, "deathPiercerBow");
		SlayerAPI.addBow(JourneyItems.fusionBow, "fusionBow");
		SlayerAPI.addBow(JourneyItems.springBow, "springBow");
		SlayerAPI.addBow(JourneyItems.starlightBow, "starlightBow");
		SlayerAPI.addBow(JourneyItems.wastefulBow, "wastefulBow");
		SlayerAPI.addBow(JourneyItems.flamingBow, "flamingBow");
		SlayerAPI.addBow(JourneyItems.darkEnforcer, "darkEnforcer");
		SlayerAPI.addBow(JourneyItems.depthsBow, "depthsBow");
		SlayerAPI.addBow(JourneyItems.frostbittenBow, "frostbittenBow");
		SlayerAPI.addBow(JourneyItems.frostyBow, "frostyBow");
		SlayerAPI.addBow(JourneyItems.charredBow, "charredBow");
		SlayerAPI.addBow(JourneyItems.fluffyBow, "fluffyBow");
		SlayerAPI.addBow(JourneyItems.golemBow, "golemBow");
		SlayerAPI.addBow(JourneyItems.loggersBow, "loggersBow");
		SlayerAPI.addBow(JourneyItems.overgrownBow, "overgrownBow");
		SlayerAPI.addBow(JourneyItems.overseerBow, "overseerBow");
		SlayerAPI.addBow(JourneyItems.woodlandBow, "woodlandBow");
		SlayerAPI.addBow(JourneyItems.rocsWing, "rocsWing");
		SlayerAPI.addBow(JourneyItems.scaleBow, "scaleBow");
		SlayerAPI.addBow(JourneyItems.mantleBow, "mantleBow");
		SlayerAPI.addBow(JourneyItems.coreExpender, "coreExpender");
		SlayerAPI.addBow(JourneyItems.royalBow, "royalBow");
		SlayerAPI.addBow(JourneyItems.blazingBow, "blazingBow");
		SlayerAPI.addBow(JourneyItems.darkTerraBow, "darkTerraBow");
		SlayerAPI.addBow(JourneyItems.lavenderBow, "lavenderBow");
		SlayerAPI.addBow(JourneyItems.terralightBow, "terralightBow");
		SlayerAPI.addBow(JourneyItems.terrianBow, "terrianBow");
	}

	@Override
	public void clientInit(FMLInitializationEvent event) {
		EntityRendering.init();
		SlayerAPI.registerEvent(new BossTickHandler());
		SlayerAPI.registerEvent(new ClientTickEvent());
		SlayerAPI.registerEvent(new PlayerStats());
	}

	public static String[] brick = {SlayerAPI.PREFIX + "blackColouredBrick", SlayerAPI.PREFIX + "blueColouredBrick", SlayerAPI.PREFIX + "brownColouredBrick", SlayerAPI.PREFIX + "cyanColouredBrick", SlayerAPI.PREFIX + "grayColouredBrick", SlayerAPI.PREFIX + "limeColouredBrick", SlayerAPI.PREFIX + "magentaColouredBrick", SlayerAPI.PREFIX + "orangeColouredBrick", SlayerAPI.PREFIX + "pinkColouredBrick", SlayerAPI.PREFIX + "purpleColouredBrick", SlayerAPI.PREFIX + "redColouredBrick", SlayerAPI.PREFIX + "whiteColouredBrick", SlayerAPI.PREFIX + "yellowColouredBrick"};
	public static String[] miniBricks = {SlayerAPI.PREFIX + "blackMiniColouredBrick", SlayerAPI.PREFIX + "blueMiniColouredBrick", SlayerAPI.PREFIX + "brownMiniColouredBrick", SlayerAPI.PREFIX + "cyanMiniColouredBrick", SlayerAPI.PREFIX + "grayMiniColouredBrick", SlayerAPI.PREFIX + "limeMiniColouredBrick", SlayerAPI.PREFIX + "magentaMiniColouredBrick", SlayerAPI.PREFIX + "orangeMiniColouredBrick", SlayerAPI.PREFIX + "pinkMiniColouredBrick", SlayerAPI.PREFIX + "purpleMiniColouredBrick", SlayerAPI.PREFIX + "redMiniColouredBrick", SlayerAPI.PREFIX + "whiteMiniColouredBrick", SlayerAPI.PREFIX + "yellowMiniColouredBrick"};
	public static String[] brickNames = {"blackColouredBrick", "blueColouredBrick", "brownColouredBrick", "cyanColouredBrick", "grayColouredBrick", "limeColouredBrick", "magentaColouredBrick", "orangeColouredBrick", "pinkColouredBrick", "purpleColouredBrick", "redColouredBrick", "whiteColouredBrick", "yellowColouredBrick"};
	public static String[] miniBricksName = {"blackMiniColouredBrick", "blueMiniColouredBrick", "brownMiniColouredBrick", "cyanMiniColouredBrick", "grayMiniColouredBrick", "limeMiniColouredBrick", "magentaMiniColouredBrick", "orangeMiniColouredBrick", "pinkMiniColouredBrick", "purpleMiniColouredBrick", "redMiniColouredBrick", "whiteMiniColouredBrick", "yellowMiniColouredBrick"};
	public static String[] brison = {"dark", "red", "small", "bstone"};

	@Override
	public void registerModModels() {
		for(String s : JourneyBlocks.blockName) {
			Item i = GameRegistry.findItem(SlayerAPI.MOD_ID, s);
			SlayerAPI.registerItemRender(i, s);
		}

		for(String s : JourneyItems.itemNames) {
			Item i = GameRegistry.findItem(SlayerAPI.MOD_ID, s);
			SlayerAPI.registerItemRender(i, s);
		}

		SlayerAPI.addBowRender(JourneyItems.flameBow, "flameBow");
		SlayerAPI.addBowRender(JourneyItems.poisonBow, "poisonBow");
		SlayerAPI.addBowRender(JourneyItems.darknessBow, "darknessBow");
		SlayerAPI.addBowRender(JourneyItems.frozenBow, "frozenBow");
		SlayerAPI.addBowRender(JourneyItems.staringBow, "staringBow");

		for(int i = 0; i < brickNames.length; i++) {
			Item it = GameRegistry.findItem(SlayerAPI.MOD_ID, "blockColouredBricks");
			SlayerAPI.registerItemRender(it, i, brickNames[i]);
		}

		for(int i = 0; i < miniBricksName.length; i++) {
			Item it = GameRegistry.findItem(SlayerAPI.MOD_ID, "blockMiniColouredBricks");
			SlayerAPI.registerItemRender(it, i, miniBricksName[i]);
		}

		for(int i = 0; i < 4; i++) {
			Item it = GameRegistry.findItem(SlayerAPI.MOD_ID, "brisonBlocks");
			SlayerAPI.registerItemRender(it, i, brison[i]);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void spawnParticle(EnumParticlesClasses particle, World worldObj, double posX, double posY, double posZ, boolean b) {
		try {
			EntityFX fx = null;
			if(b) {
				fx = (EntityFX)particle.getParticle().getConstructor(World.class, double.class, double.class, double.class).newInstance(worldObj, posX, posY, posZ);
			} else {
				fx = (EntityFX)particle.getParticle().getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class).newInstance(worldObj, posX, posY, posZ, 0D, 0D, 0D);
			}
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateDarkEnergy(int amount) {
		DarkEnergyBar.getProperties(Minecraft.getMinecraft().thePlayer).setBarValue(amount);
	}
	
	@Override
	public void updateEssence(int amount) {
		EssenceBar.getProperties(Minecraft.getMinecraft().thePlayer).setBarValue(amount);
	}
	
	@Override
	public void updatePower(int amount) {
		PowerBar.getProperties(Minecraft.getMinecraft().thePlayer).setBarValue(amount);
	}
}