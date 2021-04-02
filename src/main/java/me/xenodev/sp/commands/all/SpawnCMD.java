package me.xenodev.sp.commands.all;

import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpawnCMD implements CommandExecutor {

    public static ArrayList<Player> spawndown = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("spawn")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("set")){
                    if(!p.hasPermission("skypvp.spawn.set")) {
                        p.sendMessage(Main.getInstance().error + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.spawn.set");
                        return true;
                    }
                    LocationFilebuilder.setLocation("Spawn", p.getLocation());
                    p.sendMessage(Main.getInstance().prefix + "§7Du hast den §6Spawn §7gesetzt");
                }
            }else{
                spawndown.add(p);
                p.sendMessage(Main.getInstance().prefix + " §7Du wirst in 5 Sekunden teleportiert! §cBewege dich nicht");
                new BukkitRunnable(){

                    @Override
                    public void run() {
                        if(spawndown.contains(p)){
                            Location loc = LocationFilebuilder.getLocation("Spawn");
                            p.teleport(loc);
                            p.sendMessage(Main.getInstance().prefix + "§7Du hast dich zum §6Spawn §7teleportiert");
                        }
                    }
                }.runTaskLater(Main.getInstance(), 20*5);
            }
        }


        return false;
    }

}
