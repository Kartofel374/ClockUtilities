package me.ziemniaczek.clockutilities.commands;

import me.ziemniaczek.clockutilities.ClockUtilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String sMethodArg, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.hasPermission("clockUtilities.reload")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to perform this command!");
                return true;
            }
        }
        ClockUtilities.getPlugin(ClockUtilities.class).reloadConfig();
        sender.sendMessage(ChatColor.RED + "Config reloaded!");
        return true;
    }
}
