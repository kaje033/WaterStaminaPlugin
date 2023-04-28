package com.qm.mainsystem.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inv implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try{
            Player p = (Player) sender;

            p.openInventory(Bukkit.getPlayer(args[0]).getInventory());
        }catch (Exception e){

        }

        return false;
    }
}
