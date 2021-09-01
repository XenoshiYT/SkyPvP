package me.xenodev.sp.commands.player;

import me.xenodev.sp.main.Main;
import me.xenodev.sp.utils.all.ItemBuilder;
import me.xenodev.sp.utils.player.KitItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KitCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("kits") || cmd.getName().equalsIgnoreCase("kit")){
            if(args.length == 2){
                if(!p.hasPermission("skypvp.kits.edit")){
                    p.sendMessage(Main.getError() + " §cDu hast dazu keine Rechte. Du benötigst: §6" + "skypvp.kits.edit");
                    return true;
                }

                if(args[0].equalsIgnoreCase("create")){
                    if(args[1].equalsIgnoreCase("kit1")){
                        KitItemBuilder.setKit(p, "1");
                        p.sendMessage(Main.getPrefix() + " §7Du hast das Kit " + Main.getInstance().kit1name.replace("&", "§") + " §7erstellt");
                    }else if(args[1].equalsIgnoreCase("kit2")){
                        KitItemBuilder.setKit(p, "2");
                        p.sendMessage(Main.getPrefix() + " §7Du hast das Kit " + Main.getInstance().kit2name.replace("&", "§") + " §7erstellt");
                    }else if(args[1].equalsIgnoreCase("kit3")){
                        KitItemBuilder.setKit(p, "3");
                        p.sendMessage(Main.getPrefix() + " §7Du hast das Kit " + Main.getInstance().kit3name.replace("&", "§") + " §7erstellt");
                    }else if(args[1].equalsIgnoreCase("kit4")){
                        KitItemBuilder.setKit(p, "4");
                        p.sendMessage(Main.getPrefix() + " §7Du hast das Kit " + Main.getInstance().kit4name.replace("&", "§") + " §7erstellt");
                    }else if(args[1].equalsIgnoreCase("kit5")){
                        KitItemBuilder.setKit(p, "5");
                        p.sendMessage(Main.getPrefix() + " §7Du hast das Kit " + Main.getInstance().kit5name.replace("&", "§") + " §7erstellt");
                    }else{
                        p.sendMessage(Main.getError() + " §cDer Befehl §6" + args[1] + " §cexisitiert nicht!");
                        p.sendMessage(Main.getPrefix() + " §7Benutze den Befehl <kit1-5>");
                    }
                }else{
                    p.sendMessage(Main.getError() + " §cDer Befehl §6" + args[0] + " §cexisitiert nicht!");
                    p.sendMessage(Main.getPrefix() + " §7Benutze den Befehl <create>");
                }
            }else {
                Inventory inv = Bukkit.createInventory(null, 9 * 6, "§c§lKits");

                for (int i = 0; i < 54; i++) {
                    inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 15).build());
                }

                if(KitItemBuilder.file1.exists()) {
                    inv.setItem(11, new ItemBuilder(Material.SKULL_ITEM, (short) 3).setOwnerURL(Main.getInstance().kit1head).setName(Main.getInstance().kit1name.replace("&", "§")).setLore("", "§7Das Kit kostet: §c" + Main.getInstance().kit1cost).build());
                }else{
                    inv.setItem(11, new ItemBuilder(Material.BARRIER).setName("§cDieses Kit existiert nicht").build());
                }
                if(KitItemBuilder.file2.exists()) {
                    inv.setItem(15, new ItemBuilder(Material.SKULL_ITEM, (short) 3).setOwnerURL(Main.getInstance().kit2head).setName(Main.getInstance().kit2name.replace("&", "§")).setLore("", "§7Das Kit kostet: §c" + Main.getInstance().kit2cost).build());
                }else{
                    inv.setItem(15, new ItemBuilder(Material.BARRIER).setName("§cDieses Kit existiert nicht").build());
                }
                if(KitItemBuilder.file3.exists()) {
                    inv.setItem(28, new ItemBuilder(Material.SKULL_ITEM, (short) 3).setOwnerURL(Main.getInstance().kit3head).setName(Main.getInstance().kit3name.replace("&", "§")).setLore("", "§7Das Kit kostet: §c" + Main.getInstance().kit3cost).build());
                }else{
                    inv.setItem(28, new ItemBuilder(Material.BARRIER).setName("§cDieses Kit existiert nicht").build());
                }
                if(KitItemBuilder.file4.exists()) {
                    inv.setItem(34, new ItemBuilder(Material.SKULL_ITEM, (short) 3).setOwnerURL(Main.getInstance().kit4head).setName(Main.getInstance().kit4name.replace("&", "§")).setLore("", "§7Das Kit kostet: §c" + Main.getInstance().kit4cost).build());
                }else{
                    inv.setItem(34, new ItemBuilder(Material.BARRIER).setName("§cDieses Kit existiert nicht").build());
                }
                if(KitItemBuilder.file5.exists()) {
                    inv.setItem(40, new ItemBuilder(Material.SKULL_ITEM, (short) 3).setOwnerURL(Main.getInstance().kit5head).setName(Main.getInstance().kit5name.replace("&", "§")).setLore("", "§7Das Kit kostet: §c" + Main.getInstance().kit5cost).build());
                }else{
                    inv.setItem(40, new ItemBuilder(Material.BARRIER).setName("§cDieses Kit existiert nicht").build());
                }

                p.openInventory(inv);
            }
        }
        return false;
    }
}
