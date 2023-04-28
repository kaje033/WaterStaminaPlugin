package com.qm.mainsystem.Event;

import com.qm.mainsystem.Hash;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.qm.mainsystem.Json.Write.jPut;

public class PlayerItemConsume implements Listener {

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) throws IOException, ParseException {

        Player p = e.getPlayer();

        ItemStack potion = new ItemStack(Material.POTION);
        ItemStack potion1 = new ItemStack(Material.POTION);

        List<String> potionLore = new ArrayList<String>();

        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
        PotionMeta potionMeta1 = (PotionMeta) potion.getItemMeta();

        potionMeta.setColor(Color.AQUA);
        potionMeta.setDisplayName(ChatColor.AQUA+"증류수");
        potionLore.add(ChatColor.WHITE+"마실 수 있는 물 입니다");
        potionMeta1.setBasePotionData(new PotionData(PotionType.WATER));

        potionMeta.setLore(potionLore);
        potion.setItemMeta(potionMeta);
        potion1.setItemMeta(potionMeta1);

        if(e.getItem().getItemMeta().equals(potion.getItemMeta())){
            if((int) Hash.getValue("thirst",p)<10) {
                Hash.addValue("thirst",p,(int) Hash.getValue("thirst",p)+2);
                jPut((int) Hash.getValue("thirst",p),e.getPlayer().getUniqueId());
            }
            else{
                e.setCancelled(true);
            }
            if((int) Hash.getValue("thirst",p)>10){
                Hash.addValue("thirst",p,10);
                jPut((int) Hash.getValue("thirst",p),e.getPlayer().getUniqueId());
            }
        }
        if(e.getItem().getItemMeta().equals(potion1.getItemMeta())){
            if((int) Hash.getValue("thirst",p)>0) {
                Hash.addValue("thirst", p, (int) Hash.getValue("thirst", p) - 1);
                jPut((int) Hash.getValue("thirst", p), e.getPlayer().getUniqueId());

            }
        }
    }
}
