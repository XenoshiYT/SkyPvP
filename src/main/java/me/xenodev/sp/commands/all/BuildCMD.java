package me.xenodev.sp.commands.all;

import me.xenodev.sp.main.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCMD implements CommandExecutor {


    public static ArrayList<Player> build = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("build")) {
            if (!p.hasPermission("skypvp.allow.build")) {
                p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.allow.build");
                return true;
            }

            if (build.contains(p)) {
                build.remove(p);
                p.sendMessage(Main.getPrefix() + "§7Du hast den Buildingmodus §cverlassen");
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().clear();
            } else {
                build.add(p);
                p.sendMessage(Main.getPrefix() + "§7Du hast den Buildingmodus §cbetreten");
                p.setGameMode(GameMode.CREATIVE);
                p.getInventory().clear();
            }
        }
        return false;
    }

}
