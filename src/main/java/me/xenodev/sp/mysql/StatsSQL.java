package me.xenodev.sp.mysql;

import me.xenodev.sp.main.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StatsSQL {

    public static boolean playerExists(UUID uuid){

        try{
            ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Time WHERE UUID= '" + uuid + "'");
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
            return false;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid){

        if(!(playerExists(uuid))){
            Main.getInstance().mysql.update("INSERT INTO Time(UUID, HOURS, MINUTES, SECONDS) VALUES ('" + uuid + "', '0', '0', '0');");
        }
    }

    public static Integer getKills(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("KILLS")) == null));
                i = rs.getInt("KILLS");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getKills(uuid);
        }

        return i;
    }

    public static Integer getDeaths(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("DEATHS")) == null));
                i = rs.getInt("DEATHS");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getDeaths(uuid);
        }

        return i;
    }

    public static Integer getStreak(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("STREAK")) == null));
                i = rs.getInt("STREAK");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getStreak(uuid);
        }

        return i;
    }

    public static void setKills(UUID uuid, Integer hours){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET KILLS= '" + hours + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setKills(uuid, hours);
        }
    }

    public static void setDeaths(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET DEATHS= '" + minutes + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setDeaths(uuid, minutes);
        }
    }

    public static void setStreak(UUID uuid, Integer seconds){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET STREAK= '" + seconds + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setStreak(uuid, seconds);
        }
    }

    public static void addKills(UUID uuid, Integer hours){
        if(playerExists(uuid)){
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() + hours.intValue()));
        }else{
            createPlayer(uuid);
            addKills(uuid, hours);
        }
    }

    public static void addDeaths(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() + minutes.intValue()));
        }else{
            createPlayer(uuid);
            addDeaths(uuid, minutes);
        }
    }

    public static void addStreak(UUID uuid, Integer seconds){
        if(playerExists(uuid)){
            setStreak(uuid, Integer.valueOf(getStreak(uuid).intValue() + seconds.intValue()));
        }else{
            createPlayer(uuid);
            addStreak(uuid, seconds);
        }
    }

    public static void removeKills(UUID uuid, Integer hours){
        if(playerExists(uuid)){
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() - hours.intValue()));
        }else{
            createPlayer(uuid);
            addKills(uuid, hours);
        }
    }

    public static void removeDeaths(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() - minutes.intValue()));
        }else{
            createPlayer(uuid);
            addDeaths(uuid, minutes);
        }
    }

    public static void removeStreak(UUID uuid, Integer minutes){
        if(playerExists(uuid)){
            setStreak(uuid, Integer.valueOf(getStreak(uuid).intValue() - minutes.intValue()));
        }else{
            createPlayer(uuid);
            addStreak(uuid, minutes);
        }
    }

    public static void resetKills(UUID uuid){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET KILLS= '0' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
        }
    }

    public static void resetDeaths(UUID uuid){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET DEATHS= '0' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
        }
    }

    public static void resetStreak(UUID uuid){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Stats SET STREAK= '0' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
        }
    }

    public static Double getKD(UUID uuid){
        Double kd;

        if(getDeaths(uuid) == 0 && getKills(uuid) == 0){
            kd = 0.0;
        } else if(getDeaths(uuid) == 0){
            kd = Double.valueOf(getKills(uuid));
        }else{
            kd = Double.valueOf(getKills(uuid) / getDeaths(uuid));
        }
        return kd;
    }
}
