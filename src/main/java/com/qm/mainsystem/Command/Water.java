package com.qm.mainsystem.Command;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;
import java.util.List;

public class Water implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(p.isOp()){
            ItemStack potion = new ItemStack(Material.POTION);
            List<String> potionLore = new ArrayList<String>();
            PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

            potionMeta.setColor(Color.AQUA);
            potionMeta.setDisplayName(ChatColor.AQUA+"증류수");
            potionLore.add(ChatColor.WHITE+"마실 수 있는 물 입니다");

            potionMeta.setLore(potionLore);
            potion.setItemMeta(potionMeta);

            p.getInventory().addItem(potion);
        }
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage("콘솔에서는 이 명령어를 실행할 수 없습니다");
            return false;
        }

        return false;
    }
}
