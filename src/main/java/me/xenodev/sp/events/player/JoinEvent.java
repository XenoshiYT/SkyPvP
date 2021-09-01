package me.xenodev.sp.events.player;

import me.xenodev.sp.file.CoinsFilebuilder;
import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.CoinsSQL;
import me.xenodev.sp.mysql.StatsSQL;
import me.xenodev.sp.mysql.TimeSQL;
import me.xenodev.sp.utils.all.ActionBarBuilder;
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
            TimeSQL.createPlayer(p.getUniqueId());
            CoinsSQL.createPlayer(p.getUniqueId());
        }

        ScoreBoardBuilder.setScoreboard(p);

        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
            if(CoinsFilebuilder.getCoins(p) == 0){
                CoinsFilebuilder.addCoins(p, Main.getInstance().startcoins);
            }
        }

        if(LocationFilebuilder.cfg.getString("Spawn.World") != null){
            Location loc = LocationFilebuilder.getLocation("Spawn");
            p.teleport(loc);
        }else{
            p.sendMessage(Main.getPrefix() + " §4§lDer Spawn wurde noch nicht gesetzt! Bitte den Spawn setzten mit /spawn set");
        }

        new ActionBarBuilder("§aWillkommen auf " + Main.getInstance().servername.replace("&", "§")).send(p);

        e.setJoinMessage(Main.getPrefix() + " §7Der Spieler §e" + p.getName() + " §7ist dem Server beigetreten");

    }
}
