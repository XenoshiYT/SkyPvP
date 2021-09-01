package me.xenodev.sp.events.all;

import me.xenodev.sp.main.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamageEvent implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.FALL){
            if(Main.getInstance().falldamage == true){
                e.setCancelled(false);
            }else{
                e.setCancelled(true);
            }
        }
    }

}
