package com.capri.nationlayer.config;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    //MySQL info
    public static String JDBC_URL;
    public static String MYSQL_USER;
    public static String MYSQL_PASSWORD;


    //Relation Colors
    public static String NEUTRAL;
    public static String HOSTILE;
    public static String WARRING;
    public static String ALLY;
    public static String FRIENDLY;

    public static void init(JavaPlugin plugin){
        Config config = new Config(plugin, "settings");
        plugin.getLogger().info("Loading settings...");

        JDBC_URL = config.getString("url");
        MYSQL_USER = config.getString("user");
        MYSQL_PASSWORD = config.getString("password");

        NEUTRAL = config.getString("neutral");
        HOSTILE = config.getString("hostile");
        WARRING = config.getString("warring");
        ALLY = config.getString("ally");
        FRIENDLY = config.getString("friendly");

    }
}
