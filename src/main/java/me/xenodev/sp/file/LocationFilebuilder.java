package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationFilebuilder {

    private static File file = new File("plugins//" + Main.getInstance().getName() + "//Location", "save.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setLocation(String name, Location loc){
        cfg.set(name + ".World", loc.getWorld().getName());
        cfg.set(name + ".X", loc.getX());
        cfg.set(name + ".Y", loc.getY());
        cfg.set(name + ".Z", loc.getZ());
        cfg.set(name + ".Yaw", loc.getYaw());
        cfg.set(name + ".Pitch", loc.getPitch());
        save();
    }

    public static Location getLocation(String name){
        World world = Bukkit.getWorld(cfg.getString(name + ".World"));
        double x = cfg.getDouble(name + ".X");
        double y = cfg.getDouble(name + ".Y");
        double z = cfg.getDouble(name + ".Z");
        Location loc = new Location(world, x, y, z);
        loc.setYaw(cfg.getInt(name + ".Yaw"));
        loc.setPitch(cfg.getInt(name + ".Pitch"));
        return loc;
    }

    private static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
