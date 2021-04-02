package me.xenodev.sp.main;

import me.xenodev.sp.commands.all.BuildCMD;
import me.xenodev.sp.commands.all.DeathheightCMD;
import me.xenodev.sp.commands.all.SpawnCMD;
import me.xenodev.sp.commands.player.OnlinezeitCMD;
import me.xenodev.sp.commands.player.StatsCMD;
import me.xenodev.sp.events.all.DamageEvent;
import me.xenodev.sp.events.all.MoveEvent;
import me.xenodev.sp.events.player.JoinEvent;
import me.xenodev.sp.events.player.LeaveEvent;
import me.xenodev.sp.file.ConfigFilebuilder;
import me.xenodev.sp.mysql.MySQL;
import me.xenodev.sp.utils.all.TimerBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public String prefix;
    public String error;
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

    public MySQL mysql;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        ConfigFilebuilder.getConfig();

        if(datasave.equalsIgnoreCase("MySQL")){
            ConnectMySQL();
        }

        TimerBuilder.startScoreboard();
        TimerBuilder.startOnlinetime();

        commands();
        events();
    }

    @Override
    public void onDisable() {
        mysql.close();
    }

    public static Main getInstance(){
        return instance;
    }

    private void ConnectMySQL(){
        mysql = new MySQL(mysqlhost, mysqldatabase, mysqlusername, mysqlpassword);
        mysql.update("CREATE TABLE IF NOT EXISTS Time(UUID VARCHAR(100),HOURS BIGINT,MINUTES INT,SECONDS INT)");
        mysql.update("CREATE TABLE IF NOT EXISTS Stats(UUID VARCHAR(100),KILLS BIGINT,DEATHS BIGINT,STREAK BIGINT)");
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
    }

    private void events(){
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
    }
}
