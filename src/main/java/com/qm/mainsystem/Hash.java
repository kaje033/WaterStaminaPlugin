package com.qm.mainsystem;

import org.bukkit.entity.Player;

import java.util.Map;

public class Hash {
    protected static java.util.HashMap<String, java.util.HashMap<Player, Object>> Stats = new java.util.HashMap<String, java.util.HashMap<Player, Object>>();
    public static void addValue(String StatType, Player p, Object value) {
        java.util.HashMap<Player, Object> scist = Stats.get(StatType);
        if (scist == null) {
            scist = new java.util.HashMap<Player, Object>();
        }

        scist.put(p, value);
        Stats.put(StatType, scist);

    }
    public static Object getValue(String StatType, Player p) {
        Map<Player, Object> scist = Stats.get(StatType);
        if (scist == null) {
            return null;
        }
        return scist.get(p);
    }
    public static void resetValue(String StatType, Player p) {
        Map<Player, Object> scist = Stats.get(StatType);
        if (scist == null) {
            return;
        }
        scist.remove(p);
        Stats.put(StatType, (java.util.HashMap<Player, Object>) scist);
    }
}
