package com.qm.mainsystem.ActionBar;

import com.qm.mainsystem.Hash;
import com.qm.mainsystem.Json.Read;
import com.qm.mainsystem.MainSystem;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.qm.mainsystem.Json.Write.jPut;

public class ThirstBar implements Listener {

    public ThirstBar(MainSystem plugin){
        this.plugin = plugin;
    }
    public static MainSystem plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException, ParseException {

        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        String path = "plugins/MainSystem/WaterSystem"+uuid+".json"; //폴더 경로
        File Folder = new File(path);
        if (!Folder.exists()) {
            try{
                jPut(10,p.getUniqueId());
            }
            catch(Exception ex){
                ex.getStackTrace();
            }
        }

        Hash.addValue("thirst",p,Read.Read(uuid));

        if(Hash.getValue("stamina",p)==null){
            Hash.addValue("stamina",p,10);
        }

        String str = "●";
        String str2 = "◯";

        String str3 = "■";
        String str4 = "□";

        new BukkitRunnable() {
            @Override
            public void run() {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW+""+str4.repeat(10-(int) Hash.getValue("stamina",p))+str3.repeat((int) Hash.getValue("stamina",p))+ChatColor.AQUA + "       " + str2.repeat(10 - (int) Hash.getValue("thirst",p)) + str.repeat((int)Hash.getValue("thirst",p))));

                if((int) Hash.getValue("thirst",p) == 0){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,plugin.getConfig().getInt("갈증 구속 지속시간"),plugin.getConfig().getInt("갈증 독 레벨")-1,true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,plugin.getConfig().getInt("갈증 멀미 지속시간"),plugin.getConfig().getInt("갈증 멀미 레벨")-1,true));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,plugin.getConfig().getInt("갈증 독 지속시간"),plugin.getConfig().getInt("갈증 구속 레벨")-1,true));

                    if((boolean)Hash.getValue("check",p)) {
                        p.sendMessage(ChatColor.RED + "수분 섭취가 필요합니다 물을 끓여 " + ChatColor.AQUA + "증류수" + ChatColor.RED + "를 만들어 마시세요");
                        Hash.addValue("check",p,false);
                    }
                }
                else {
                    Hash.addValue("check",p,true);
                }

                if((int) Hash.getValue("stamina",p)==0){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,plugin.getConfig().getInt("스태미나 구속 지속시간")*20,plugin.getConfig().getInt("스태미나 구속 레벨")-1,true));
                }

            }
        }.runTaskTimer(plugin,0,0);

        new BukkitRunnable(){
            @Override
            public void run(){

                if(!p.isOp()) {
                    if ((int) Hash.getValue("thirst", p) != 0) {
                        Hash.addValue("thirst", p, (int) Hash.getValue("thirst", p) - 1);

                        jPut((int) Hash.getValue("thirst", p), uuid);
                    }
                }
            }
        }.runTaskTimer(plugin,plugin.getConfig().getInt("물 다는 속도") * 20,plugin.getConfig().getInt("물 다는 속도") * 20);

    }
}
