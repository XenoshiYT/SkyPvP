package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StatsFilebuilder {

    private static File file = new File("plugins//" + Main.getInstance().getName() + "//Stats", "save.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setKills(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Kills", amount);
        save();
    }

    public static void setDeaths(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Deaths", amount);
        save();
    }

    public static void setStreak(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Streak", amount);
        save();
    }

    public static void addKills(OfflinePlayer p, Integer amount){
        setKills(p, getKills(p) + amount);
    }

    public static void addDeaths(OfflinePlayer p, Integer amount){
        setDeaths(p, getDeaths(p) + amount);
    }

    public static void addStreak(OfflinePlayer p, Integer amount){
        setStreak(p, getStreak(p) + amount);
    }

    public static void removeKills(OfflinePlayer p, Integer amount){
        setKills(p,getKills(p) - amount);
    }

    public static void removeDeaths(OfflinePlayer p, Integer amount){
        setDeaths(p,getDeaths(p) - amount);
    }

    public static void removeStreak(OfflinePlayer p, Integer amount){
        setStreak(p,getStreak(p) - amount);
    }

    public static void resetKills(OfflinePlayer p){
        cfg.set(p.getUniqueId().toString() + ".Kills", 0);
        save();
    }

    public static void resetDeaths(OfflinePlayer p){
        cfg.set(p.getUniqueId().toString() + ".Deaths", 0);
        save();
    }

    public static void resetStreak(OfflinePlayer p){
        cfg.set(p.getUniqueId().toString() + ".Streak", 0);
        save();
    }

    public static Integer getKills(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Kills");
    }

    public static Integer getDeaths(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Deaths");
    }

    public static Integer getStreak(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Streak");
    }

    public static Double getKD(OfflinePlayer p){
        Double kd = Double.valueOf(getKills(p) / getDeaths(p));
        return kd;
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
