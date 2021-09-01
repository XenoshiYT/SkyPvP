package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CoinsFilebuilder {

    private static File file = new File("plugins//" + Main.getInstance().getName() + "//Coins", "save.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setCoins(OfflinePlayer p, Integer amount){
        cfg.set(p.getUniqueId().toString() + ".Coins", amount);
        save();
    }

    public static void addCoins(OfflinePlayer p, Integer amount){
        setCoins(p, getCoins(p) + amount);
    }

    public static Integer getCoins(OfflinePlayer p){
        if(cfg.get(p.getUniqueId().toString() + ".Coins") != null){
            return cfg.getInt(p.getUniqueId().toString() + ".Coins");
        }
        return 0;
    }

    public static void removeCoins(OfflinePlayer p, Integer amount){
        setCoins(p, getCoins(p) - amount);
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
