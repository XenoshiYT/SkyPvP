package me.xenodev.sp.commands.all;

import me.xenodev.sp.main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EnchantCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("enchant")) {
            if (!p.hasPermission("skypvp.enchant")) {
                p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.enchant");
                return true;
            }

            if(args.length == 2){
                Enchantment enchantment = Enchantment.getByName(args[0]);
                Integer level = Integer.valueOf(args[1]);

                if(!(p.getItemInHand().equals(null) || p.getItemInHand().getType().equals(Material.AIR))){
                    p.getItemInHand().addUnsafeEnchantment(enchantment, level);
                }else{
                    p.sendMessage(Main.getError() + " §cDu hast kein Item in der Hand");
                }
            }

        }
        return false;
    }
}
