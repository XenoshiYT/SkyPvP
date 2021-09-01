package me.xenodev.sp.events.all;

import me.xenodev.sp.file.CoinsFilebuilder;
import me.xenodev.sp.main.Main;
import me.xenodev.sp.mysql.CoinsSQL;
import me.xenodev.sp.utils.player.KitItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitSelectEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("§c§lKits")){
            e.setCancelled(true);
            if(e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE) || e.getCurrentItem().equals(null)) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().kit1name.replace("&", "§"))){
                p.closeInventory();
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    if(CoinsFilebuilder.getCoins(p) >= Main.getInstance().kit1cost){
                        CoinsFilebuilder.removeCoins(p, Main.getInstance().kit1cost);
                        KitItemBuilder.getKit(p, "1");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit1name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    if(CoinsSQL.getCoins(p.getUniqueId()) >= Main.getInstance().kit1cost){
                        CoinsSQL.removeCoins(p.getUniqueId(), Main.getInstance().kit1cost);
                        KitItemBuilder.getKit(p, "1");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit1name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().kit2name.replace("&", "§"))){
                p.closeInventory();
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    if(CoinsFilebuilder.getCoins(p) >= Main.getInstance().kit2cost){
                        CoinsFilebuilder.removeCoins(p, Main.getInstance().kit2cost);
                        KitItemBuilder.getKit(p, "2");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit2name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    if(CoinsSQL.getCoins(p.getUniqueId()) >= Main.getInstance().kit2cost){
                        CoinsSQL.removeCoins(p.getUniqueId(), Main.getInstance().kit2cost);
                        KitItemBuilder.getKit(p, "2");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit2name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().kit3name.replace("&", "§"))){
                p.closeInventory();
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    if(CoinsFilebuilder.getCoins(p) >= Main.getInstance().kit3cost){
                        CoinsFilebuilder.removeCoins(p, Main.getInstance().kit3cost);
                        KitItemBuilder.getKit(p, "3");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit3name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    if(CoinsSQL.getCoins(p.getUniqueId()) >= Main.getInstance().kit3cost){
                        CoinsSQL.removeCoins(p.getUniqueId(), Main.getInstance().kit3cost);
                        KitItemBuilder.getKit(p, "3");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit3name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().kit4name.replace("&", "§"))){
                p.closeInventory();
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    if(CoinsFilebuilder.getCoins(p) >= Main.getInstance().kit4cost){
                        CoinsFilebuilder.removeCoins(p, Main.getInstance().kit4cost);
                        KitItemBuilder.getKit(p, "4");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit4name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    if(CoinsSQL.getCoins(p.getUniqueId()) >= Main.getInstance().kit4cost){
                        CoinsSQL.removeCoins(p.getUniqueId(), Main.getInstance().kit4cost);
                        KitItemBuilder.getKit(p, "4");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit4name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().kit5name.replace("&", "§"))){
                p.closeInventory();
                if(Main.getInstance().datasave.equalsIgnoreCase("File")){
                    if(CoinsFilebuilder.getCoins(p) >= Main.getInstance().kit5cost){
                        CoinsFilebuilder.removeCoins(p, Main.getInstance().kit5cost);
                        KitItemBuilder.getKit(p, "5");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit5name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }else if(Main.getInstance().datasave.equalsIgnoreCase("MySQL")){
                    if(CoinsSQL.getCoins(p.getUniqueId()) >= Main.getInstance().kit5cost){
                        CoinsSQL.removeCoins(p.getUniqueId(), Main.getInstance().kit5cost);
                        KitItemBuilder.getKit(p, "5");
                        p.sendMessage(Main.getPrefix() + " §7Du hast dir das Kit " + Main.getInstance().kit5name.replace("&", "§") + " §7gekauft");
                    }else{
                        p.sendMessage(Main.getError() + " §cDu besitzt nicht genügen Coins um dir das Kit zu kaufen");
                    }
                }
            }
        }
    }


}
