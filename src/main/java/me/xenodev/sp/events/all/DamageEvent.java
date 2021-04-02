package me.xenodev.sp.events.all;

import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.StatsSQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageEvent implements Listener {

    @EventHandler
    public void onMove(PlayerDeathEvent e){
        if(e.getEntity() instanceof Player){
            Player p = e.getEntity();
            Player k = p.getKiller();

            if(k != null){
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    StatsFilebuilder.addDeaths(p, 1);
                    StatsFilebuilder.addKills(k,1);
                    StatsFilebuilder.addStreak(k, 1);
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    StatsSQL.addDeaths(p.getUniqueId(), 1);
                    StatsSQL.addKills(k.getUniqueId(),1);
                    StatsSQL.addStreak(k.getUniqueId(), 1);
                }
                e.setDeathMessage(Main.getInstance().prefix + " §c" + k.getName() + " §7hat §e" + p.getName() + " §7getötet");
            }else{
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    StatsFilebuilder.addDeaths(p, 1);
                    if(!(StatsFilebuilder.getStreak(p) <= 0)){
                        StatsFilebuilder.removeStreak(p, 1);
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    StatsSQL.addDeaths(p.getUniqueId(), 1);
                    if(!(StatsSQL.getStreak(p.getUniqueId()) <= 0)){
                        StatsSQL.removeStreak(p.getUniqueId(), 1);
                    }
                }
                e.setDeathMessage(Main.getInstance().prefix + " §e" + p.getName() + " §7ist von selbst gestorben");
            }
        }
    }
}
