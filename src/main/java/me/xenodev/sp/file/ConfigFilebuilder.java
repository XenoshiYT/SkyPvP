package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFilebuilder {

    public static void getConfig(){
        Main.getInstance().prefix = Main.getInstance().getConfig().getString("prefix");
        Main.getInstance().error = Main.getInstance().getConfig().getString("error");
        Main.getInstance().website = Main.getInstance().getConfig().getString("website");
        Main.getInstance().discord = Main.getInstance().getConfig().getString("discord");
        Main.getInstance().teamspeak = Main.getInstance().getConfig().getString("teamspeak");
        Main.getInstance().datasave = Main.getInstance().getConfig().getString("datasave");

        Main.getInstance().mysqlhost = Main.getInstance().getConfig().getString("MySQL.host");
        Main.getInstance().mysqldatabase = Main.getInstance().getConfig().getString("MySQL.database");
        Main.getInstance().mysqlusername = Main.getInstance().getConfig().getString("MySQL.username");
        Main.getInstance().mysqlpassword = Main.getInstance().getConfig().getString("MySQL.password");

        Main.getInstance().scoreboarddisplay = Main.getInstance().getConfig().getString("Scoreboard.display");
        Main.getInstance().scoreboardinfo = Main.getInstance().getConfig().getString("Scoreboard.info");

        Main.getInstance().deathheight = Main.getInstance().getConfig().getInt("deathheight");
    }
}
