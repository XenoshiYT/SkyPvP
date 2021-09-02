package me.xenodev.sp.events.all;

import me.xenodev.sp.file.CoinsFilebuilder;
import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.CoinsSQL;
import me.xenodev.sp.mysql.StatsSQL;
import me.xenodev.sp.utils.all.ActionBarBuilder;
import me.xenodev.sp.utils.all.RespawnBuilder;
import me.xenodev.sp.utils.player.RespawnItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;

public class DamageEvent implements Listener {

    public static ArrayList<Player> pvplist = new ArrayList<>();

    @EventHandler
    public void onDamage(PlayerDeathEvent e){
        if(e.getEntity() instanceof Player){
            Player p = e.getEntity();
            Player k = p.getKiller();

            if(k != null){
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    StatsFilebuilder.addDeaths(p, 1);
                    StatsFilebuilder.addKills(k,1);
                    StatsFilebuilder.addStreak(k, 1);
                    CoinsFilebuilder.addCoins(k, Main.getInstance().killcoins);
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    StatsSQL.addDeaths(p.getUniqueId(), 1);
                    StatsSQL.addKills(k.getUniqueId(),1);
                    StatsSQL.addStreak(k.getUniqueId(), 1);
                    CoinsSQL.addCoins(k.getUniqueId(), Main.getInstance().killcoins);
                }
                RespawnBuilder.respawn(p, 1);
                e.setDeathMessage(Main.getPrefix() + " §c" + k.getName() + " §7hat §e" + p.getName() + " §7getötet");
                new ActionBarBuilder("§7Du hast §a" + Main.getInstance().killcoins + " §7bekommen").send(k);
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
                RespawnBuilder.respawn(p, 1);
                e.setDeathMessage(Main.getPrefix() + " §e" + p.getName() + " §7ist von selbst gestorben");
            }

            pvplist.add(p);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    pvplist.remove(p);
                }
            }, 20*5);
        }
    }
}
