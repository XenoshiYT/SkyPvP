package me.xenodev.sp.utils.all;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material){
        this(material, (short) 0);
    }

    public ItemBuilder setName(String name){
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setAmount(Integer amount){
        item.setAmount(amount);

        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, Integer level){
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder setColor(Color color){
        LeatherArmorMeta lmeta = (LeatherArmorMeta) meta;
        lmeta.setColor(color);
        return this;
    }

    public ItemBuilder setLore(String... lore){
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(){
        meta.spigot().setUnbreakable(true);
        return this;
    }

    public ItemBuilder setOwner(String name){
        SkullMeta smeta = (SkullMeta)meta;
        smeta.setOwner(name);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }

}
