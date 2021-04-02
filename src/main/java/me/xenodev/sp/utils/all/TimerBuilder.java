package me.xenodev.sp.utils.all;

import me.xenodev.sp.file.TimeFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.TimeSQL;
import me.xenodev.sp.utils.player.ScoreBoardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TimerBuilder {

    private static int sched1;
    private static int sched2;

    public static void startScoreboard() {
    if (!Bukkit.getScheduler().isCurrentlyRunning(sched1)) {
        sched1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    ScoreBoardBuilder.updateScoreboard(all);
                }
            }
        }, 0L, 20L);
    }
}

    public static void startOnlinetime() {
        if (!Bukkit.getScheduler().isCurrentlyRunning(sched2)) {
            sched2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")) {
                            TimeSQL.addSeconds(all.getUniqueId(), 1);
                            if (TimeSQL.getSeconds(all.getUniqueId()) == 60) {
                                TimeSQL.addMinutes(all.getUniqueId(), 1);
                                TimeSQL.setSeconds(all.getUniqueId(), 0);
                            }

                            if (TimeSQL.getMinutes(all.getUniqueId()) == 60) {
                                TimeSQL.addHours(all.getUniqueId(), 1);
                                TimeSQL.setMinutes(all.getUniqueId(), 0);
                            }
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            TimeFilebuilder.addSeconds(all, 1);
                            if (TimeFilebuilder.getSeconds(all) == 60) {
                                TimeFilebuilder.addMinutes(all, 1);
                                TimeFilebuilder.setSeconds(all, 0);
                            }

                            if (TimeFilebuilder.getMinutes(all) == 60) {
                                TimeFilebuilder.addHours(all, 1);
                                TimeFilebuilder.setMinutes(all, 0);
                            }
                        }
                    }
                }
            }, 0L, 20L);
        }
    }
}
