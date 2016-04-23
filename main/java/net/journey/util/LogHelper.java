package net.journey.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraftforge.fml.common.FMLLog;
import net.slayer.api.SlayerAPI;

import org.apache.logging.log4j.Level;

public class LogHelper {

    private static BufferedWriter writer;
    
    static {
        File dir = new File("./EssenceOfTheGods");
        dir.mkdir();
        dir = new File("./EssenceOfTheGods/debug.log");
        try {
            writer = new BufferedWriter(new FileWriter(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void log(Level level, Object msg) {
        FMLLog.log(SlayerAPI.MOD_NAME, level, "[Essence of the gods] " + msg.toString());
        if(SlayerAPI.DEVMODE) writeFile(msg);
    }

    public static void debug(Object msg) {
        if(SlayerAPI.DEVMODE) log(Level.DEBUG, "[DEBUG] " + msg);
    }

    public static void error(Object msg) {
        log(Level.ERROR, msg);
    }

    public static void info(Object msg) {
        log(Level.INFO, msg);
    }

    public static void warn(Object msg) {
        log(Level.WARN, msg);
    }

    public static void dev(Object msg) {
        log(Level.INFO, msg);
    }
    
    public static void writeFile(Object msg){
        try {
            writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}