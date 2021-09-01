package me.xenodev.sp.events.all;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FreeItemEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        Entity entity = e.getRightClicked();
        Player p = e.getPlayer();

        if(entity instanceof ItemFrame){
            ItemFrame frame = (ItemFrame) e.getRightClicked();
            if(!(p.hasPermission("skypvp.freeitem.rotate") && p.isSneaking())) {
                frame.setRotation(Rotation.NONE);
                if(!(frame.getItem().getType().equals(Material.AIR) || frame.getItem().equals(null))){
                    e.setCancelled(true);
                    ItemStack stack = frame.getItem();

                    Inventory inv = Bukkit.createInventory(null, 9*3, "§a§lFree Items");
                    for(int i = 0; i < 27; i++){
                        inv.setItem(i, stack);
                    }
                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent e){
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if(damager instanceof Player && entity instanceof ItemFrame){
            Player p = (Player) e.getDamager();

            if(!(p.hasPermission("skypvp.freeitem.remove") && p.isSneaking())){
                e.setCancelled(true);
            }
        }
    }

}
