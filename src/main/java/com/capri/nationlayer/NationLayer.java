package com.capri.nationlayer;

import com.capri.nationlayer.command.CommandHandler;
import com.capri.nationlayer.config.ConfigManager;
import com.capri.nationlayer.db.Database;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class NationLayer extends JavaPlugin {

    public static NationLayer instance;
    public Database db;





    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.init(this);
        try {
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&eStarting Database Connection"));
            db = new Database(ConfigManager.JDBC_URL, ConfigManager.MYSQL_USER, ConfigManager.MYSQL_PASSWORD);
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&aDatabase Connection Complete!"));

            System.out.println(ChatColor.translateAlternateColorCodes('&', "&eChecking Migration..."));
            db.migrateTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void getCommands(){
        getCommand("Nationlayer").setExecutor(new CommandHandler());


    }
}
