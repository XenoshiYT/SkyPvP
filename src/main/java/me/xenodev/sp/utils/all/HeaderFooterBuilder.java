package me.xenodev.sp.utils.all;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class HeaderFooterBuilder {

    public static void sendTab(Player p, String header, String footer) {
        if (header == null) {
            header = "";
        }
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) {
            footer = "";
        }
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        header = header.replaceAll("%player%", p.getName()).replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())).replaceAll("%max%", String.valueOf(Bukkit.getMaxPlayers())).replaceAll("%ping%", String.valueOf(PingBuilder.getPing(p)));
        footer = footer.replaceAll("%player%", p.getName()).replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())).replaceAll("%max%", String.valueOf(Bukkit.getMaxPlayers())).replaceAll("%ping%", String.valueOf(PingBuilder.getPing(p)));

        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.sendPacket(headerPacket);
        }
    }
}
