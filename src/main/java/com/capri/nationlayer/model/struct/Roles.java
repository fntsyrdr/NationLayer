package com.capri.nationlayer.model.struct;


import javax.management.relation.Role;
import java.util.HashMap;

public enum Roles {

    NOT_BLACKLISTED,
    MEMBER,
    MOD,
    ADMIN,
    OWNER,
    PRIMARY_OWNER;

    private final static HashMap<String, Roles> BY_NAME = new HashMap<>();
    static {
        for (Roles rank : values()){
            BY_NAME.put(rank.name(), rank);
        }
    }

    public static Roles getRole(String role){
        return BY_NAME.get(role.toUpperCase());
    }
    public static String getStringOfRoles(){
        StringBuilder ranks = new StringBuilder();
        for (String rank: BY_NAME.keySet()){
            ranks.append(rank);
            ranks.append(" ");
        }
        return ranks.toString();
    }
    public static Roles getByID(int id){
        switch (id){
            case 0:
                return Roles.NOT_BLACKLISTED;
            case 1:
                return Roles.MEMBER;
            case 2:
                return Roles.MOD;
            case 3:
                return Roles.ADMIN;
            case 4:
                return Roles.OWNER;
            case 5:
                return Roles.PRIMARY_OWNER;
            default:
                return null;
        }
    }
    public static int getID(Roles role) {
        if (role == null) {
            return -1;
        }
        switch (role) {
            case NOT_BLACKLISTED:
                return 0;
            case MEMBER:
                return 1;
            case MOD:
                return 2;
            case ADMIN:
                return 3;
            case OWNER:
                return 4;
            case PRIMARY_OWNER:
                return 5;
            default:
                return -1;
        }
    }
    public static String getNiceRankName(Roles role) {
        if (role == null) {
            return "RANK_ERROR";
        }
        switch (role) {
            case MEMBER:
                return "Member";
            case MOD:
                return "Mod";
            case ADMIN:
                return "Admin";
            case OWNER:
                return "Owner";
            case PRIMARY_OWNER:
                return "Primary Owner";
            case NOT_BLACKLISTED:
                return "Anyone who is not blacklisted";
        }
        return "RANK_ERROR";
    }
}

