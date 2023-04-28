//package com.qm.mainsystem.Event;
//
//import com.qm.mainsystem.MainSystem;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.Action;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.scheduler.BukkitRunnable;
//
//public class PlayerInteract implements Listener {
//
//    public PlayerInteract(MainSystem plugin){
//        this.plugin = plugin;
//    }
//    public static MainSystem plugin;
//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent e){
//        Player p =e.getPlayer();
//
//        if(e.getAction()== Action.LEFT_CLICK_AIR){
//            new BukkitRunnable(){
//                @Override
//                public void run(){
//
//                }
//            }.runTaskTimer();
//        }
//    }
//}
