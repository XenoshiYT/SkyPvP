package me.xenodev.sp.events.all;

import me.xenodev.sp.main.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {

    @EventHandler
    public void onFootDamage(EntityDamageEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.STARVATION){
            if(Main.getInstance().fooddamage == true){
                e.setCancelled(false);
            }else{
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        Player p = (Player) e.getEntity();
        if(Main.getInstance().fooddamage == true){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
            p.setFoodLevel(20);
            p.setSaturation(20);
        }
    }
}
