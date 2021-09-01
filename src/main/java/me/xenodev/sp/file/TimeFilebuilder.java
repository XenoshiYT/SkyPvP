package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TimeFilebuilder {

    private static File file = new File("plugins//" + Main.getInstance().getName() + "//Time", "save.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setSeconds(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Seconds", amount);
        save();
    }

    public static void setMinutes(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Minutes", amount);
        save();
    }

    public static void setHours(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Hours", amount);
        save();
    }

    public static void addSeconds(OfflinePlayer p, Integer amount){
        setSeconds(p, getSeconds(p) + amount);
    }

    public static void addMinutes(OfflinePlayer p, Integer amount){
        setMinutes(p, getMinutes(p) + amount);
    }

    public static void addHours(OfflinePlayer p, Integer amount){
        setHours(p, getHours(p) + amount);
    }

    public static Integer getSeconds(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Seconds");
    }

    public static Integer getMinutes(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Minutes");
    }

    public static Integer getHours(OfflinePlayer p){
        return cfg.getInt(p.getUniqueId().toString() + ".Hours");
    }

    public static String changeTime(OfflinePlayer p){
        if(getHours(p) != 0){
            return getHours(p) + "h";
        }else if(getMinutes(p) >= 0 && getHours(p) == 0){
            return getMinutes(p) + " min";
        }
        return "kein Eintrag!";
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
