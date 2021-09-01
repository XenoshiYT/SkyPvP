package me.xenodev.sp.utils.player;

import me.xenodev.sp.utils.all.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class RespawnItemBuilder {

    public static void setStarterItems(Player p){

        p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName("§e§lSkyPvP §8× §7Starter").build());
        p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName("§e§lSkyPvP §8× §7Starter").build());
        p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName("§e§lSkyPvP §8× §7Starter").build());
        p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName("§e§lSkyPvP §8× §7Starter").build());

        p.getInventory().addItem(new ItemBuilder(Material.WOOD_SWORD).setEnchantment(Enchantment.DAMAGE_ALL, 1).setName("§e§lSkyPvP §8× §7Starter").build());
        p.getInventory().addItem(new ItemBuilder(Material.COOKED_BEEF).setAmount(16).setName("§e§lSkyPvP §8× §7Starter").build());

    }

}
