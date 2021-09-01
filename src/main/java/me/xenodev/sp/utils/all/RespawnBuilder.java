package me.xenodev.sp.utils.all;

import me.xenodev.sp.main.Main;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class RespawnBuilder {

    public static void respawn(Player p, Integer time){
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                ((CraftPlayer)p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
            }
        }, time);
    }

}
