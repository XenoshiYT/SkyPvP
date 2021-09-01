package me.xenodev.sp.file;

import me.xenodev.sp.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFilebuilder {

    public static void getConfig(){
        Main.getInstance().servername = Main.getInstance().getConfig().getString("servername");
        Main.getInstance().prefix = Main.getInstance().getConfig().getString("prefix");
        Main.getInstance().error = Main.getInstance().getConfig().getString("error");
        Main.getInstance().header = Main.getInstance().getConfig().getString("header");
        Main.getInstance().footer = Main.getInstance().getConfig().getString("footer");
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

        Main.getInstance().killcoins = Main.getInstance().getConfig().getInt("killcoins");
        Main.getInstance().startcoins = Main.getInstance().getConfig().getInt("startcoins");
        Main.getInstance().kit1cost = Main.getInstance().getConfig().getInt("kit1.cost");
        Main.getInstance().kit2cost = Main.getInstance().getConfig().getInt("kit2.cost");
        Main.getInstance().kit3cost = Main.getInstance().getConfig().getInt("kit3.cost");
        Main.getInstance().kit4cost = Main.getInstance().getConfig().getInt("kit4.cost");
        Main.getInstance().kit5cost = Main.getInstance().getConfig().getInt("kit5.cost");
        Main.getInstance().kit1name = Main.getInstance().getConfig().getString("kit1.name");
        Main.getInstance().kit2name = Main.getInstance().getConfig().getString("kit2.name");
        Main.getInstance().kit3name = Main.getInstance().getConfig().getString("kit3.name");
        Main.getInstance().kit4name = Main.getInstance().getConfig().getString("kit4.name");
        Main.getInstance().kit5name = Main.getInstance().getConfig().getString("kit5.name");
        Main.getInstance().kit1head = Main.getInstance().getConfig().getString("kit1.head");
        Main.getInstance().kit2head = Main.getInstance().getConfig().getString("kit2.head");
        Main.getInstance().kit3head = Main.getInstance().getConfig().getString("kit3.head");
        Main.getInstance().kit4head = Main.getInstance().getConfig().getString("kit4.head");
        Main.getInstance().kit5head = Main.getInstance().getConfig().getString("kit5.head");

    }
}
