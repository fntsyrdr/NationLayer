package com.capri.nationlayer.model.permissions;

import com.capri.nationlayer.model.struct.Roles;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PermissionType {
    private static HashMap<String, PermissionType> permissionByName;
    private static Map<Integer, PermissionType> permissionByID;
    private static int maximumExistingID;

    public static void init(){
        permissionByName = new HashMap<>();
        permissionByID = new TreeMap<>();
        maximumExistingID = 0;

        Map<Integer, String> dbPerms = new HashMap<>();//pull from db later
        for(Map.Entry<Integer, String> perm : dbPerms.entrySet()){
            int id = perm.getKey();
            String name = perm.getValue();
            maximumExistingID = Math.max(maximumExistingID, id);

        }


    }

    public static PermissionType getPermission(String name) {
        return permissionByName.get(name);
    }

    public static PermissionType getPermission(int id) {
        return permissionByID.get(id);
    }

    public static PermissionType registerPermission(String name, List<Roles> defaultPermLevels) {
        return registerPermission(name, defaultPermLevels, null, true);
    }

    public static PermissionType registerPermission(String name, List <Roles> defaultPermLevels, String description) {
        return registerPermission(name, defaultPermLevels, description, true);
    }

    public static PermissionType registerPermission(String name, List <Roles> defaultPermLevels, String description, boolean canBeBlacklisted) {
        if (name == null ) {
            Bukkit.getLogger().severe("Could not register permission, name was null");
            return null;
        }
        PermissionType existing = permissionByName.get(name);
        if (existing != null) {
            existing.update(defaultPermLevels, description, canBeBlacklisted);
            return existing;
        }
        //not in db yet
        int id = maximumExistingID + 1;
        maximumExistingID = id;
        PermissionType perm = internalRegisterPermission(id, name, defaultPermLevels, description, canBeBlacklisted);
        //NameLayerPlugin.getGroupManagerDao().registerPermission(perm);
        if (!defaultPermLevels.isEmpty()) {
            //NameLayerPlugin.getGroupManagerDao().addNewDefaultPermission(defaultPermLevels, perm);
        }
        return perm;
    }
    private static PermissionType internalRegisterPermission(int id, String name, List <Roles> defaultPermLevels, String description, boolean canBeBlackListed) {
        PermissionType p = new PermissionType(name, id, defaultPermLevels, description, canBeBlackListed);
        permissionByName.put(name, p);
        permissionByID.put(id, p);
        return p;
    }
    private String name;
    private List<Roles> defaultRoles;
    private int id;
    private String description;
    private boolean canBeBlacklisted;

    private PermissionType(String name, int id, List<Roles> defaultRoles, String description, boolean canBeBlacklisted){
        this.name = name;
        this.defaultRoles = defaultRoles;
        this.id = id;
        this.description = description;
        this.canBeBlacklisted = canBeBlacklisted;
    }
    public String getName() {
        return name;
    }
    public List <Roles> getDefaultPermLevels() {
        return defaultRoles;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void update(List <Roles> defaultPermLevels, String description, boolean canBeBlacklisted) {
        this.defaultRoles = defaultPermLevels;
        this.description = description;
        this.canBeBlacklisted = canBeBlacklisted;
    }
}
