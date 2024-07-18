package me.ziemniaczek.clockutilities;

import me.ziemniaczek.clockutilities.commands.TimerCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClockUtilities extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("timer").setExecutor(new TimerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
