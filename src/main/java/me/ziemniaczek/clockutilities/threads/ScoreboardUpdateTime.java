package me.ziemniaczek.clockutilities.threads;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardUpdateTime extends Thread {

    private final Player p;
    private final Scoreboard scoreboard;
    private int h, m, s;

    public ScoreboardUpdateTime(final Player p, final Scoreboard scoreboard, final int h, final int m, final int s) {
        this.p = p;
        this.scoreboard = scoreboard;
        this.h = h;
        this.m = m;
        this.s = s;
    }

    @Override
    public void run() {
        while (s >= 0) {
            String sh, sm, ss;
            if(h < 10) {
                sh = "0" + h;
            } else {
                sh = "" + h;
            }if(m < 10) {
                sm = "0" + m;
            } else {
                sm = "" + m;
            }
            if(s < 10) {
                ss = "0" + s;
            } else {
                ss = "" + s;
            }
            final TextComponent name = Component.text("--------Timer--------").color(TextColor.color(255, 51, 51));
            Objective objective = scoreboard.registerNewObjective("time left","dummy", name);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score score = objective.getScore(ChatColor.AQUA + "Time left: " + ChatColor.WHITE + sh + ":" + sm + ":" + ss);
            score.setScore(1);
            p.setScoreboard(scoreboard);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s--;
            if(s < 0) {
                for(int i = 0; i < 25; i++) {
                    p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 10, 1);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(s <= 0 && m > 0) {
                s = 59;
                m--;
            }
            if(m <= 0 && h > 0) {
                m = 59;
                h--;
            }
            scoreboard.getObjective("time left").unregister();
        }
    }

}
