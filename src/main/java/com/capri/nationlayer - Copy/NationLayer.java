package com.capri.nationlayer;

import com.capri.nationlayer.command.CommandHandler;
import com.capri.nationlayer.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class NationLayer extends JavaPlugin {

    public static NationLayer instance;


    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.init(this);





        String url = ConfigManager.JDBC_URL;
        String user= ConfigManager.MYSQL_USER;
        String password = ConfigManager.MYSQL_PASSWORD;

        try {
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&eStarting Database Connection"));
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&aDatabase Connection Complete!"));



        } catch (SQLException e) {
            System.out.println(ChatColor.translateAlternateColorCodes('&',"&cConnection failed!"));
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void getCommands(){
        getCommand("AcceptInvite").setExecutor(new CommandHandler());


    }
}
