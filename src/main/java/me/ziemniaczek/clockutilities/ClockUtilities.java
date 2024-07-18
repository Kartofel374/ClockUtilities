package me.ziemniaczek.clockutilities;

import me.ziemniaczek.clockutilities.commands.ReloadCommand;
import me.ziemniaczek.clockutilities.commands.TimerCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ClockUtilities extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        CheckConfig.check(this);
        Objects.requireNonNull(this.getCommand("timer")).setExecutor(new TimerCommand());
        Objects.requireNonNull(this.getCommand("reloadClockUtil")).setExecutor(new ReloadCommand());
    }
}
