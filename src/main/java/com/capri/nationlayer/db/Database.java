package com.capri.nationlayer.db;
import com.capri.nationlayer.model.faction.Nation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class Database {
    private Connection conn;

    public Database(String url, String user, String password) throws SQLException{
        conn = DriverManager.getConnection(url, user, password);
    }


    public void migrateTables(){

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM NationLayer");
            rs.next();
            int rowCount = rs.getInt(1);
            if(rowCount == 0) {
                System.out.println(ChatColor.translateAlternateColorCodes('&',"&eDatabase appears empty, attempting to generate tables..."));
                createDataStructure();
            }

        } catch (SQLException e) {
            System.out.println(ChatColor.translateAlternateColorCodes('&',"&cConnection failed!"));
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void createDataStructure(){
        String createNationTable = "CREATE TABLE nation(nation_id CHAR[36] PRIMARY KEY, name CHAR[26], owner CHAR[36], " +
                "ally_nation_id TEXT, enemy_nation_id TEXT, password CHAR[25], )";
        String createNationPlayersTable = "CREATE TABLE members(nation_id CHAR[36], member_id CHAR[36])";
        String createNationRelationTable = "CREATE TABLE relations(origin_id CHAR[36], target_id CHAR[36], relation BIT";


        try {
            Statement statement = conn.createStatement();
            statement.executeQuery(createNationTable);
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&aNationTable...Done!"));
            statement.executeQuery(createNationPlayersTable);
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&aNationPlayerTable...Done!"));
            statement.executeQuery(createNationRelationTable);
            System.out.println(ChatColor.translateAlternateColorCodes('&', "&aRelationTable...Done!"));
            System.out.println(ChatColor.translateAlternateColorCodes('&',"&aTables generated successfully!"));
        } catch (SQLException e) {
            System.out.println(ChatColor.translateAlternateColorCodes('&',"&cGeneration failed!"));
            throw new RuntimeException(e);
        }


    }

    public void createNation(Nation nation){

        StringBuilder sb = new StringBuilder();

        String id = nation.getId().toString();
        String name = nation.getName();
        Player player = Bukkit.getServer().getPlayer(nation.getOwner().toString());
        String owner = player.getUniqueId().toString();
        String password = nation.getPassword();
        List<UUID> allies = nation.getAllies();
        List<UUID> enemy = nation.getEnemies();


        for(int i = 0; i < allies.size(); i++){
            sb.append(allies.get(i));
            if(i < allies.size() - 1){
                sb.append(", ");
            }
        }
        String allyCSV = sb.toString();
        for(int i = 0; i < enemy.size(); i++){
            sb.append(enemy.get(i));
            if(i < enemy.size() - 1){
                sb.append(", ");
            }
        }
        String enemyCSV = sb.toString();

        String createNation = "INSERT INTO nation(nation_id, name, owner, ally_nation_id, enemy_nation_id, password) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(createNation);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, owner);
            ps.setString(4, allyCSV);
            ps.setString(5, enemyCSV);
            ps.setString(6, password);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void addNationMember(Player player, Nation nation){
        String id = player.getUniqueId().toString();
        String nationId = nation.getId().toString();

        String addNationMember = "INSERT INTO members(nation_id, member_id ) VALUES(" + nationId + ", " + id;

        try {
            Statement statement = conn.createStatement();
            statement.executeQuery(addNationMember);
        } catch (SQLException e) {
            System.out.println(ChatColor.translateAlternateColorCodes('&',"&cFailed to insert @ members"));
            throw new RuntimeException(e);
        }

    }
    public void removerNationMember(Player player){
        String id = player.getUniqueId().toString();
        String removeNationMember = "UPDATE members SET nation_id = null WHERE member_id =" + id;
        try {
            Statement statement = conn.createStatement();
            statement.executeQuery(removeNationMember);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
