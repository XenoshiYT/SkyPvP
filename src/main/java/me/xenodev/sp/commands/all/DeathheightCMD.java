package me.xenodev.sp.commands.all;

import me.xenodev.sp.file.LocationFilebuilder;
import me.xenodev.sp.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathheightCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("deathheight")){
            if(args.length == 1){
                if(!p.hasPermission("skypvp.deathheight.set")) {
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.deathheight.set");
                    return true;
                }

                Integer height = Integer.valueOf(args[0]);

                Main.getInstance().getConfig().set("deathheight", height);
                p.sendMessage(Main.getPrefix() + "§7Du hast die §6Todeshöhe §7gesetzt");
            }
        }


        return false;
    }
}
