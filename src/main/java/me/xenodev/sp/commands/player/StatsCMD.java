package me.xenodev.sp.commands.player;

import me.xenodev.sp.file.StatsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.StatsSQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("stats")){
            if(args.length == 4){
                if(!p.hasPermission("skypvp.stats.edit")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.stats.edit");
                    return true;
                }
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                Integer amount = Integer.valueOf(args[3]);
                if(args[1].equalsIgnoreCase("set")){
                    if(args[2].equalsIgnoreCase("kills")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.setKills(t, amount);
                            p.sendMessage(Main.getPrefix()+ " §7Du hast dem Spieler §a" + t.getName() + " §7die Kills auf §e" + amount + " §7gesetzt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.setKills(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Kills auf §e" + amount + " §7gesetzt");
                        }
                    }else if(args[2].equalsIgnoreCase("tode")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.setDeaths(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Tode auf §e" + amount + " §7gesetzt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.setDeaths(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Tode auf §e" + amount + " §7gesetzt");
                        }
                    }else if(args[2].equalsIgnoreCase("streak")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.setStreak(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Streak auf §e" + amount + " §7gesetzt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.setStreak(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §7die Streak auf §e" + amount + " §7gesetzt");
                        }
                    }else{
                        p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[2] + " §cwurde nicht gefunden");
                        p.sendMessage(Main.getError() + " §7Benutze doch bitte <kills, tode, streak>");
                    }
                }else if(args[1].equalsIgnoreCase("add")){
                    if(args[2].equalsIgnoreCase("kills")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.addKills(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Kills hinzugefügt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.addKills(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Kills hinzugefügt");
                        }
                    }else if(args[2].equalsIgnoreCase("tode")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.addDeaths(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Tode hinzugefügt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.addDeaths(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Tode hinzugefügt");
                        }
                    }else if(args[2].equalsIgnoreCase("streak")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.addStreak(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Streak-Punkte hinzugefügt");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.addStreak(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Streak-Punkte hinzugefügt");
                        }
                    }else{
                        p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[2] + " §cwurde nicht gefunden");
                        p.sendMessage(Main.getError() + " §7Benutze doch bitte <kills, tode, streak>");
                    }
                }else if(args[1].equalsIgnoreCase("remove")){
                    if(args[2].equalsIgnoreCase("kills")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.removeKills(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Kills abgezogen");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.removeKills(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Kills abgezogen");
                        }
                    }else if(args[2].equalsIgnoreCase("tode")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.removeDeaths(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Tode abgezogen");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.removeDeaths(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Tode abgezogen");
                        }
                    }else if(args[2].equalsIgnoreCase("streak")){
                        if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                            StatsFilebuilder.removeStreak(t, amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Streak-Punkte abgezogen");
                        }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                            StatsSQL.removeStreak(t.getUniqueId(), amount);
                            p.sendMessage(Main.getPrefix() + " §7Du hast dem Spieler §a" + t.getName() + " §e" + amount + " §7Streak-Punkte abgezogen");
                        }
                    }else{
                        p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[2] + " §cwurde nicht gefunden");
                        p.sendMessage(Main.getError() + " §7Benutze doch bitte <kills, tode, streak>");
                    }
                }else{
                    p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[1] + " §cwurde nicht gefunden");
                    p.sendMessage(Main.getError() + " §7Benutze doch bitte <set, add, remove>");
                }
            }else if(args.length == 2){
                if(!p.hasPermission("skypvp.stats.reset")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.stats.reset");
                    return true;
                }
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                if(args[1].equalsIgnoreCase("reset")){
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        StatsFilebuilder.resetKills(t);
                        StatsFilebuilder.resetDeaths(t);
                        StatsFilebuilder.resetStreak(t);
                        p.sendMessage(Main.getPrefix() + " §7Du hast die Stats von §e" + t.getName() + " §7zurückgesetzt");
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        StatsSQL.resetKills(t.getUniqueId());
                        StatsSQL.resetDeaths(t.getUniqueId());
                        StatsSQL.resetStreak(t.getUniqueId());
                        p.sendMessage(Main.getPrefix() + " §7Du hast die Stats von §e" + t.getName() + " §7zurückgesetzt");
                    }
                }else{
                    p.sendMessage(Main.getError() + " §cDie Aussage §6" + args[1] + " §cwurde nicht gefunden");
                    p.sendMessage(Main.getError() + " §7Benutze doch bitte <reset>");
                }
            }else if(args.length == 1){
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
                if(args[0].equalsIgnoreCase("reset")){
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        StatsFilebuilder.resetKills(p);
                        StatsFilebuilder.resetDeaths(p);
                        StatsFilebuilder.resetStreak(p);
                        p.sendMessage(Main.getPrefix() + " §7Du hast deine Stats zurückgesetzt");
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        StatsSQL.resetKills(p.getUniqueId());
                        StatsSQL.resetDeaths(p.getUniqueId());
                        StatsSQL.resetStreak(p.getUniqueId());
                        p.sendMessage(Main.getPrefix() + " §7Du hast deine Stats zurückgesetzt");
                    }
                }else{
                    if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                        p.sendMessage(Main.getPrefix() + " §7Die Stats von §e§l" + t.getName() + "§7:");
                        p.sendMessage("§8§l» §7Kills: §a" + StatsFilebuilder.getKills(t));
                        p.sendMessage("§8§l» §7Tode: §a" + StatsFilebuilder.getDeaths(t));
                        p.sendMessage("§8§l» §7Streak: §a" + StatsFilebuilder.getStreak(t));
                        p.sendMessage("§8§l» §7KD: §a" + StatsFilebuilder.getKD(t));
                    }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                        p.sendMessage(Main.getPrefix() + " §7Die Stats von §e§l" + t.getName() + "§7:");
                        p.sendMessage("§8§l» §7Kills: §a" + StatsSQL.getKills(t.getUniqueId()));
                        p.sendMessage("§8§l» §7Tode: §a" + StatsSQL.getDeaths(t.getUniqueId()));
                        p.sendMessage("§8§l» §7Streak: §a" + StatsSQL.getStreak(t.getUniqueId()));
                        p.sendMessage("§8§l» §7KD: §a" + StatsSQL.getKD(t.getUniqueId()));
                    }
                }
            }else if(args.length == 0){
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    p.sendMessage(Main.getPrefix() + " §7Die Stats von §a§lDir" + "§7:");
                    p.sendMessage("§8§l» §7Kills: §a" + StatsFilebuilder.getKills(p));
                    p.sendMessage("§8§l» §7Tode: §a" + StatsFilebuilder.getDeaths(p));
                    p.sendMessage("§8§l» §7Streak: §a" + StatsFilebuilder.getStreak(p));
                    p.sendMessage("§8§l» §7KD: §a" + StatsFilebuilder.getKD(p));
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    p.sendMessage(Main.getPrefix() + " §7Die Stats von §a§lDir" + "§7:");
                    p.sendMessage("§8§l» §7Kills: §a" + StatsSQL.getKills(p.getUniqueId()));
                    p.sendMessage("§8§l» §7Tode: §a" + StatsSQL.getDeaths(p.getUniqueId()));
                    p.sendMessage("§8§l» §7Streak: §a" + StatsSQL.getStreak(p.getUniqueId()));
                    p.sendMessage("§8§l» §7KD: §a" + StatsSQL.getKD(p.getUniqueId()));
                }
            }else{
                p.sendMessage(Main.getError() + " §cDer Befehl ist nicht richtig, Benutze lieber:");
                p.sendMessage(Main.getError() + " §7/stats <name> <set, add, remove> <kills, tod, streak> <anzahl>");
                p.sendMessage(Main.getError() + " §7/stats <name> <reset>");
                p.sendMessage(Main.getError() + " §7/stats <name, reset>");
                p.sendMessage(Main.getError() + " §7/stats");
            }
        }
        return false;
    }
}
