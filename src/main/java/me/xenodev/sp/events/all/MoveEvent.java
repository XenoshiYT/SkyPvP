package me.xenodev.sp.events.all;

import me.xenodev.sp.commands.all.SpawnCMD;
import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.utils.player.RespawnItemBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if(p.getLocation().getY() <= Main.getInstance().deathheight){
            p.getInventory().clear();
            Location loc = LocationFilebuilder.getLocation("Spawn");
            p.teleport(loc);
            StatsFilebuilder.addDeaths(p, 1);
            p.sendMessage(Main.getPrefix() + " §cDu bist zu weit runter gefallen und wurdest respawnt");
        }

        if(SpawnCMD.spawndown.contains(p)){
            Location loc = SpawnCMD.spawnloc.get(p);
            if(p.getLocation().getX() != loc.getX() || p.getLocation().getY() != loc.getY() || p.getLocation().getZ() != loc.getZ()){
                SpawnCMD.spawndown.remove(p);
                SpawnCMD.spawnloc.remove(p);
                SpawnCMD.sched1.cancel();
                p.sendMessage(Main.getPrefix() + " §cTeleport abgebrochen! Du hast dich bewegt");
            }
        }
    }

}
