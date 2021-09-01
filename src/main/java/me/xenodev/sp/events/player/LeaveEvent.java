package me.xenodev.sp.events.player;

import me.xenodev.sp.events.all.DamageEvent;
import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.StatsSQL;
import me.xenodev.sp.utils.player.ScoreBoardBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(Main.getPrefix() + " §7Der Spieler §e" + p.getName() + " §7hat den Server verlassen");

        if(DamageEvent.pvplist.contains(p)){
            p.getInventory().clear();
            if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                StatsFilebuilder.addDeaths(p, 1);
            }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                StatsSQL.addDeaths(p.getUniqueId(), 1);
            }
        }

    }
}
