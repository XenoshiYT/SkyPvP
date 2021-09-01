package me.xenodev.sp.commands.all;

import me.xenodev.sp.events.all.DamageEvent;
import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class SpawnCMD implements CommandExecutor {

    public static ArrayList<Player> spawndown = new ArrayList<>();
    public static HashMap<Player, Location> spawnloc = new HashMap<>();
    public static BukkitTask sched1;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("spawn")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("set")){
                    if(!p.hasPermission("skypvp.spawn.set")) {
                        p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.spawn.set");
                        return true;
                    }
                    LocationFilebuilder.setLocation("Spawn", p.getLocation());
                    p.sendMessage(Main.getPrefix() + "§7Du hast den §6Spawn §7gesetzt");
                }
            }else{
                if(!DamageEvent.pvplist.contains(p)) {
                    spawndown.add(p);
                    spawnloc.put(p, p.getLocation());
                    p.sendMessage(Main.getPrefix() + " §7Du wirst in 5 Sekunden teleportiert! §cBewege dich nicht");
                    sched1 = new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (spawndown.contains(p)) {
                                Location loc = LocationFilebuilder.getLocation("Spawn");
                                p.teleport(loc);
                                p.sendMessage(Main.getPrefix() + "§7Du hast dich zum §6Spawn §7teleportiert");
                                spawndown.remove(p);
                                spawnloc.remove(p);
                                sched1.cancel();
                            }
                        }
                    }.runTaskLater(Main.getInstance(), 20 * 5);
                }else{
                    p.sendMessage(Main.getError() + " §cDu befindest dich im Kampf! Du solltest dich nicht ausloggen oder dich teleportieren");
                }
            }
        }


        return false;
    }

}
