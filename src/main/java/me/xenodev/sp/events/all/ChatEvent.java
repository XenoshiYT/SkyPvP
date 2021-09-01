package me.xenodev.sp.events.all;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);
        if(p.hasPermission("skypvp.chat.team")){
            if(p.hasPermission("skypvp.chat.color")){
                e.setFormat(user.getPrefix().replace("&", "§") + " §8| §7" + p.getName() + " §7» §a§l" + e.getMessage().replace("&", "§").replace("%", "%%"));
            }else{
                e.setFormat(user.getPrefix().replace("&", "§") + " §8| §7" + p.getName() + " §7» §a§l" + e.getMessage().replace("%", "%%"));
            }
        }else{
            if(p.hasPermission("skypvp.chat.color")){
                e.setFormat(user.getPrefix().replace("&", "§") + " §8| §7" + p.getName() + " §7» §f" + e.getMessage().replace("&", "§").replace("%", "%%"));
            }else{
                e.setFormat(user.getPrefix().replace("&", "§") + " §8| §7" + p.getName() + " §7» §f" + e.getMessage().replace("%", "%%"));
            }
        }
    }

}
