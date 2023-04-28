package com.qm.mainsystem.ActionBar;

import com.qm.mainsystem.Hash;
import com.qm.mainsystem.MainSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class StaminaBar implements Listener {

    public StaminaBar(MainSystem plugin){
        this.plugin = plugin;
    }
    public static MainSystem plugin;

    @EventHandler
    public void onSprinting(PlayerToggleSprintEvent e){
        Player p = e.getPlayer();

        if(!p.isOp()){

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (e.isSprinting()) {
                        if (Hash.getValue("stamina", p) == null) {
                            Hash.addValue("stamina", p, 10);
                        }
                        if ((int) Hash.getValue("stamina", p) > 0) {
                            Hash.addValue("stamina", p, (int) Hash.getValue("stamina", p) - 1);
                        }
                        if ((int) Hash.getValue("stamina", p) < 1) {
                            if (!p.isSprinting()) {
                                start(p);

                                cancel();
                            }

                        }
                        if ((int) Hash.getValue("stamina", p) > 0) {
                            if (!p.isSprinting()) {
                                start(p);

                                cancel();
                            }
                        }
                    }
                }
            }.runTaskTimer(plugin,0,plugin.getConfig().getInt("갈증 구속 지속시간") * 20);
        }
    }
    public void start(Player p){
        new BukkitRunnable(){
            @Override
            public void run(){
                if((int) Hash.getValue("stamina",p)<10) {
                    Hash.addValue("stamina", p, (int) Hash.getValue("stamina", p) + 1);
                }
                else {
                    cancel();
                }
                if(p.isSprinting()){
                    cancel();
                }
            }
        }.runTaskTimer(plugin,0,plugin.getConfig().getInt("스태미나 다는 속도") * 20);

    }
}
