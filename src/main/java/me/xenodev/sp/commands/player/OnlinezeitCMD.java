package me.xenodev.sp.commands.player;

import me.xenodev.sp.file.TimeFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.TimeSQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnlinezeitCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("oz") || cmd.getName().equalsIgnoreCase("onlinezeit") || cmd.getName().equalsIgnoreCase("onlinetime") || cmd.getName().equalsIgnoreCase("ot")){
            if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                if(args.length == 1){
                    OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                    p.sendMessage(Main.getPrefix() + "§7Die Onlinezeit von §e" + t.getName() + "§7:");
                    p.sendMessage("  §8§l» §7Stunden: §e" + TimeSQL.getHours(t.getUniqueId()));
                    p.sendMessage("  §8§l» §7Minuten: §e" + TimeSQL.getMinutes(t.getUniqueId()));
                    p.sendMessage("  §8§l» §7Sekunden: §e" + TimeSQL.getSeconds(t.getUniqueId()));
                }else{
                    p.sendMessage(Main.getPrefix() + "§7Die Onlinetime von §a§l" + "Dir" + "§7:");
                    p.sendMessage("  §8§l» §7Stunden: §e" + TimeSQL.getHours(p.getUniqueId()));
                    p.sendMessage("  §8§l» §7Minuten: §e" + TimeSQL.getMinutes(p.getUniqueId()));
                    p.sendMessage("  §8§l» §7Sekunden: §e" + TimeSQL.getSeconds(p.getUniqueId()));
                }
            }else if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                if(args.length == 1){
                    OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                    p.sendMessage(Main.getPrefix() + "§7Die Onlinezeit von §e" + t.getName() + "§7:");
                    p.sendMessage("§8§l» §7Stunden: §e" + TimeFilebuilder.getHours(t));
                    p.sendMessage("§8§l» §7Minuten: §e" + TimeFilebuilder.getMinutes(t));
                    p.sendMessage("§8§l» §7Sekunden: §e" + TimeFilebuilder.getSeconds(t));
                }else{
                    p.sendMessage(Main.getPrefix() + "§7Die Onlinetime von §a§l" + "Dir" + "§7:");
                    p.sendMessage("  §8§l» §7Stunden: §e" + TimeFilebuilder.getHours(p));
                    p.sendMessage("  §8§l» §7Minuten: §e" + TimeFilebuilder.getMinutes(p));
                    p.sendMessage("  §8§l» §7Sekunden: §e" + TimeFilebuilder.getSeconds(p));
                }
            }
        }
        return false;
    }
}
