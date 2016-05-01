package net.journey;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;

public class JourneyAchievements {
	
	public static final AchievementPage ap = new AchievementPage("Journey Achievements");
	public static Achievement achievementOre;
	public static Achievement achievementGem;
	public static Achievement achievementBoil;
	public static Achievement achievementSapphireSword;
	public static Achievement achievementKillNether;
	public static Achievement achievementEuca;
	public static Achievement achievementDepths;
	public static Achievement achievementCorba;
	public static Achievement achievementTerrania;
	public static Achievement achievementCloudia;
	public static Achievement achievementFl;
	public static Achievement achievementCave;
	public static Achievement achievementPet;
	public static Achievement achievementRoc;
	public static Achievement achievementThunderbird;
	public static Achievement achievementNetherBeast;
	public static Achievement achievementWitherBeast;
	public static Achievement achievementSoul;
	public static Achievement achievementBlazier;
	public static Achievement achievementCorallator;
	public static Achievement achievementEudor;
	public static Achievement achievementScale;
	public static Achievement achievementlogger;
	public static Achievement achievementSentry;
	public static Achievement achievementTerra;
	public static Achievement achievementSkyStalker;
	
	public static void init() {
		AchievementPage.registerAchievementPage(ap);
		registerMiscAchievements();
	}

	public static void registerMiscAchievements() {
		achievementOre = addAchievement("achievement.ore", "ore", 2, 0, new ItemStack(JourneyItems.sapphire), null);
		achievementFl = addAchievement("achievement.fl", "fl", -1, -2, new ItemStack(JourneyItems.crystalFlake), achievementOre).setSpecial();
		achievementGem = addAchievement("achievement.gem", "gem", -2, 0, new ItemStack(JourneyItems.blueGem), null);
		achievementSapphireSword = addAchievement("achievement.sapphireSword", "sapphireSword", 2, 2, new ItemStack(JourneyItems.sapphireSword), achievementOre);	
		achievementKillNether = addAchievement("achievement.netherKill", "netherKill", -4, -2, new ItemStack(JourneyItems.blood), achievementFl);
		achievementBoil = addAchievement("achievement.boil", "boil", -4, -4, new ItemStack(JourneyItems.boilingSkull), achievementKillNether).setSpecial();
		achievementEuca = addAchievement("achievement.euca", "euca", -2, -4, new ItemStack(JourneyItems.eucaPortalGem), achievementBoil).setSpecial();
		achievementDepths = addAchievement("achievement.depths", "depths", 0, -4, new ItemStack(JourneyItems.depthsPortalGem), achievementEuca).setSpecial();
		achievementCorba = addAchievement("achievement.corba", "corba", 2, -4, new ItemStack(JourneyItems.corbaPortalGem), achievementDepths).setSpecial();
		achievementTerrania = addAchievement("achievement.terrania", "terrania", 4, -4, new ItemStack(JourneyItems.terraniaPortalGem), achievementCorba).setSpecial();
		achievementCloudia = addAchievement("achievement.cloudia", "cloudia", 6, -4, new ItemStack(JourneyItems.cloudiaPortalGem), achievementTerrania).setSpecial();
		achievementCave = addAchievement("achievement.cave", "cave", 4, 1, new ItemStack(JourneyItems.caveCrystal), null);
		achievementPet = addAchievement("achievement.pet", "pet", -2, 1, new ItemStack(JourneyItems.floroPedal), null);
		achievementThunderbird = addAchievement("achievement.thunderbird", "thunderbird", 0, -5, new ItemStack(JourneyItems.thunderbirdOrb), achievementDepths);
		achievementRoc = addAchievement("achievement.roc", "roc", 2, -6, new ItemStack(JourneyItems.rocFeather), achievementThunderbird);
		achievementNetherBeast = addAchievement("achievement.netherBeast", "netherBeast", -6, -2, new ItemStack(JourneyItems.netherBeastOrb), achievementKillNether);
		achievementWitherBeast = addAchievement("achievement.witherBeast", "witherBeast", -6, -1, new ItemStack(JourneyItems.witheringBeastOrb), achievementKillNether);
		achievementSoul = addAchievement("achievement.soul", "soul", -6, 0, new ItemStack(JourneyItems.soulWatcherOrb), achievementKillNether);
		achievementBlazier = addAchievement("achievement.blazier", "blazier", -4, -5, new ItemStack(JourneyItems.blazierOrb), achievementBoil);
		achievementCorallator = addAchievement("achievement.corallator", "corallator", -2, -5, new ItemStack(JourneyItems.corallatorOrb), achievementEuca);
		achievementEudor = addAchievement("achievement.eudor", "eudor", -2, -3, new ItemStack(JourneyItems.eudorOrb), achievementEuca);
		achievementScale = addAchievement("achievement.scale", "scale", 0, -3, new ItemStack(JourneyItems.scaleOrb), achievementDepths);
		achievementlogger = addAchievement("achievement.logger", "logger", 2, -5, new ItemStack(JourneyItems.loggerOrb), achievementCorba);
		achievementSentry = addAchievement("achievement.sentry", "sentry", 2, -3, new ItemStack(JourneyItems.sentryKingOrb), achievementCorba);
		achievementTerra = addAchievement("achievement.terra", "terra", 4, -5, new ItemStack(JourneyItems.enchantedTerrastar), achievementTerrania);
		achievementSkyStalker = addAchievement("achievement.sky", "sky", 6, -5, new ItemStack(JourneyItems.mysteriousDisk), achievementCloudia);
	}

	public static Achievement addAchievement(String n, String id, int x, int y, ItemStack icon, Achievement a) {
		Achievement achievement = new Achievement(n, id, x, y, icon, a);
		achievement.registerStat();
		ap.getAchievements().add(achievement);
		return achievement;
	}
}