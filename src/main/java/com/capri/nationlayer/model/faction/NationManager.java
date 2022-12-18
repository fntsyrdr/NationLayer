package com.capri.nationlayer.model.faction;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class NationManager {
    private static NationManager instance;
    protected Map<UUID, Nation> nations;
    protected Map<String, UUID> nationNames;

    protected Map<UUID, NationPlayer> players;

    public boolean isNameTaken(String name){
        return this.nationNames.get(name) != null;
    }
    private NationPlayer getPlayer(Player player){
        return this.getPlayer(player.getUniqueId());
    }
    private NationPlayer getPlayer(UUID uuid){
        return this.players.get(uuid);
    }
    public Nation getNationByUUID(UUID uuid){
        return this.nations.get(uuid);
    }
    public Nation getNationByName(String name){
        return this.getNationByUUID(this.nationNames.get(name));
    }


    public static NationManager getInstance() {
        return instance;
    }

}
