package me.xenodev.sp.main;

import me.xenodev.sp.commands.all.*;
import me.xenodev.sp.commands.player.CoinsCMD;
import me.xenodev.sp.commands.player.KitCMD;
import me.xenodev.sp.commands.player.OnlinezeitCMD;
import me.xenodev.sp.commands.player.StatsCMD;
import me.xenodev.sp.events.all.*;
import me.xenodev.sp.events.player.JoinEvent;
import me.xenodev.sp.events.player.LeaveEvent;
import me.xenodev.sp.file.ConfigFilebuilder;
import me.xenodev.sp.mysql.MySQL;
import me.xenodev.sp.utils.all.TimerBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.*;

public class Main extends JavaPlugin {

    public String servername;
    public String prefix;
    public String error;
    public String header;
    public String footer;
    public String discord;
    public String teamspeak;
    public String website;
    public String datasave;
    public String mysqlhost;
    public String mysqldatabase;
    public String mysqlusername;
    public String mysqlpassword;
    public String scoreboarddisplay;
    public String scoreboardinfo;
    public Integer deathheight;
    public Boolean falldamage;
    public Boolean fooddamage;

    public Integer killcoins;
    public Integer startcoins;
    public Integer kit1cost;
    public Integer kit2cost;
    public Integer kit3cost;
    public Integer kit4cost;
    public Integer kit5cost;
    public String kit1name;
    public String kit2name;
    public String kit3name;
    public String kit4name;
    public String kit5name;
    public String kit1head;
    public String kit2head;
    public String kit3head;
    public String kit4head;
    public String kit5head;

    public MySQL mysql;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        checkPlugins();
        this.saveDefaultConfig();
        FileConfiguration fileConfiguration = this.getConfig();
        ConfigFilebuilder.getConfig();
        System.out.println("-------------- [ " + getDescription().getName() + " ] --------------");
        System.out.println("Das Plugin wurde erfolgreich auf Version v" + getDescription().getVersion() + " geladen!");
        System.out.println("Coded by XenoDEV");
        if(datasave.equalsIgnoreCase("MySQL")){
            ConnectMySQL();
        }
        System.out.println("-------------- [ " + getDescription().getName() + " ] --------------");

        TimerBuilder.startScoreboard();
        TimerBuilder.startOnlinetime();
        TimerBuilder.startTablist();

        commands();
        events();
    }

    @Override
    public void onDisable() {
        if(datasave.equalsIgnoreCase("MySQL")){
            mysql.close();
        }
    }

    public static Main getInstance(){
        return instance;
    }

    public static String getPrefix(){
        return getInstance().prefix.replace("&", "§");
    }

    public static String getError(){
        return getInstance().error.replace("&", "§");
    }

    private void ConnectMySQL(){
        mysql = new MySQL(mysqlhost, mysqldatabase, mysqlusername, mysqlpassword);
        mysql.update("CREATE TABLE IF NOT EXISTS Time(UUID VARCHAR(100),HOURS BIGINT,MINUTES INT,SECONDS INT)");
        mysql.update("CREATE TABLE IF NOT EXISTS Stats(UUID VARCHAR(100),KILLS BIGINT,DEATHS BIGINT,STREAK BIGINT)");
        mysql.update("CREATE TABLE IF NOT EXISTS Coins(UUID VARCHAR(100),Coins BIGINT)");
        System.out.println("Die MySQL-Verbindung wurde hergestellt");
    }

    private void commands(){
        getServer().getPluginCommand("build").setExecutor(new BuildCMD());
        getServer().getPluginCommand("deathheight").setExecutor(new DeathheightCMD());
        getServer().getPluginCommand("oz").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("ot").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("onlinezeit").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("onlinetime").setExecutor(new OnlinezeitCMD());
        getServer().getPluginCommand("spawn").setExecutor(new SpawnCMD());
        getServer().getPluginCommand("stats").setExecutor(new StatsCMD());
        getServer().getPluginCommand("coins").setExecutor(new CoinsCMD());
        getServer().getPluginCommand("kit").setExecutor(new KitCMD());
        getServer().getPluginCommand("kits").setExecutor(new KitCMD());
        getServer().getPluginCommand("config").setExecutor(new ConfigReloadCMD());
        getServer().getPluginCommand("enchant").setExecutor(new EnchantCMD());
        getServer().getPluginCommand("standard").setExecutor(new StandardCMD());
    }

    private void events(){
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getServer().getPluginManager().registerEvents(new FreeItemEvent(), this);
        getServer().getPluginManager().registerEvents(new BuildEvent(), this);
        getServer().getPluginManager().registerEvents(new WeatherEvent(), this);
        getServer().getPluginManager().registerEvents(new KitSelectEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new FallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodEvent(), this);
    }

    private void checkPlugins(){
        if(getServer().getPluginManager().getPlugin("PermissionsEx") == null){
            System.out.print("[ERROR] PermissionsEx wurde nicht gefunden! Server wird herunter gefahren");
            System.out.print("[ERROR] Bitte Installieren sie PermissionsEx auf Ihrem Server");
            getServer().shutdown();
        }
    }
}
