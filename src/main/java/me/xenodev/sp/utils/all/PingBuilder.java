package me.xenodev.sp.utils.all;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingBuilder {

    public static Integer getPing(Player p){
        return ((CraftPlayer)p).getHandle().ping;
    }

}
