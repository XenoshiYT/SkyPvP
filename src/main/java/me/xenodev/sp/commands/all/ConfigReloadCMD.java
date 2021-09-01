package me.xenodev.sp.commands.all;

import me.xenodev.sp.file.ConfigFilebuilder;
import me.xenodev.sp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigReloadCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("config")){
            if(args.length == 1){
                if(!p.hasPermission("skypvp.config.reload")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.config.reload");
                    return true;
                }

                if(args[0].equalsIgnoreCase("reload")){
                    ConfigFilebuilder.getConfig();
                    p.sendMessage(Main.getPrefix() + " §7Du hast die Config neu geladen");
                }else{
                    p.sendMessage(Main.getError() + " §cDer Befehl §6" + args[0] + " §cexisitiert nicht!");
                    p.sendMessage(Main.getPrefix() + " §7Benutze den Befehl <reload>");
                }
            }else{
                p.sendMessage(Main.getPrefix() + " §7Benutze den Befehl /config <reload>");
            }

        }

        return false;
    }
}
