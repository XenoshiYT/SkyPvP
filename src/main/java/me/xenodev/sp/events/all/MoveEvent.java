package me.xenodev.sp.events.all;

import me.xenodev.sp.commands.all.SpawnCMD;
import me.xenodev.sp.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if(p.getLocation().getY() <= Main.getInstance().deathheight){
            p.setHealth(0);
            p.sendMessage(Main.getInstance().prefix + " §7Du bist zu weit runter gefallen");
        }

        if(SpawnCMD.spawndown.contains(p)){
            SpawnCMD.spawndown.remove(p);
            p.sendMessage(Main.getInstance().prefix + " §7Du hast dich bewegt, deshalb wirst du nicht teleportiert!");
        }
    }

}
