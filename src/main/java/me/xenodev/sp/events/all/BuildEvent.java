package me.xenodev.sp.events.all;

import me.xenodev.sp.commands.all.BuildCMD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildEvent implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!BuildCMD.build.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!BuildCMD.build.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpringBreak(PlayerInteractEvent e){
        if(e.getAction().equals(Action.PHYSICAL)){
            e.setCancelled(true);
        }
    }

}
