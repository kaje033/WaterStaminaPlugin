package com.qm.mainsystem;

import com.qm.mainsystem.ActionBar.StaminaBar;
import com.qm.mainsystem.Command.Inv;
import com.qm.mainsystem.Command.Water;
import com.qm.mainsystem.Event.PlayerItemConsume;
import com.qm.mainsystem.ActionBar.ThirstBar;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class MainSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("\n\nWaterSystem 플러그인이 활성화 되었습니다\nmade by question_Mark\n\n");

        getServer().getPluginManager().registerEvents(new ThirstBar(this),this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsume(),this);
        getServer().getPluginManager().registerEvents(new StaminaBar(this),this);
        getCommand("물").setExecutor(new Water());
        getCommand("inv").setExecutor(new Inv());

        ItemStack potion = new ItemStack(Material.POTION);
        List<String> potionLore = new ArrayList<String>();
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        potionMeta.setColor(Color.AQUA);
        potionMeta.setDisplayName(ChatColor.AQUA+"증류수");
        potionLore.add(ChatColor.WHITE+"마실 수 있는 물 입니다");

        potionMeta.setLore(potionLore);
        potion.setItemMeta(potionMeta);

        FurnaceRecipe r2 = new FurnaceRecipe(potion,Material.POTION);
        getServer().addRecipe(r2);

        saveConfig();
        File cfile = new File(getDataFolder(), "config.yml");
        if (cfile.length() == 0) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("\n\nWaterSystem 플러그인이 비활성화 되었습니다\nmade by question_Mark\n\n");
    }



}
