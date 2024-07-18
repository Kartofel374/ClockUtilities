package me.ziemniaczek.clockutilities;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CheckConfig {
    public static void check(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        plugin.getLogger().warning("1");
        //
        //timer section
        //
        //timer.repeats
        if(!config.contains("timer.repeats")) {
            plugin.getLogger().warning("[Config] timer.repeats don't exist! Setting do default (25)");
            config.set("timer.repeats", 25);
        }
        if(config.getInt("timer.repeats") < 0) {
            plugin.getLogger().warning("[Config] timer.repeats must be positive or zero! Setting to default (25)");
            config.set("timer.repeats", 25);
        }
        //timer.delay
        if(!config.contains("timer.delay")) {
            plugin.getLogger().warning("[Config] timer.delay don't exist! Setting do default (250)");
            config.set("timer.delay", 250);
        }
        if(config.getInt("timer.delay") <= 0) {
            plugin.getLogger().warning("[Config] timer.delay must be positive number! Setting to default (250)");
            config.set("timer.delay", 250);
        }
        //timer.wait
        if(!config.contains("timer.wait")) {
            plugin.getLogger().warning("[Config] timer.wait don't exist! Setting do default (10000)");
            config.set("timer.wait", 10000);
        }
        if(config.getInt("timer.wait") < -1) {
            plugin.getLogger().warning("[Config] timer.wait must be positive, zero, or -1! Setting to default (10000)");
            config.set("timer.wait", 10000);
        }
        //
        //Saving config
        //
        plugin.reloadConfig();
    }
}
