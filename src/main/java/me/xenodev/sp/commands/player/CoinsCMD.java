package me.xenodev.sp.commands.player;

import me.xenodev.sp.file.CoinsFilebuilder;
import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.CoinsSQL;
import me.xenodev.sp.mysql.StatsSQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("coins")){
            if(args.length == 3){
                if(!p.hasPermission("skypvp.coins.edit")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.coins.edit");
                    return true;
                }
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                Integer amount = Integer.valueOf(args[2]);
                if(args[1].equalsIgnoreCase("set")){
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        CoinsFilebuilder.setCoins(p, amount);
                        p.sendMessage(Main.getPrefix()+ " §7Du hast dem Spieler §a" + t.getName() + " §7die Coins auf §e" + amount + " §7gesetzt");
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        CoinsSQL.setCoins(t.getUniqueId(), amount);
                        p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Coins auf §e" + amount + " §7gesetzt");
                    }
                }else if(args[1].equalsIgnoreCase("add")){
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        CoinsFilebuilder.addCoins(t, amount);
                        p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Coins hinzugefügt");
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        CoinsSQL.addCoins(t.getUniqueId(), amount);
                        p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Coins hinzugefügt");
                    }
                }else if(args[1].equalsIgnoreCase("remove")){
                    if (Main.getInstance().datasave.equalsIgnoreCase("File")) {
                        CoinsFilebuilder.removeCoins(t, amount);
                        p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Coins abgezogen");
                    } else if (Main.getInstance().datasave.equalsIgnoreCase("MySQL")) {
                        CoinsSQL.removeCoins(t.getUniqueId(), amount);
                        p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Coins abgezogen");
                    }
                }else{
                    p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[1] + " §cwurde nicht gefunden");
                    p.sendMessage(Main.getError() + " §7Benutze doch bitte <set, add, remove>");
                }
            }else if(args.length == 2){
                if(!p.hasPermission("skypvp.coins.reset")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.coins.reset");
                    return true;
                }
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                if(args[1].equalsIgnoreCase("reset")){
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        CoinsFilebuilder.setCoins(t, 0);
                        p.sendMessage(Main.getPrefix() + " §7Du hast die Coins von §e" + t.getName() + " §7zurückgesetzt");
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        CoinsSQL.setCoins(t.getUniqueId(), 0);
                        p.sendMessage(Main.getPrefix() + " §7Du hast die Coins von §e" + t.getName() + " §7zurückgesetzt");
                    }
                }else{
                    p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[1] + " §cwurde nicht gefunden");
                    p.sendMessage(Main.getError() + " §7Benutze doch bitte <reset>");
                }
            }else if(args.length == 1){
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    p.sendMessage(Main.getPrefix() + " §7Die Coins von §e§l" + t.getName() + "§7:");
                    p.sendMessage("§8§l» §7Coins: §a" + CoinsFilebuilder.getCoins(t));
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    p.sendMessage(Main.getPrefix() + " §7Die Coins von §e§l" + t.getName() + "§7:");
                    p.sendMessage("§8§l» §7Coins: §a" + CoinsSQL.getCoins(t.getUniqueId()));
                }
            }else if(args.length == 0){
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    p.sendMessage(Main.getPrefix() + " §7Die Coins von §a§lDir" + "§7:");
                    p.sendMessage("§8§l» §7Coins: §a" + CoinsFilebuilder.getCoins(p));
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    p.sendMessage(Main.getPrefix() + " §7Die Coins von §a§lDir" + "§7:");
                    p.sendMessage("§8§l» §7Coins: §a" + CoinsSQL.getCoins(p.getUniqueId()));
                }
            }else{
                p.sendMessage(Main.getError() + " §cDer Befehl ist nicht richtig, Benutze lieber:");
                p.sendMessage(Main.getError() + " §7/coins <name> <set, add, remove> <anzahl>");
                p.sendMessage(Main.getError() + " §7/coins <name> <reset>");
                p.sendMessage(Main.getError() + " §7/coins <name>");
                p.sendMessage(Main.getError() + " §7/coins");
            }
        }
        return false;
    }
}
