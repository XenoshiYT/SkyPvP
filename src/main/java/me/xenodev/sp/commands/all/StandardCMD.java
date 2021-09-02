package me.xenodev.sp.commands.all;

import me.xenodev.sp.main.Main;
import me.xenodev.sp.utils.player.RespawnItemBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StandardCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("standard")){
            RespawnItemBuilder.setStandardItems(p);
            p.sendMessage(Main.getPrefix() + "§7Du hast dir die §6Standard§7-Ausrüstung geholt");
        }

        return false;
    }
}
