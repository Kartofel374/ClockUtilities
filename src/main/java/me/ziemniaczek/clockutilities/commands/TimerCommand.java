package me.ziemniaczek.clockutilities.commands;

import me.ziemniaczek.clockutilities.threads.ScoreboardUpdateTime;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String sMethodArg, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You can't execute this command!");
            return true;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("clockUtilities.timer")) {
            p.sendMessage(ChatColor.RED + "You don't have permission to perform this command!");
            return true;
        }
        int h, m, s;
        h=m=s=0;
        try {
            switch (args.length) {
                case 3:
                    h += Integer.parseInt(args[2]);
                case 2:
                    m += Integer.parseInt(args[1]);
                case 1:
                    s += Integer.parseInt(args[0]);
                    break;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Time must be an positive integer!");
            return true;
        }
        if ((h+m+s) <= 0 || h < 0 || m < 0 || s < 0) {
            sender.sendMessage(ChatColor.RED + "Time must be an positive integer!");
            return true;
        }
        if(s > 59) {
            while (s > 59) {
                m++;
                s-=60;
            }
        }
        if(m > 59) {
            while (m > 59) {
                h++;
                m-=60;
            }
        }
        Scoreboard board = p.getScoreboard();
        new ScoreboardUpdateTime(p, board, h, m, s).start();
        return true;
    }
}
