package me.xenodev.sp.mysql;

import me.xenodev.sp.main.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinsSQL {

    public static boolean playerExists(UUID uuid){

        try{
            ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
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
            Main.getInstance().mysql.update("INSERT INTO Coins(UUID, COINS) VALUES ('" + uuid + "', '0');");
        }
    }

    public static Integer getCoins(UUID uuid){
        Integer i = 0;

        if(playerExists(uuid)){
            try{
                ResultSet rs = Main.getInstance().mysql.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
                if((!rs.next()) || (Integer.valueOf(rs.getInt("COINS")) == null));
                i = rs.getInt("COINS");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            createPlayer(uuid);
            getCoins(uuid);
        }

        return i;
    }

    public static void setCoins(UUID uuid, Integer coins){
        if(playerExists(uuid)){
            Main.getInstance().mysql.update("UPDATE Coins SET COINS= '" + coins + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addCoins(UUID uuid, Integer coins){
        if(playerExists(uuid)){
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
        }else{
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void removeCoins(UUID uuid, Integer coins){
        if(playerExists(uuid)){
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
        }else{
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }
}
