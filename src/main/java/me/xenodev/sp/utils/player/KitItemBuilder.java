package me.xenodev.sp.utils.player;

import me.xenodev.sp.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KitItemBuilder {

    public static File file1 = new File("plugins//" + Main.getInstance().getName() + "//Kit", "kit1.yml");
    public static YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

    public static File file2 = new File("plugins//" + Main.getInstance().getName() + "//Kit", "kit2.yml");
    public static YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);

    public static File file3 = new File("plugins//" + Main.getInstance().getName() + "//Kit", "kit3.yml");
    public static YamlConfiguration cfg3 = YamlConfiguration.loadConfiguration(file3);

    public static File file4 = new File("plugins//" + Main.getInstance().getName() + "//Kit", "kit4.yml");
    public static YamlConfiguration cfg4 = YamlConfiguration.loadConfiguration(file4);

    public static File file5 = new File("plugins//" + Main.getInstance().getName() + "//Kit", "kit5.yml");
    public static YamlConfiguration cfg5 = YamlConfiguration.loadConfiguration(file5);

    public static void getKit(Player p, String kit){
        if(kit.equalsIgnoreCase("1")){
            List<ItemStack> items = (List<ItemStack>) cfg1.get("Kit1");
            for(ItemStack stack : items){
                p.getInventory().addItem(stack);
            }
        }else if(kit.equalsIgnoreCase("2")){
            List<ItemStack> items = (List<ItemStack>) cfg2.getList("Kit2");
            for(ItemStack stack : items){
                p.getInventory().addItem(stack);
            }
        }else if(kit.equalsIgnoreCase("3")){
            List<ItemStack> items = (List<ItemStack>) cfg3.getList("Kit3");
            for(ItemStack stack : items){
                p.getInventory().addItem(stack);
            }
        }else if(kit.equalsIgnoreCase("4")){
            List<ItemStack> items = (List<ItemStack>) cfg4.getList("Kit4");
            for(ItemStack stack : items){
                p.getInventory().addItem(stack);
            }
        }else if(kit.equalsIgnoreCase("5")){
            List<ItemStack> items = (List<ItemStack>) cfg5.getList("Kit5");
            for(ItemStack stack : items){
                p.getInventory().addItem(stack);
            }
        }
    }

    public static void setKit(Player p, String kit){
        if(kit.equalsIgnoreCase("1")){
            cfg1.set("Kit1", null);
            ArrayList<ItemStack> list = new ArrayList<>();
            ItemStack[] items = p.getInventory().getContents();
            for(int i = 0; i < items.length; i++){
                ItemStack item = items[i];
                if(!(item == null)){
                    if(item.getType() != Material.AIR){
                        list.add(item);
                    }
                }
            }
            cfg1.set("Kit1", list);
            try {
                cfg1.save(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(kit.equalsIgnoreCase("2")){
            cfg2.set("Kit2", null);
            ArrayList<ItemStack> list = new ArrayList<>();
            ItemStack[] items = p.getInventory().getContents();
            for(int i = 0; i < items.length; i++){
                ItemStack item = items[i];
                if(!(item == null)){
                    if(item.getType() != Material.AIR){
                        list.add(item);
                    }
                }
            }
            cfg2.set("Kit2", list);
            try {
                cfg2.save(file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(kit.equalsIgnoreCase("3")){
            cfg3.set("Kit3", null);
            ArrayList<ItemStack> list = new ArrayList<>();
            ItemStack[] items = p.getInventory().getContents();
            for(int i = 0; i < items.length; i++){
                ItemStack item = items[i];
                if(!(item == null)){
                    if(item.getType() != Material.AIR){
                        list.add(item);
                    }
                }
            }
            cfg3.set("Kit3", list);
            try {
                cfg3.save(file3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(kit.equalsIgnoreCase("4")){
            cfg4.set("Kit4", null);
            ArrayList<ItemStack> list = new ArrayList<>();
            ItemStack[] items = p.getInventory().getContents();
            for(int i = 0; i < items.length; i++){
                ItemStack item = items[i];
                if(!(item == null)){
                    if(item.getType() != Material.AIR){
                        list.add(item);
                    }
                }
            }
            cfg4.set("Kit4", list);
            try {
                cfg4.save(file4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(kit.equalsIgnoreCase("5")){
            cfg5.set("Kit5", null);
            ArrayList<ItemStack> list = new ArrayList<>();
            ItemStack[] items = p.getInventory().getContents();
            for(int i = 0; i < items.length; i++){
                ItemStack item = items[i];
                if(!(item == null)){
                    if(item.getType() != Material.AIR){
                        list.add(item);
                    }
                }
            }
            cfg5.set("Kit5", list);
            try {
                cfg5.save(file5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
