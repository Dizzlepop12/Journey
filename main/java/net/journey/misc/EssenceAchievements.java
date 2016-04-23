package net.journey.misc;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;

public class EssenceAchievements {
	
	public static Achievement getOverworldOre = addAchievement("essence.getModOre", 0, 0, JourneyBlocks.shadiumOre, null, false);
	public static Achievement getSapphire = addAchievement("essence.getSapphire", 0, 10, JourneyItems.sapphire, null, false);
	public static Achievement makeOreBlock = addAchievement("essence.getOreBlock", 0, 20, JourneyBlocks.shadiumOre, null, false);
	public static Achievement makeSpawner = addAchievement("essence.getSpawner", 0, 30, JourneyItems.spawnerBar, null, false);

    private static Achievement addAchievement(String name, int x, int y, Block image, Achievement haveFirst, boolean isSpecial){
		return (Achievement) (isSpecial ? new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat() : new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat());
	}
	
	private static Achievement addAchievement(String name, int x, int y, Item image, Achievement haveFirst, boolean isSpecial){
		return (Achievement) (isSpecial ? new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat() : new Achievement(name, name, x, y, image, (Achievement)haveFirst).registerStat());
	}
    
    public static AchievementPage page = new AchievementPage("Essence Of The Gods", getOverworldOre, getSapphire, makeOreBlock, makeSpawner);
    
    public static void init() {
		AchievementPage.registerAchievementPage(page);
	}
}