package me.xenodev.sp.events.player;

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

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
            StatsSQL.createPlayer(p.getUniqueId());
        }
        ScoreBoardBuilder.setScoreboard(p);

        if(LocationFilebuilder.getLocation("Spawn") == null){
            p.sendMessage(Main.getInstance().error + " §4§lDer Spawn wurde noch nicht gesetzt! Bitte den Spawn setzten mit /spawn set");
        }else{
            Location loc = LocationFilebuilder.getLocation("Spawn");
            p.teleport(loc);
        }

        e.setJoinMessage(Main.getInstance().prefix + " §7Der Spieler §e" + p.getName() + " §7ist dem Server beigetreten");

    }
}
